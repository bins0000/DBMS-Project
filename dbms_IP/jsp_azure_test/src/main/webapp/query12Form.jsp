<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Retrieve Employee</title>
    </head>
    <body>
        <h2>Retrieve all employees whose salary is above a particular salary</h2>
        <!--
            Form for collecting user input for the new employee record.
            Upon form submission, addEmployee.jsp file will be invoked.
        -->
        <form action="query12.jsp">
            <!-- The form organized in an HTML table for better clarity. -->
            <table border=1>
                <tr>
                    <th colspan="2">Enter Salary</th>
                </tr>
                <tr>
                    <td>Salary (without '$' sign):</td>
                    <td><div style="text-align: center;">
                    <input type=text name=q12_salary>
                    </div></td>
                </tr>
                
                <tr>
                    <td><div style="text-align: center;">
                    <input type=reset value=Clear>
                    </div></td>
                    <td><div style="text-align: center;">
                    <input type=submit value=Insert>
                    </div></td>
                </tr>
            </table>
        </form>
        
    </body>
</html>
