package example;

import javax.jws.WebMethod;
import javax.jws.WebService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import classes.UserDriver;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

@WebService(serviceName = "OrderGojekService")
public class OrderGojek {
    public int expiryTime(String token) throws IOException, ParseException {
        String USER_AGENT = "Mozilla/5.0";
        String url = "http://localhost:8001/validateToken";
        URL connection = null;
        int result = 0;
        try {
            connection = new URL(url);
            HttpURLConnection conIS = (HttpURLConnection) connection.openConnection();

            //add request header
            conIS.setRequestMethod("POST");
            conIS.setRequestProperty("User-Agent", USER_AGENT);
            conIS.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            String urlParameters;
            urlParameters = "token=" + token;
            // Send post request
            conIS.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conIS.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();

            int responseCode = conIS.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(conIS.getInputStream()));
            String inputLine;
            StringBuilder resp = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                resp.append(inputLine);
            }
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(resp.toString());
            conIS.disconnect();
            String retVal = obj.get("token_auth").toString();
            System.out.println(retVal);
            if (retVal.equals("VALID")) {
                result = 1;
            } else if (retVal.equals("INVALID")) {
                result = 0;
            } else {
                result = 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @WebMethod(operationName = "addOrderToDatabase")
    public boolean addOrderToDatabase(String token, String pickingPoint, String destination, int passengerId, int driverId, int rating,
                                   String comment) throws IllegalAccessException, IOException, ParseException {
        if (expiryTime(token) == 1) {
            com.mysql.jdbc.Connection con = null;
            try {
                Calendar now = Calendar.getInstance();
                java.sql.Date sqlDate = new java.sql.Date(now.getTimeInMillis());
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = (com.mysql.jdbc.Connection) DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ojekonline", "root", "");
                Statement stmt = con.createStatement();
                String query = "insert into orders(date, picking_point, destination, passenger_id, driver_id, rating, " +
                        "comment, hide_driver, hide_passenger) " +
                        "values " + "('" + sqlDate + "','" + pickingPoint + "','" + destination + "','" + passengerId +
                        "','" + driverId + "','" + rating + "','" + comment + "','" + 0 + "','" + 0 + "')";
                int rs = stmt.executeUpdate(query);
                stmt.close();
                con.close();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }
        else return false;
    }

    @WebMethod(operationName = "getPreferredDrivers")
    public ArrayList<Driver> getPreferredDrivers(boolean isPref, int id, String pickingPoint, String destination,
                                                 String name) throws IllegalAccessException, ParseException {
        ArrayList<Driver> driverList = new ArrayList<>();
        try {
            String USER_AGENT = "Mozilla/5.0";
            String url = "http://localhost:8001/orderDriverServlet";
            URL connection = new URL(url);
            HttpURLConnection conIS = (HttpURLConnection) connection.openConnection();

            //add request header
            conIS.setRequestMethod("POST");
            conIS.setRequestProperty("User-Agent", USER_AGENT);
            conIS.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            String urlParameters;
            if (isPref) {
                urlParameters = "userId=" + id +
                        "&preferredDriver=" + name +
                        "&isPref=yes";
            } else {
                urlParameters = "userId=" + id +
                        "&preferredDriver=" + name +
                        "&isPref=no";
            }
            // Send post request
            conIS.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conIS.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();

            int responseCode = conIS.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(conIS.getInputStream()));
            String inputLine;
            StringBuilder resp = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                resp.append(inputLine);
            }
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(resp.toString());
            conIS.disconnect();
            String status = (String) obj.get("status");

            if(status.equals("good")) {
                com.mysql.jdbc.Connection con = null;

                ArrayList<UserDriver> driver = new ArrayList<>();
                org.json.simple.JSONArray jArray = (org.json.simple.JSONArray) obj.get("prefDriverList");
                if (jArray != null) {
                    for (int i=0; i<jArray.size(); i++){
                        String user_username = ((JSONObject) jArray.get(i)).get("userUsername").toString();
                        String user_name = ((JSONObject) jArray.get(i)).get("userName").toString();
                        int userId = Integer.parseInt(((JSONObject) jArray.get(i)).get("userId").toString());
                        driver.add(new UserDriver(userId, user_name, user_username));
                    }
                }
                for (UserDriver x : driver) {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = (com.mysql.jdbc.Connection) DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/ojekonline", "root", "");
                    Statement stmt = con.createStatement();
                    String query = "SELECT * FROM driver WHERE driver_id = '" + x.getUserId() + "' AND " +
                            "(UPPER(driver_location) = UPPER('" + pickingPoint + "') OR UPPER(driver_location) = " +
                            "UPPER('" + destination + "'));";
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs.next()) {
                        String queryRating = "SELECT COUNT(rating) AS votes, SUM(rating)/COUNT(rating) AS ratings " +
                                "FROM orders WHERE driver_id = '" + x.getUserId() + "';";
                        Statement stmt2 = con.createStatement();
                        ResultSet rs2 = stmt2.executeQuery(queryRating);
                        int vote = 0;
                        float rating = 0;
                        while (rs2.next()) {
                            rating = rs2.getFloat("ratings");
                            vote = rs2.getInt("votes");
                        }
                        driverList.add(Driver.setDriverParam(x.getUserId(), x.getUserName(), x.getUserUsername(), x.getUserId() + ".png", vote, rating));
                        rs2.close();
                        stmt2.close();
                    }
                    rs.close();
                    stmt.close();
                    con.close();
                }
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driverList;
    }
}
