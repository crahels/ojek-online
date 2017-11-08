package example;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

@WebService(serviceName = "HistoryGojekService")
public class HistoryGojek {
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

    @WebMethod(operationName = "hidePassanger")
    public boolean hidePassanger(String token, int order_id) throws IllegalAccessException, IOException, ParseException {
        if (expiryTime(token) == 1) {
            com.mysql.jdbc.Connection con = null;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = (com.mysql.jdbc.Connection) DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ojekonline", "root", "");
                Statement stmt = con.createStatement();
                String query = "UPDATE orders SET hide_passenger = 1 WHERE order_id =" + order_id +";";
                int rs = stmt.executeUpdate(query);
                stmt.close();
                con.close();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return true;
        }
        else return false;
    }

    @WebMethod(operationName = "hideDriver")
    public boolean hideDriver(String token, int order_id) throws IllegalAccessException, IOException, ParseException {
        if (expiryTime(token) == 1) {
            com.mysql.jdbc.Connection con = null;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = (com.mysql.jdbc.Connection) DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ojekonline", "root", "");
                Statement stmt = con.createStatement();
                String query = "UPDATE orders SET hide_driver = 1 WHERE order_id =" + order_id +";";
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

    @WebMethod(operationName = "getDriverHistory")
    public ArrayList<UserDriverHistory> getDriverHistory(int driver_id) throws IllegalAccessException, ParseException,
            ClassNotFoundException, InstantiationException {
        ArrayList<UserDriverHistory> passengerList = new ArrayList<UserDriverHistory>();
        try {
            com.mysql.jdbc.Connection Con = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Con = (com.mysql.jdbc.Connection) DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ojekonline", "root", "");
            Statement stmt = Con.createStatement();
            String query = "SELECT * from orders WHERE driver_id = '" + driver_id + "' AND hide_passenger='0';";
            ResultSet rs = stmt.executeQuery(query);

            java.sql.Date dbSqlDate;
            java.util.Date dbSqlDateConverted;
            Calendar expiry_time;
            long date;
            int passenger_id;
            String picking_point;
            String destination;
            int rating;
            String comment;
            int order_id;
            int id;

            while (rs.next()) {
                dbSqlDate = rs.getDate("date");
                dbSqlDateConverted = new java.util.Date(dbSqlDate.getTime());
                expiry_time = Calendar.getInstance();
                expiry_time.setTime(dbSqlDateConverted);
                date = expiry_time.getTimeInMillis();
                passenger_id = rs.getInt("passenger_id");
                picking_point = rs.getString("picking_point");
                destination = rs.getString("destination");
                rating = rs.getInt("rating");
                comment = rs.getString("comment");
                order_id = rs.getInt("order_id");
                try {
                    String USER_AGENT = "Mozilla/5.0";
                    String url = "http://localhost:8001/historyServlet";
                    URL connection = new URL(url);
                    HttpURLConnection conIS = (HttpURLConnection) connection.openConnection();

                    //add request header
                    conIS.setRequestMethod("POST");
                    conIS.setRequestProperty("User-Agent", USER_AGENT);
                    conIS.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                    String urlParameters;
                    urlParameters = "passengerID=" + passenger_id;
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
                    String name = (String) obj.get("user_name");
                    id = Integer.parseInt(obj.get("user_id").toString());
                    passengerList.add(new UserDriverHistory(id, date, name, picking_point, destination, rating, comment,
                            order_id));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            rs.close();
            stmt.close();
            return passengerList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengerList;
    }

    @WebMethod(operationName = "getPassengerHistory")
    public ArrayList<UserDriverHistory> getPassengerHistory(int passenger_id) throws IllegalAccessException, ParseException,
            ClassNotFoundException, InstantiationException {
        ArrayList<UserDriverHistory> passengerList = new ArrayList<UserDriverHistory>();
        try {
            com.mysql.jdbc.Connection Con = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Con = (com.mysql.jdbc.Connection) DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ojekonline", "root", "");
            Statement stmt = Con.createStatement();
            String query = "SELECT * from orders WHERE passenger_id = '" + passenger_id + "' AND hide_driver='0';";
            ResultSet rs = stmt.executeQuery(query);

            java.sql.Date dbSqlDate;
            java.util.Date dbSqlDateConverted;
            Calendar expiry_time;
            long date;
            int driver_id;
            String picking_point;
            String destination;
            int rating;
            String comment;
            int order_id;
            int id;

            while (rs.next()) {
                dbSqlDate = rs.getDate("date");
                dbSqlDateConverted = new java.util.Date(dbSqlDate.getTime());
                expiry_time = Calendar.getInstance();
                expiry_time.setTime(dbSqlDateConverted);
                date = expiry_time.getTimeInMillis();
                driver_id = rs.getInt("driver_id");
                picking_point = rs.getString("picking_point");
                destination = rs.getString("destination");
                rating = rs.getInt("rating");
                comment = rs.getString("comment");
                order_id = rs.getInt("order_id");
                try {
                    String USER_AGENT = "Mozilla/5.0";
                    String url = "http://localhost:8001/historyServlet";
                    URL connection = new URL(url);
                    HttpURLConnection conIS = (HttpURLConnection) connection.openConnection();

                    //add request header
                    conIS.setRequestMethod("POST");
                    conIS.setRequestProperty("User-Agent", USER_AGENT);
                    conIS.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                    String urlParameters;
                    urlParameters = "passengerID=" + driver_id;
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
                    String name = (String) obj.get("user_name");
                    id = Integer.parseInt(obj.get("user_id").toString());
                    passengerList.add(new UserDriverHistory(id, date, name, picking_point, destination, rating, comment,
                            order_id));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            rs.close();
            stmt.close();
            return passengerList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengerList;
    }
}
