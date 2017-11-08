package example;

import classes.UserDriver;
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
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

@WebService(serviceName = "LocationService")
public class Location {
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

    @WebMethod(operationName = "addLocation")
    public boolean addLocation(String token, int userId, String locName) throws IllegalAccessException, IOException, ParseException {
        if (expiryTime(token) == 1) {
            com.mysql.jdbc.Connection con = null;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = (com.mysql.jdbc.Connection) DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ojekonline", "root", "");
                Statement stmt = con.createStatement();
                String query = "insert into driver(driver_id, driver_location) " +
                        "values " + "('" + userId + "','" + locName + "')";
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

    @WebMethod(operationName = "deleteLocation")
    public boolean deleteLocation(String token, int userId, String locName) throws IllegalAccessException, IOException, ParseException {
        if (expiryTime(token) == 1) {
            com.mysql.jdbc.Connection con = null;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = (com.mysql.jdbc.Connection) DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ojekonline", "root", "");
                Statement stmt = con.createStatement();
                String query = "DELETE FROM driver WHERE driver_id = '" + userId + "' AND driver_location = '" + locName + "';";
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

    @WebMethod(operationName = "saveLocation")
    public boolean saveLocation(String token, int userId, String oldName, String newName) throws IllegalAccessException, IOException, ParseException {
        if (expiryTime(token) == 1) {
            com.mysql.jdbc.Connection con = null;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = (com.mysql.jdbc.Connection) DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ojekonline", "root", "");
                Statement stmt = con.createStatement();
                String query = "UPDATE driver SET driver_location = '" + newName + "' WHERE driver_id = '" + userId +
                        "' AND driver_location = '" + oldName + "';";
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

    @WebMethod(operationName = "getUserLocation")
    public ArrayList<String> getUserLocation(int userId) {
        com.mysql.jdbc.Connection con = null;
        ArrayList<String> locs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = (com.mysql.jdbc.Connection) DriverManager.getConnection (
                    "jdbc:mysql://localhost:3306/ojekonline", "root", "");
            Statement stmt = con.createStatement();
            String query = "SELECT driver_location FROM driver WHERE driver_id = '" + userId + "';";
            ResultSet rs = stmt.executeQuery(query);
            locs = new ArrayList<>();
            while (rs.next()) {
                locs.add(rs.getString("driver_location"));
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (ClassNotFoundException | InstantiationException | SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return locs;
    }
}
