package example;

import classes.UserDriver;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

@WebService(serviceName = "LocationService")
public class Location {
    @WebMethod(operationName = "checkExpiryTime")
    public boolean checkExpiryTime(String token) {
        //implementasi cek ke identity service
        // true means udh expired
        return false;
    }

    @WebMethod(operationName = "addLocation")
    public void addLocation(int userId, String locName) throws IllegalAccessException {
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
    }

    @WebMethod(operationName = "deleteLocation")
    public void deleteLocation(int userId, String locName) throws IllegalAccessException {
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
    }

    @WebMethod(operationName = "saveLocation")
    public void saveLocation(int userId, String oldName, String newName) throws IllegalAccessException {
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
