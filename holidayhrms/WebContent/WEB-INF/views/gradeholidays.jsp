<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@page import="models.GradeHoliday" %>
<%@ page import="java.util.List" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List of Holidays</title>

    <style>
        
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
 <h1>Job Grade Wise Holidays</h1><br>
    <table>
        <thead>
            <tr>
                <th>JobGradeID</th>
                <th>Total No of Holidays</th>
            </tr>
        </thead>
        <tbody>
            <%
            List<GradeHoliday> gradeholidays = (List<GradeHoliday>) request.getAttribute("gradeholidays");
            if (gradeholidays != null) {
                for (GradeHoliday gradeholiday : gradeholidays) { %>
                    <tr>
                        <td><%= gradeholiday.getJbgr_id() %></td>
                        <td><%= gradeholiday.getJbgr_totalnoh()%></td>
                    </tr>
                <% }
            } %>
        </tbody>
    </table>
</body>
</html>