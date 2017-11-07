import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Calendar;
import com.mysql.jdbc.Connection;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject arrayObj;
        arrayObj = new JSONObject();
        Connection Con = null;
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/user_ojekonline",
                    "root", "");
            String checkingQuery = "SELECT * FROM user_info WHERE user_username = '" + username + "' AND user_pass = '"
                    + password + "';";
            Statement stmt = Con.createStatement();
            ResultSet rs = stmt.executeQuery(checkingQuery);
            if (!rs.next()) {
                arrayObj.put("valid", "no");
            } else {
                arrayObj.put("valid", "yes");
                try {
                    int id = rs.getInt("user_id");
                    String name = rs.getString("user_name");
                    String phone = rs.getString("user_phone");
                    String status = rs.getString("user_status");
                    String email = rs.getString("user_email");

                    Statement st = Con.createStatement();
                    String token = RegisterServlet.generateToken(20);
                    Calendar now = Calendar.getInstance();
                    now.add(Calendar.DATE, 1);
                    java.sql.Date sqlDate = new java.sql.Date(now.getTimeInMillis());
                    int i = st.executeUpdate("INSERT INTO user(user_id, user_token, expiry_time) VALUES ('" +
                            id + "','" + token + "','" + sqlDate + "');");

                    if (i > 0) {
                        arrayObj.put("user_token", "yes");
                        arrayObj.put("user_id", id);
                        arrayObj.put("user_name", name);
                        arrayObj.put("user_phone", phone);
                        arrayObj.put("user_status", status);
                        arrayObj.put("user_email", email);
                        arrayObj.put("token", token);
                        System.out.println("id : " + id + "\n");
                        System.out.println("Successfully add new user to database");
                    } else {
                        System.out.println("Failed to add token to database");
                        arrayObj.put("user_token", "no");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            resp.setContentType("application/json:charset=UTF-8");
            resp.getWriter().write(arrayObj.toString());
        }
        catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException | JSONException theException) {
            System.out.println(theException);
        } finally {
            if (Con != null) {
                try {
                    Con.close();
                } catch (Exception e) {
                }
            }
        }
    }
}
