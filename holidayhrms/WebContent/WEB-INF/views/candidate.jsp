<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Enter Candidate Details</title>
 <link rel="stylesheet" type="text/css" href="./css/candidate.css">
     <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script src="./js/candidate.js"></script>
    
</head>
<body>
    <h1>Enter Candidate Details</h1>
    <form method="post" action="candidateadded">
        <label for="candFirstName">First Name:</label>
        <input type="text" name="candFirstName" id="candFirstName" required><br><br>
        
        <label for="candMiddleName">Middle Name:</label>
        <input type="text" name="candMiddleName" id="candMiddleName"><br><br>
        
        <label for="candLastName">Last Name:</label>
        <input type="text" name="candLastName" id="candLastName" required><br><br>
        
        <label for="candRDate">Registration Date:</label>
        <input type="date" name="candRDate" id="candRDate" required><br><br>
        
        <label for="candGender">Gender:</label>
        <input type="text" name="candGender" id="candGender" required><br><br>
        
        <label for="candDOB">Date of Birth:</label>
        <input type="date" name="candDOB" id="candDOB" required><br><br>
        
        <label for="candEmail">Email:</label>
        <input type="text" name="candEmail" id="candEmail" required><br><br>
        
        <label for="candMobile">Mobile Number:</label>
        <input type="text" name="candMobile" id="candMobile" required><br><br>
        
        <label for="candAddress">Address:</label>
        <textarea name="candAddress" id="candAddress" rows="3" required></textarea><br><br>
        
        <label for="candLUDate">Last Update Date:</label>
        <input type="date" name="candLUDate" id="candLUDate" required><br><br>
        
        <label for="candStatus">Status:</label>
        <input type="text" name="candStatus" id="candStatus" required><br><br>
        
        <input type="submit" value="Submit">
    </form>
    
     
</body>
</html>
