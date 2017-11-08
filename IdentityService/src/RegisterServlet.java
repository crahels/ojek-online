import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Calendar;
import com.mysql.jdbc.Connection;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    public static String generateToken(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject arrayObj = new JSONObject();
        Connection Con = null;
        try {
            User user = new User(req.getParameter("name"), req.getParameter("phone_number"),
                    req.getParameter("isDriver"), req.getParameter("username"), req.getParameter("email"),
                    req.getParameter("password"));
            String name = user.getName();
            String phone = user.getPhone();
            String status = user.getStatus();
            String username = user.getUsername();
            String email = user.getEmail();
            String password = user.getPassword();

            //connect to the database
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/user_ojekonline",
                    "root", "");
            String checkingQuery = "SELECT * FROM user_info WHERE user_username = '" + username + "' OR user_email = '"
                    + email + "';";
            Statement stmt = Con.createStatement();
            ResultSet rs = stmt.executeQuery(checkingQuery);
            if (rs.next()) {
                arrayObj.put("valid", "no");
            } else {
                arrayObj.put("valid", "yes");
                try {
                    Statement insertUser = Con.createStatement();
                    String insertUserQuery = "INSERT INTO user_info(user_name, user_phone, user_status, " +
                            "user_username, user_email, user_pass) VALUES ('" +  name + "','" + phone + "','"
                            + status + "','" + username + "','" + email + "','" + password + "');";
                    int i = insertUser.executeUpdate(insertUserQuery);

                    if (i <= 0) {
                        arrayObj.put("user_info", "no");
                    }
                    else {
                        arrayObj.put("user_info", "yes");
                    }
                    String getIDQuery = "SELECT user_id FROM user_info WHERE user_username = '" + username + "';";
                    Statement stm = Con.createStatement();
                    ResultSet rs1 = stm.executeQuery(getIDQuery);
                    while (rs1.next()) {
                        int id = rs1.getInt("user_id");
                        String token = generateToken(20);
                        Calendar now = Calendar.getInstance();
                        now.add(Calendar.DATE, 1);
                        java.sql.Date sqlDate = new java.sql.Date(now.getTimeInMillis());

                        Statement st = Con.createStatement();
                        String q = "INSERT INTO user(user_id, user_token, expiry_time) " +
                                "VALUES ('" + id + "','" + token + "','" + sqlDate + "');";
                        int j = st.executeUpdate(q);
                        if (j > 0) {
                            System.out.println("Successfully add new user to database");
                            arrayObj.put("user_token", "yes");
                            arrayObj.put("user_id", id);
                            arrayObj.put("user_name", name);
                            arrayObj.put("user_phone", phone);
                            arrayObj.put("user_username", username);
                            arrayObj.put("user_status", status);
                            arrayObj.put("user_email", email);
                            arrayObj.put("token", token);
                        } else {
                            System.out.println("Failed to add token to database");
                            arrayObj.put("user_token", "no");
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
        resp.setContentType("application/json:charset=UTF-8");
        resp.getWriter().write(arrayObj.toString());
    }
}