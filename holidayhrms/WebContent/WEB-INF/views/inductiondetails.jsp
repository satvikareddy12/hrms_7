<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="models.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Induction Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        
        h1 {
            color: #333;
        }
        
        table {
            border-collapse: collapse;
            width: 100%;
        }
        
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        
        th {
            background-color: #f2f2f2;
        }
        
        .no-inductions {
            color: #777;
        }
        
        button {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin-top: 20px;
            cursor: pointer;
        }
        
        button:hover {
            background-color: #45a049;
        }
    </style>
     <script>
        function goBack() {
            window.history.back();
        }
    </script>
</head>
<body>
    <% 
        List<Induction> inductions = (List<Induction>) request.getAttribute("indid");
        Integer i = (Integer) request.getAttribute("ID");
    %>
    <h1>Induction-<%= i %> Details </h1>
    
    <% if (inductions != null && !inductions.isEmpty()) { %>
        <table>
            <thead>
                <tr>
                    <th>Employee Offer ID</th>
                    <th>Date</th>
                    <th>Handled By</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <% for (Induction induction : inductions) { %>
                    <tr>
                       <td><%= induction.getIndcEmofId() %></td>
                       <td><%= induction.getIndcDate() %></td>
                       <td><%= induction.getIndcProcessedAusrId() %></td>
                       <td><%= induction.getIndcStatus() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p class="no-inductions">No inductions found.</p>
    <% } %>
        <button onclick="goBack()">Back</button>
</body>
</html>

