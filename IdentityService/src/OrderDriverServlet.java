import org.json.JSONObject;
import classes.UserDriver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

/*@WebServlet(name = "OrderDriverServlet", urlPatterns = {"/orderDriverServlet"})*/
public class OrderDriverServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        if (req.getParameter("isPref").equals("yes")) {
            getPreferredDriver(req, resp);
        } else if (req.getParameter("isPref").equals("no")) {
            //getOtherDriver(req, resp);
        }
    }

    public void getPreferredDriver(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject arrayObj;
        arrayObj = new JSONObject();
        com.mysql.jdbc.Connection con = null;
        ArrayList<UserDriver> driver = null;
        try {
            int userId = Integer.parseInt(req.getParameter("userId"));
            String preferredDriver = req.getParameter("preferredDriver");
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = (com.mysql.jdbc.Connection) DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/user_ojekonline", "root", "");
                Statement stmt = con.createStatement();
                String query = "SELECT user_id, user_name, user_username from user_info WHERE (user_status = '0' OR user_status = '2') AND UPPER(user_name) = UPPER('" + preferredDriver + "')" + "AND user_id <> '" + userId + "';";
                ResultSet rs = stmt.executeQuery(query);
                driver = new ArrayList<>();
                while (rs.next()) {
                    driver.add(new UserDriver(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_username")));
                }
                rs.close();
                stmt.close();
                con.close();
                arrayObj.put("prefDriverList",driver);
                arrayObj.put("status","good");
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

    public void getOtherDriver(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject arrayObj;
        arrayObj = new JSONObject();
        com.mysql.jdbc.Connection con = null;
        ArrayList<UserDriver> driver = null;
        try {
            int userId = Integer.parseInt(req.getParameter("userId"));
            String preferredDriver = req.getParameter("preferredDriver");
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = (com.mysql.jdbc.Connection) DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/user_ojekonline", "root", "");
                Statement stmt = con.createStatement();
                String query = "SELECT user_id, user_name, user_username from user_info WHERE (user_status = '0' OR user_status = '2') AND UPPER(user_name) <> UPPER('" + preferredDriver + "')" + "AND user_id <> '" + userId + "';";
                ResultSet rs = stmt.executeQuery(query);
                driver = new ArrayList<>();
                while (rs.next()) {
                    driver.add(new UserDriver(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_username")));
                }
                rs.close();
                stmt.close();
                con.close();
                arrayObj.put("prefDriverList",driver);
                arrayObj.put("status","good");
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
