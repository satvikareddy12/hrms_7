<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.List" %>
<%@ page import="models.input.output.EmployeeParameterIO" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Parameters</title>
   <link rel="stylesheet" type="text/css" href="./css/employeeparameter.css">
 
</head>
<body>
<h1>Employee Parameters</h1>

<%
    List<EmployeeParameterIO> employeeParameters = (List<EmployeeParameterIO>) request.getAttribute("employeeParameters");
%>

<table>
    <tr>
        <th>Employee ID</th>
        <th>Employer ID</th>
        <th>Employer Name</th>
        <th>Employer Value</th>
        <th>Last Updated Date</th>
        <th>Last Updated User</th>
    </tr>

    <% for (EmployeeParameterIO employeeParam : employeeParameters) { %>
        <tr>
            <td><%= employeeParam.getEmployeeId() %></td>
            <td><%= employeeParam.getParameterId() %></td>
            <td><%= employeeParam.getParameterName() %></td>
            <td><%= employeeParam.getParameterValue() %></td>
            <td><%= employeeParam.getLastUpdatedDate() %></td>
            <td><%= employeeParam.getLastUpdatedUser() %></td>
        </tr>
    <% } %>
</table>

<%
    if (employeeParameters.isEmpty()) {
%>
    <p>No employee parameters found for the provided ID.</p>
<%
    }
%>
</body>
</html>
