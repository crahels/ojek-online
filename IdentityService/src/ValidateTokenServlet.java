import org.json.JSONException;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "ValidateToken",urlPatterns = {"/validateToken"})
public class ValidateTokenServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        JSONObject arrayObj;
        arrayObj = new JSONObject();
        String token = req.getParameter("token");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            try (Connection Con = (Connection) DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/user_ojekonline","root", "")) {
                Statement st = Con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM user WHERE user_token = '" + token + "';");
                if(rs.next()){
                    int id = rs.getInt("user_id");
                    java.sql.Date dbSqlDate = rs.getDate("expiry_time");
                    java.util.Date dbSqlDateConverted = new java.util.Date(dbSqlDate.getTime());
                    Calendar expiry_time = Calendar.getInstance();
                    expiry_time.setTime(dbSqlDateConverted);
                    long time = expiry_time.getTimeInMillis();
                    long now = Calendar.getInstance().getTimeInMillis();
                    if(now < time) {
                        arrayObj.put("token_auth", "VALID");
                        String query = "SELECT * FROM user_info WHERE user_id = '" + id + "';";
                        Statement stmt = Con.createStatement();
                        ResultSet rs1 = stmt.executeQuery(query);

                        if (rs1.next()) {
                            String name = rs1.getString("user_name");
                            String phone = rs1.getString("user_phone");
                            String status = rs1.getString("user_status");
                            String email = rs1.getString("user_email");
                            arrayObj.put("id", id);
                            arrayObj.put("user_name", name);
                            arrayObj.put("user_phone", phone);
                            arrayObj.put("user_status", status);
                            arrayObj.put("user_email", email);
                        }
                    }
                    else {
                        /*Statement stmt = Con.createStatement();
                        stmt.executeUpdate("DELETE FROM user WHERE  user_token = '" + token + "';");*/
                        arrayObj.put("token_auth", "EXPIRED");
                    }
                }
                else{
                    arrayObj.put("token_auth", "INVALID");
                }
                resp.setContentType("application/json:charset=UTF-8");
                resp.getWriter().write(arrayObj.toString());
            }
        } catch (InstantiationException | IllegalAccessException | JSONException ex) {
            Logger.getLogger(ValidateTokenServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ValidateTokenServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ValidateTokenServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}