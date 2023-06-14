<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Leave Page</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }

        .form-container {
            width: 400px;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .form-container h1 {
            text-align: center;
        }

        .form-container label {
            display: block;
            margin-bottom: 10px;
        }

        .form-container input[type="text"],
        .form-container input[type="date"],
        .form-container textarea,
        .form-container select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .form-container input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            background-color: #4caf50;
            color: #ffffff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h1>Leave Page</h1>
        <form action="/delete" method="post">
            <label for="employeeId">Employee ID:</label>
            <input type="text" id="employeeId" name="employeeId">
            
            <label for="leaveStartDate">Leave Start Date:</label>
            <input type="date" id="leaveStartDate" name="leaveStartDate">
            
            <label for="leaveEndDate">Leave End Date:</label>
            <input type="date" id="leaveEndDate" name="leaveEndDate">
               
            <input type="submit" value="Submit">
        </form>
    </div>
</body>
</html>