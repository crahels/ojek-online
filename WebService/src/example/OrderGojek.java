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
    @WebMethod(operationName = "checkExpiryTime")
    public boolean checkExpiryTime(String token) {
        //implementasi cek ke identity service
        // true means udh expired
        return false;
    }

    /*
    @WebMethod(operationName = "registerDOOServlet")
    public void RegisterDOOServlet(String param_username, String param_phone_number, String param_status) throws IOException {
        org.json.JSONObject arrayObj = new org.json.JSONObject();
        com.mysql.jdbc.Connection Con = null;
        try {
            UserDOO user = new UserDOO(param_username,
                    param_phone_number);
            String username = user.getUsername();
            String phone_number = user.getPhoneNumber();

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ojekonline",
                    "root", "");

            String status = param_status;
            try {
                //connect to database
                Statement st = Con.createStatement();
                //ResultSet rs;
                String q = "insert into user(user_name, user_phone, user_status) " +
                        "values " + "('" + username + "','" + phone_number + "','" + status + "')";
                System.out.println("Query: " + q);
                int i = st.executeUpdate(q);
                if (i > 0) {
                    String getIDQuery = "SELECT user_id FROM user WHERE user_name = '" + username + "';";
                    Statement stm = Con.createStatement();
                    ResultSet rs1 = stm.executeQuery(getIDQuery);
                    while (rs1.next()) {
                        int id = rs1.getInt("user_id");
                        arrayObj.put("id", id);
                        System.out.println("id : " + id + "\n");
                    }
                    System.out.println("Successfully add new user to database");
                    arrayObj.put("tes", "yes");
                } else {
                    System.out.println("Failed to add token to database");
                    arrayObj.put("tes", "no");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch ( ClassNotFoundException | IllegalAccessException | InstantiationException |
                SQLException | JSONException theException) {
            System.out.println("Register failed: An Exception has occurred! " + theException);
        } finally {
            if (Con != null) {
                try {
                    Con.close();
                } catch (Exception e) {
                }
            }
        }
    }*/

    @WebMethod(operationName = "addOrderToDatabase")
    public void addOrderToDatabase(String pickingPoint, String destination, int passengerId, int driverId, int rating, String comment) throws IllegalAccessException {
        com.mysql.jdbc.Connection con = null;
        try {
            Calendar now = Calendar.getInstance();
            java.sql.Date sqlDate = new java.sql.Date(now.getTimeInMillis());
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = (com.mysql.jdbc.Connection) DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ojekonline", "root", "");
            Statement stmt = con.createStatement();
            String query = "insert into orders(date, picking_point, destination, passenger_id, driver_id, rating, comment, hide_driver, hide_passenger) " +
                    "values " + "('" + sqlDate + "','" + pickingPoint + "','" + destination + "','" + passengerId + "','"
                    + driverId + "','" + rating + "','" + comment + "','" + 0 + "','" + 0 + "')";
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
    }

    @WebMethod(operationName = "getPreferredDrivers")
    public ArrayList<Driver> getPreferredDrivers(boolean isPref, int id, String pickingPoint, String destination, String name) throws IllegalAccessException, ParseException {
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
                    String query = "SELECT * FROM driver WHERE driver_id = '" + x.getUserId() + "' AND (UPPER(driver_location) = UPPER('" + pickingPoint + "') OR UPPER(driver_location) = UPPER('" + destination + "'));";
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs.next()) {
                        String queryRating = "SELECT COUNT(rating) AS votes, SUM(rating)/COUNT(rating) AS ratings FROM orders WHERE driver_id = '" + x.getUserId() + "';";
                        Statement stmt2 = con.createStatement();
                        ResultSet rs2 = stmt2.executeQuery(queryRating);
                        int vote = 0;
                        float rating = 0;
                        while (rs2.next()) {
                            rating = rs2.getFloat("ratings");
                            vote = rs2.getInt("votes");
                        }
                        driverList.add(Driver.setDriverParam(x.getUserId(), x.getUserName(), x.getUserUsername(), "default.png", vote, rating));
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
