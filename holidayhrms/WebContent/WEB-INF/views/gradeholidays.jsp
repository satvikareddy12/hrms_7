<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@page import="models.GradeHoliday" %>
<%@ page import="java.util.List" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List of Holidays</title>
     <link rel="stylesheet" type="text/css" href="./css/gradeHolidays.css">
   
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