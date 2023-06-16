<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Enter Candidate Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        form {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #333;
        }

        input[type="text"],
        input[type="date"],
        input[type="email"],
        textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 14px;
            margin-bottom: 10px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
    
</head>
<body>
    <h1>Enter Candidate Details</h1>
    <form method="post" action="success">
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
