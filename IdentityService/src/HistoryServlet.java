import classes.UserDriver;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HistoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        if (req.getParameter("isDriver").equals("yes")) {
            try {
                getDriverHistory(req, resp);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (req.getParameter("isDriver").equals("no")) {
            getPassengerHistory(req, resp);
        }
    }

    public void getDriverHistory(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        JSONObject arrayObj;
        arrayObj = new JSONObject();

        int userID = Integer.parseInt(req.getParameter("passengerID"));

        com.mysql.jdbc.Connection Con = null;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Con = (com.mysql.jdbc.Connection) DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/user_ojekonline", "root", "");
        Statement stmt = Con.createStatement();
        String query = "SELECT user_name from user_info WHERE user_id = '" + userID + "';";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String name = rs.getString("user_name");
            arrayObj.put("user_name", name);
        }
        resp.setContentType("application/json:charset=UTF-8");
        resp.getWriter().write(arrayObj.toString());
    }

    public void getPassengerHistory(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

    }
}