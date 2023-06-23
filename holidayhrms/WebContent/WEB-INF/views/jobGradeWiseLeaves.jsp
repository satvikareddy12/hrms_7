<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Job Grade Leaves</title>
    <style>
        /* Add your custom CSS styles here */
        body {
            font-family: Arial, sans-serif;
        }
        
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 30px;
        }
        
        table {
            margin-top: 20px;
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
    <%@ page import="models.input.output.JobGradeLeavesOutModel" %>
    
    <h1>Job Grade Wise Leaves</h1>
    
    <table>
        <tr>
            <th>Job Grade ID</th>
            <th>Total Leaves</th>
            <th>Casual Leaves</th>
            <th>Sick Leaves</th>
            <th>Other Leaves</th>
        </tr>
        
        <% 
        List<JobGradeLeavesOutModel> jobGradeLeaves = (List<JobGradeLeavesOutModel>) request.getAttribute("jobgradeleaves");
        
        for (JobGradeLeavesOutModel jobGrade : jobGradeLeaves) { 
        %>
        <tr>
            <td><%= jobGrade.getJobGradeId() %></td>
            <td><%= jobGrade.getTotalLeaves() %></td>
            <td><%= jobGrade.getCasualLeaves() %></td>
            <td><%= jobGrade.getSickLeaves() %></td>
            <td><%= jobGrade.getOtherLeaves() %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>