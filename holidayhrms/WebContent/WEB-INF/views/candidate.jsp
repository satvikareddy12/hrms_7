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
        <label for="firstName">First Name:</label>
        <input type="text" name="firstName" id="firstName" required><br><br>
        
        <label for="middleName">Middle Name:</label>
        <input type="text" name="middleName" id="middleName"><br><br>
        
        <label for="lastName">Last Name:</label>
        <input type="text" name="lastName" id="lastName" required><br><br>
        
        <label for="rDate">Registration Date:</label>
        <input type="date" name="rDate" id="rDate" required><br><br>
        
        <label for="gender">Gender:</label>
        <input type="text" name="gender" id="gender" required><br><br>
        
        <label for="dob">Date of Birth:</label>
        <input type="date" name="dob" id="dob" required><br><br>
        
        <label for="email">Email:</label>
        <input type="text" name="email" id="email" required><br><br>
        
        <label for="mobile">Mobile Number:</label>
        <input type="text" name="mobile" id="mobile" required><br><br>
        
        <label for="address">Address:</label>
        <textarea name="address" id="address" rows="3" required></textarea><br><br>
        
        <label for="ludate">Last Update Date:</label>
        <input type="date" name="ludate" id="ludate" required><br><br>
        
        <label for="status">Status:</label>
        <input type="text" name="status" id="status" required><br><br>
        
        <input type="submit" value="Submit">
    </form>
</body>
</html>
