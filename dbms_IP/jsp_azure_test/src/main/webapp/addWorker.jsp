<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Worker</title>
</head>
    <body>
    <%@page import="jsp_azure_test.DataHandler"%>
    <%@page import="java.sql.ResultSet"%>
    <%@page import="java.sql.Array"%>
    <%
    // The handler is the one in charge of establishing the connection.
    DataHandler handler = new DataHandler();

    // Get the attribute values passed from the input form.
    String wname = request.getParameter("wname");
    String waddress = request.getParameter("waddress");
    String wsalary = request.getParameter("wsalary");
    String max_no_products = request.getParameter("max_no_products");

    /*
     * If the user hasn't filled out all the time, movie name and duration. This is very simple checking.
     */
       
       // Now perform the query with the data from the form.
       boolean success = handler.addWorker(wname, waddress, wsalary, max_no_products);
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
