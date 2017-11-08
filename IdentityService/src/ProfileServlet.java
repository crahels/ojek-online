import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject arrayObj;
        arrayObj = new JSONObject();
        com.mysql.jdbc.Connection con = null;
        ArrayList<User> user = null;
        try {
            int userId = Integer.parseInt(req.getParameter("userId"));
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = (com.mysql.jdbc.Connection) DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/user_ojekonline", "root", "");
                Statement stmt = con.createStatement();
                String getDataUserQuery = "SELECT * FROM user_info WHERE user_id = '" + userId + "';";
                ResultSet rs = stmt.executeQuery(getDataUserQuery);
                user = new ArrayList<>();
                if (rs.next()) {
                    user.add(new User(rs.getString("user_name"), rs.getString("user_phone")
                            , rs.getString("user_status"), rs.getString("user_username"),
                            rs.getString("user_email"), rs.getString("user_pass")));
                }
                rs.close();
                stmt.close();
                con.close();
                arrayObj.put("dataUserList", user);
            } catch (ClassNotFoundException | InstantiationException | SQLException e) {
                e.printStackTrace();
                arrayObj.put("status", "bad");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                arrayObj.put("status", "bad");
            }
        } catch (Exception e) {
            e.printStackTrace();
            arrayObj.put("status", "bad");
        }
        resp.setContentType("application/json:charset=UTF-8");
        resp.getWriter().write(arrayObj.toString());
    }
}
