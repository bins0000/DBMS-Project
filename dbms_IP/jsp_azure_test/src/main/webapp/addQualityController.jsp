<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Quality Controller</title>
</head>
    <body>
    <%@page import="jsp_azure_test.DataHandler"%>
    <%@page import="java.sql.ResultSet"%>
    <%@page import="java.sql.Array"%>
    <%
    // The handler is the one in charge of establishing the connection.
    DataHandler handler = new DataHandler();

    // Get the attribute values passed from the input form.
    String qname = request.getParameter("qname");
    String qaddress = request.getParameter("qaddress");
    String qsalary = request.getParameter("qsalary");
    String qtype = request.getParameter("qtype");

    /*
     * If the user hasn't filled out all the time, movie name and duration. This is very simple checking.
     */
       
       // Now perform the query with the data from the form.
       boolean success = handler.addQualityController(qname, qaddress, qsalary, qtype);
       if (!success) { // Something went wrong
           %>
               <h2>There was a problem inserting the employee</h2>
           <%
       } else { // Confirm success to the user
           %>
                    
           <h2>Employee was successfully inserted.</h2>
           
           
           <%
       }
    
    %>
    </body>
</html>
