package example;

import javax.jws.WebMethod;
import javax.jws.WebService;
import com.mysql.jdbc.Connection;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

@WebService(serviceName = "OrderGojekService")
public class OrderGojek {
    @WebMethod(operationName = "checkExpiryTime")
    public boolean checkExpiryTime(String token) {
        //implementasi cek ke identity service
        // true means udh expired
        return false;
    }

    @WebMethod(operationName = "getPreferredDrivers")
    public ArrayList<Driver> getPreferredDrivers(String pickingPoint, String destination, String name) throws IllegalAccessException {
        ArrayList<Driver> driver = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ojekonline","root","");
            Statement stmt = con.createStatement();
            String query = "SELECT DISTINCT user_id, user_name FROM (SELECT user_id, user_name from user WHERE (user_status = '0' OR user_status = '2') AND UPPER(user_name) =UPPER('" + name + "')) AS datadriver " +
                    "INNER JOIN driver ON (datadriver.user_id = driver.driver_id) WHERE UPPER(driver_location) = UPPER('" + pickingPoint + "') OR UPPER(driver_location) =UPPER('" + destination + "');";
            // take id, name, profile picture, votes, rating
            ResultSet rs = stmt.executeQuery(query);
            driver = new ArrayList<>();
            while(rs.next()) {
                String queryRating = "SELECT COUNT(rating) AS votes, SUM(rating)/COUNT(rating) AS ratings FROM orders WHERE driver_id = '" + rs.getInt("user_id") + "';";
                Statement stmt2 = con.createStatement();
                ResultSet rs2 = stmt2.executeQuery(queryRating);
                int user_id = rs.getInt("user_id");
                String user_name = rs.getString("user_name");
                float rating = 0;
                int vote = 0;
                while (rs2.next()) {
                    rating = rs2.getFloat("ratings");
                    vote = rs2.getInt("votes");
                }
                driver.add(Driver.setDriverParam(user_id, user_name, "default.png", vote, rating));
                rs2.close();
                stmt2.close();
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (ClassNotFoundException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    @WebMethod(operationName = "getOtherDrivers")
    public String getOtherDrivers(String pickingPoint, String destination, String name) {
        return "";
    }
}
