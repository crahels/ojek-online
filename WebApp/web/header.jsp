<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.DataOutputStream" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page import="example.IOException_Exception" %>
<%@ page import="example.ParseException_Exception" %>
<%@ page import="example.HelloWorldService" %>
<%@ page import="example.HelloWorld" %>
<%@ page import="static java.awt.SystemColor.window" %>
<%--
  Created by IntelliJ IDEA.
  User: MARCELLINO
  Date: 05/11/2017
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession sesion = request.getSession();

    String tokenn = sesion.getAttribute("token").toString();
    HelloWorldService servis = new HelloWorldService();
    HelloWorld prt = servis.getHelloWorldPort();
    try {
        if (prt.expiryTime(tokenn) == 0) { // invalid
            out.print("<script>alert('INVALID ACCESS');" +
                    "window.location = 'login.jsp';</script>");
            //response.sendRedirect("login.jsp");
        } else if (prt.expiryTime(tokenn) == 2) { // expired
            out.print("<script>alert('EXPIRED ACCESS');" +
                    "window.location = 'login.jsp';</script>");
            //response.sendRedirect("login.jsp");
        }
    } catch (IOException_Exception e) {
        e.printStackTrace();
    } catch (ParseException_Exception e) {
        e.printStackTrace();
    }

    String usernamee = sesion.getAttribute("username").toString();
    if(request.getParameter("logout") != null) {
        String USER_AGENT = "Mozilla/5.0";
        String url = "http://localhost:8001/logout";
        URL connection = new URL(url);
        HttpURLConnection con = (HttpURLConnection) connection.openConnection();

        //add request header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "token="+tokenn;

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder resp = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            resp.append(inputLine);
        }
        in.close();
        con.disconnect();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(resp.toString());
        String status= (String) obj.get("status");

        if(status.equals("ok")){
            response.sendRedirect("login.jsp");
        }
        else {
            out.println(status);
            out.println("<script>alert('Logout Failed')</script>");
            response.sendRedirect("login.jsp");
        }
    }
%>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div class="container">
        <table width="100%">
            <tr>
                <td>
                    <h1 class="left"><span class="header-title-1">PR</span>-<span class="header-title-2">OJEK</span></h1>
                </td>

                <td>
                    <p class="hi-username">Hi, <span class="bold"><% out.print(usernamee); %></span> !</p>
                </td>
            </tr>
            <tr>
                <td>
                    <p class="subtitle left">wushh... wushh... ngeeeeeenggg...</p>
                </td>
                <td>
                    <a class="logout right" href="header.jsp?logout=1">Logout</a>
                </td>
            </tr>
        </table>
        <ul class="nav-bar">
            <li>
                <a <% if (application.getAttribute("currentPage") == "order") {
                    out.print("class='active'");
                }
                %>
                    href="order_gojek.jsp">Order</a></li>
            <li>
                <a <% if (application.getAttribute("currentPage") == "history") {
                    out.print("class='active'");
                }%>
                    href="my_previous_order.jsp">History</a></li>
            <li>
                <a <% if (application.getAttribute("currentPage") == "profile") {
                    out.print("class='active'");
                }%>
                    href="profile.jsp">My Profile</a></li>
        </ul>
    </div>
</body>
</html>