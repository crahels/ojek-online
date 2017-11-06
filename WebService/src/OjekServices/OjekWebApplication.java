package OjekServices;

import javax.jws.WebService;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.xml.ws.Endpoint;

@WebService(serviceName = "OjekWebApplicationService")
public class OjekWebApplication {

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
    }

    public static void main(String[] argv) {
        Object implementor = new OjekWebApplication ();
        String address = "http://localhost:8002/OjekWebApplication";
        Endpoint.publish(address, implementor);
    }
}
