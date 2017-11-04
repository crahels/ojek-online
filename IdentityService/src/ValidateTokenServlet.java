import org.json.JSONException;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "ValidateToken",urlPatterns = {"/validateToken"})
public class ValidateTokenServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        JSONObject arrayObj;
        arrayObj = new JSONObject();
        String token = request.getParameter("token");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            try (Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/user_ojekonline","root", "")) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM user WHERE token = '" + token + "';");
                if(rs.next()){
                    arrayObj.put("res", "SUCCESS");
                    arrayObj.put("id", rs.getString("id"));
                }
                else{
                    arrayObj.put("res", "FAIL");
                }
                response.setContentType("application/json:charset=UTF-8");
                response.getWriter().write(arrayObj.toString());
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