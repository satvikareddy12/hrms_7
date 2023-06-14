<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Retrieve Employee ID</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
        }
        
        h1 {
            color: #333;
        }
        
        form {
            max-width: 300px;
            margin-top: 20px;
        }
        
        label {
            display: block;
            margin-top: 10px;
        }
        
        input[type="text"] {
            width: 100%;
            padding: 5px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            margin-top: 10px;
            cursor: pointer;
            border-radius: 3px;
        }
    </style>

</head>
<body>
    <h1>Enter Employee ID</h1>
    <form action="delete" method="post">
        <label for="emplId">Employee ID:</label>
        <input type="text" name="emplId" id="emplId" required><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>