<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Enter Candidate ID</title>
    <style>
        
        h1 {
            color: #333;
            text-align: center;
        }
        
        form {
            margin-top: 20px;
            text-align: center;
        }
        
        label {
            display: block;
            margin-bottom: 10px;
            text-align: center;
        }
        
        input[type="text"] {
            padding: 5px;
            width: 200px;
        }
        
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #333;
            color: #fff;
            border: none;
            cursor: pointer;
            margin: 0 auto;
        }
    </style>
</head>
<body>
    <h1>Enter Employee ID</h1><br>
    <form action="getpayslip" method="post">
        <label for="employeeeId">Employee ID:</label>
        <input type="text" id="empl_id" name="empl_id" required><br><br>
        <input type="submit" value="Get Details">
    </form>
</body>
</html>
