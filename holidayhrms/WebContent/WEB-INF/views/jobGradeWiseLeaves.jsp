<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Job Grade Leaves</title>
 <link rel="stylesheet" type="text/css" href="./css/jobGradeWiseLeaves.css">
  
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