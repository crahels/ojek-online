<%--
  Created by IntelliJ IDEA.
  User: MARCELLINO
  Date: 05/11/2017
  Time: 5:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2>Make an Order</h2>
<div class="box <%
	  if(application.getAttribute("currentSubPage") == "destination") {
		  out.print("backgroundyellow");
	  }
	%>"> <div class="circle left"> 1 </div>Select Destination</div>
<div class="box <%
	  if(application.getAttribute("currentSubPage") == "driver") {
		  out.print("backgroundyellow");
	  }
	%>"> <div class="circle left"> 2 </div>Select a Driver</div>
<div class="box <%
	  if(application.getAttribute("currentSubPage") == "complete") {
		  out.print("backgroundyellow");
	  }
	%>"> <div class="circle left"> 3 </div>Complete your order</div>

