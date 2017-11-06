package example;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.mysql.jdbc.Connection;

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

    @WebMethod(operationName = "getPreferredDrivers")
    public ArrayList<example.Driver> getPreferredDrivers(String pickingPoint, String destination, String name) throws IllegalAccessException {
        ArrayList<example.Driver> driver = null;
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
                driver.add(example.Driver.setDriverParam(user_id, user_name, "default.png", vote, rating));
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
