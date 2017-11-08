package example;

import javax.jws.WebMethod;
import javax.jws.WebService;

import classes.UserDriver;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@WebService(serviceName = "ProfileService")
public class Profile {
    @WebMethod(operationName = "getDataUser")
    public ArrayList<User> getDataUser(int userId) {
        ArrayList<User> user = new ArrayList<>();
        try {
            String USER_AGENT = "Mozilla/5.0";
            String url = "http://localhost:8001/profileServlet";
            URL connection = new URL(url);
            HttpURLConnection conIS = (HttpURLConnection) connection.openConnection();

            //add request header
            conIS.setRequestMethod("POST");
            conIS.setRequestProperty("User-Agent", USER_AGENT);
            conIS.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = "userId=" + userId;
            // Send post request
            conIS.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conIS.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();

            int responseCode = conIS.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(conIS.getInputStream()));
            String inputLine;
            StringBuilder resp = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                resp.append(inputLine);
            }
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(resp.toString());
            conIS.disconnect();
            org.json.simple.JSONArray jArray = (org.json.simple.JSONArray) obj.get("dataUserList");
            if (jArray != null) {
                String user_name = ((JSONObject) jArray.get(0)).get("name").toString();
                String user_phone = ((JSONObject) jArray.get(0)).get("phone").toString();
                String user_status = ((JSONObject) jArray.get(0)).get("status").toString();
                String user_username = ((JSONObject) jArray.get(0)).get("username").toString();
                String user_email = ((JSONObject) jArray.get(0)).get("email").toString();
                String user_pass = ((JSONObject) jArray.get(0)).get("password").toString();
                user.add(new User(user_name, user_phone, user_status, user_username, user_email, user_pass));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        {

        }
        return user;
    }

    @WebMethod(operationName = "getDriverByID")
    public Driver getDriverByID(int userId) throws IllegalAccessException, ParseException {
        ArrayList<Driver> driverList = new ArrayList<>();
        Driver driverOjek = new Driver();
        try {
            String USER_AGENT = "Mozilla/5.0";
            String url = "http://localhost:8001/orderDriverServlet";
            URL connection = new URL(url);
            HttpURLConnection conIS = (HttpURLConnection) connection.openConnection();

            //add request header
            conIS.setRequestMethod("POST");
            conIS.setRequestProperty("User-Agent", USER_AGENT);
            conIS.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = "userId=" + userId +
                    "&preferredDriver=" + "" +
                    "&isPref=null";
            // Send post request
            conIS.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conIS.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();

            int responseCode = conIS.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(conIS.getInputStream()));
            String inputLine;
            StringBuilder resp = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                resp.append(inputLine);
            }
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(resp.toString());
            conIS.disconnect();
            String status = (String) obj.get("status");

            if (status.equals("good")) {
                com.mysql.jdbc.Connection con = null;


                ArrayList<UserDriver> driver = new ArrayList<>();
                org.json.simple.JSONArray jArray = (org.json.simple.JSONArray) obj.get("prefDriverList");
                if (jArray != null) {
                    for (int i = 0; i < jArray.size(); i++) {
                        String user_username = ((JSONObject) jArray.get(i)).get("userUsername").toString();
                        String user_name = ((JSONObject) jArray.get(i)).get("userName").toString();
                        int userid = Integer.parseInt(((JSONObject) jArray.get(i)).get("userId").toString());
                        driver.add(new UserDriver(userid, user_name, user_username));
                    }
                }
                //List<UserDriver> driver = (List<UserDriver>) obj.get("prefDriverList");
                System.out.println(driver.size() + " ini array of user driver");
                for (UserDriver x : driver) {
                    if (x.getUserId() == userId) {
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        con = (com.mysql.jdbc.Connection) DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/ojekonline", "root", "");
                        String queryRating = "SELECT COUNT(rating) AS votes, SUM(rating)/COUNT(rating) AS ratings FROM orders WHERE driver_id = '" + x.getUserId() + "';";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(queryRating);
                        int vote = 0;
                        float rating = 0;
                        while (rs.next()) {
                            rating = rs.getFloat("ratings");
                            vote = rs.getInt("votes");
                        }
                        driverOjek.setRating(rating);
                        driverOjek.setVote(vote);
                        rs.close();
                        stmt.close();
                        con.close();
                    }
                }
            }
            System.out.println("status not good");
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return driverOjek;
    }
}