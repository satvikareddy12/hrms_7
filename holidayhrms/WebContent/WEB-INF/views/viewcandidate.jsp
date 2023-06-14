<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="models.Candidate" %>
<!DOCTYPE html>
<html>
<head>
    <title>Candidate Details</title>
     <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f7f7f7;
            text-align:center;
        }
        
        h1 {
            color: #333;
            text-align: center;
        }
        
        h2 {
            color: #555;
            margin-top: 20px;
        }
        
        p {
            color: #777;
            margin: 5px 0;
        }
        
        .error-message {
            color: #ff0000;
            font-weight: bold;
            margin-top: 20px;
        }
        
        .container {
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h1>Candidate Details</h1>
    <%
        Candidate candidate = (Candidate) request.getAttribute("candidate");
        String error = (String) request.getAttribute("error");
        
        if (candidate != null) {
    %>
            <h2>Candidate ID: <%= candidate.getCandId() %></h2>
            <p>First Name: <%= candidate.getCandFirstName() %></p>
            <p>Middle Name: <%= candidate.getCandMiddleName() %></p>
            <p>Last Name: <%= candidate.getCandLastName() %></p>
            <p>Registration Date: <%= candidate.getCandRDate() %></p>
            <p>Gender: <%= candidate.getCandGender() %></p>
            <p>Date of Birth: <%= candidate.getCandDOB() %></p>
            <p>Email: <%= candidate.getCandEmail() %></p>
            <p>Mobile Number: <%= candidate.getCandMobile() %></p>
            <p>Address: <%= candidate.getCandAddress() %></p>
            <p>Last Update Date: <%= candidate.getCandLUDate() %></p>
            <p>Status: <%= candidate.getCandStatus() %></p>
    <%
        } else if (error != null) {
    %>
            <p class="error-message"><%= error %></p>
    <%
        }
    %>
</body>
</html>
