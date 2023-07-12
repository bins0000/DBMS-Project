<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add Employee</title>
    </head>
    <body>
        <h2>Add Employee</h2>
        <!--
            Form for collecting user input for the new employee record.
            Upon form submission, addEmployee.jsp file will be invoked.
        -->
        <form action="addWorker.jsp">
            <!-- The form organized in an HTML table for better clarity. -->
            <table border=1>
                <tr>
                    <th colspan="2">Enter Worker's Data:</th>
                </tr>
                <tr>
                    <td>Worker's Name:</td>
                    <td><div style="text-align: center;">
                    <input type=text name=wname>
                    </div></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><div style="text-align: center;">
                    <input type=text name=waddress>
                    </div></td>
                </tr>
                <tr>
                    <td>Salary:</td>
                    <td><div style="text-align: center;">
                    <input type=text name=wsalary>
                    </div></td>
                </tr>
                <tr>
                    <td>Max number of product this worker can produce in a day:</td>
                    <td><div style="text-align: center;">
                    <input type=text name=max_no_products>
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
        
        
        <form action="addQualityController.jsp">
            <!-- The form organized in an HTML table for better clarity. -->
            <table border=1>
                <tr>
                    <th colspan="2">Enter Quality Controller's Data:</th>
                </tr>
                <tr>
                    <td>Quality Controller's Name:</td>
                    <td><div style="text-align: center;">
                    <input type=text name=qname>
                    </div></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><div style="text-align: center;">
                    <input type=text name=qaddress>
                    </div></td>
                </tr>
                <tr>
                    <td>Salary:</td>
                    <td><div style="text-align: center;">
                    <input type=text name=qsalary>
                    </div></td>
                </tr>
                <tr>
                    <td>Type of product this quality controller can inspect:</td>
                    <td><div style="text-align: center;">
                    <input type=text name=qtype>
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
        
        <form action="addTechnicalStaff.jsp">
            <!-- The form organized in an HTML table for better clarity. -->
            <table border=1>
                <tr>
                    <th colspan="2">Enter Technical Staff's Data:</th>
                </tr>
                <tr>
                    <td>Technical Staff's Name:</td>
                    <td><div style="text-align: center;">
                    <input type=text name=tname>
                    </div></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><div style="text-align: center;">
                    <input type=text name=taddress>
                    </div></td>
                </tr>
                <tr>
                    <td>Salary:</td>
                    <td><div style="text-align: center;">
                    <input type=text name=tsalary>
                    </div></td>
                </tr>
                <tr>
                    <td>Highest Degree (BS, MS, PhD):</td>
                    <td><div style="text-align: center;">
                    <input type=text name=highest_degree>
                    </div></td>
                </tr>
                <tr>
                    <td>Technical Position:</td>
                    <td><div style="text-align: center;">
                    <input type=text name=tech_position>
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
