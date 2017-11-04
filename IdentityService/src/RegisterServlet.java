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

@WebServlet(name = "RegisterServlet", urlPatterns = {"/registerServlet"})
public class RegisterServlet extends HttpServlet {

    public String generateToken(int len) {
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

            User user = new User(req.getParameter("username"),
                    req.getParameter("email"), req.getParameter("password"));
            String username = user.getUsername();
            String email = user.getEmail();
            String password = user.getPassword();

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            arrayObj.put("qwe", "YES");
            Con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/user_ojekonline",
                    "root", "");
            String checkingQuery = "SELECT * FROM user WHERE user_username = '" + username + "' OR user_email = '"
                    + email + "';";
            Statement stmt = Con.createStatement();
            ResultSet rs = stmt.executeQuery(checkingQuery);
            if (rs != null) {
                arrayObj.put("valid", "no");
            } else {
                arrayObj.put("valid", "yes");
                try {
                    //connect to database
                    Statement st = Con.createStatement();
                    String token = generateToken(20);

                    Calendar now = Calendar.getInstance();
                    now.add(Calendar.MINUTE, 15);
                    java.sql.Date sqlDate = new java.sql.Date(now.getTimeInMillis());
                    arrayObj.put("token", token);
                    //ResultSet rs;
                    String q = "insert into user(user_token, user_username, user_email, user_pass, expiry_time) " +
                            "values " + "('" + token + "','" + username + "','" + email + "','" + password + "','"
                            + sqlDate + "')";
                    System.out.println("Query: " + q);
                    int i = st.executeUpdate(q);
                    if (i > 0) {
                        String getIDQuery = "SELECT user_id FROM user WHERE user_username = '" + username + "';";
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
            }
        } catch ( ClassNotFoundException | IllegalAccessException | InstantiationException |
                SQLException | JSONException theException) {
            arrayObj.put("zxc", theException);
            System.out.println("Register failed: An Exception has occurred! " + theException);
        } finally {
            if (Con != null) {
                try {
                    Con.close();
                } catch (Exception e) {
                }
            }
        }
        arrayObj.put("asdf", "NO");
        resp.setContentType("application/json:charset=UTF-8");
        resp.getWriter().write(arrayObj.toString());
    }
}