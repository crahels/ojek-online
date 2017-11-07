import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet(urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        String token = req.getParameter("token");
        JSONObject arrayObj;
        arrayObj = new JSONObject();
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection Con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/user_ojekonline",
                    "root", "");
            Statement st = Con.createStatement();
            int i = st.executeUpdate("DELETE FROM user WHERE  user_token = '" + token + "';");
            if (i>0){
                arrayObj.put("status","ok");

            }
            else {
                arrayObj.put("status","error");
            }
            resp.setContentType("application/json:charset=UTF-8");
            resp.getWriter().write(arrayObj.toString());
        }
        catch (Throwable theException) {
            System.out.println(theException);
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