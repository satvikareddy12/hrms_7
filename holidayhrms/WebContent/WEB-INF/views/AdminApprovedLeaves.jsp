<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Approved Leaves</title>
    <style>
        /* Add your custom CSS styles here */
        body {
            font-family: Arial, sans-serif;
        }
        
        h1 {
            color: #333;
            text-align: center;
        }
        
        table {
            border-collapse: collapse;
            width: 100%;
            max-width: 800px;
            margin: 0 auto;
            background-color: #fff;
        }
        
        th, td {
            padding: 8px;
            text-align: left;
        }
        
        th {
            background-color: #f2f2f2;
            font-weight: bold;
            color: #333;
        }
        
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>
    <%@ page import="java.util.List, java.util.ArrayList" %>
    <%@ page import="models.ApprovedLeaveModel" %>
    
    <h1>Approved Leaves</h1>
    
    <table>
        <tr>
            <th>Employee ID</th>
            <th>Employee Name</th>
            <th>Approved Start Date</th>
            <th>Approved End Date</th>
        </tr>
        
        <% 
        List<ApprovedLeaveModel> approvedLeaves = (List<ApprovedLeaveModel>) request.getAttribute("approvedLeaves");
        
        for (ApprovedLeaveModel leave : approvedLeaves) { 
        %>
        <tr>
            <td><%= leave.getEmployeeId() %></td>
            <td><%= leave.getEmployeeName() %></td>
            <td><%= leave.getApprovedStartDate() %></td>
            <td><%= leave.getApprovedEndDate() %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>
