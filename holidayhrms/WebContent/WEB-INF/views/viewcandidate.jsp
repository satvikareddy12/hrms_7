<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="models.input.output.CandidateIO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Candidate Details</title>
     <link rel="stylesheet" type="text/css" href="./css/viewCandidate.css">
    
</head>
<body>
    <h1>Candidate Details</h1>
    <%
    CandidateIO candidate = (CandidateIO) request.getAttribute("candidate");
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
