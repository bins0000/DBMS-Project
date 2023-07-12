<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
        <title>Employees With High Salary</title>
    </head>
    <body>
        <%@page import="jsp_azure_test.DataHandler"%>
        <%@page import="java.sql.ResultSet"%>
        
        <%
     	
     	// The handler is the one in charge of establishing the connection.
        DataHandler handler = new DataHandler();
        
        // Get the attribute values passed from the input form.
        String str_salary = request.getParameter("q12_salary");
        
        float salary = Integer.parseInt(str_salary);
        
        ResultSet resultSet = handler.getEmployee(salary);
        
        %>
        

        <!-- The table for displaying all the movie records -->
        <table cellspacing="2" cellpadding="2" border="1">
            <tr> <!-- The table headers row -->
              <td align="center">
                <h4>Employee Name</h4>
              </td>
            </tr>
            <%
               while(resultSet.next()) { // For each movie_night record returned...
                   // Extract the attribute values for every row returned
                   final String name = resultSet.getString("name");
                   
                   
                   out.println("<tr>"); // Start printing out the new table row
                   out.println( // Print each attribute value
                        "<td align=\"center\">" + name) ;
                   out.println("</tr>");
               }
               %>
          </table>
    </body>
</html>