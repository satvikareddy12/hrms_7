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
    <form action="employeeListDelete" method="post">
        <label for="emplId">Employee ID:</label>
        <input type="text" name="emplId" id="emplId" required><br>
        <input type="submit" value="Submit">
    </form>
      <div id="employeeList">
        <!-- Employee list will be dynamically updated here -->
    </div>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script>
        function loadEmployeeList() {
    	$.ajax({
        	url: "employeeListDelete", 
        	method: "GET",
        	success: function(response) {
            	$("#employeeList").html(response);
        	},
        	error: function(xhr, status, error) {
            	console.log("Error loading employee list: " + error);
        	}
    	});
	}
    loadEmployeeList();
    setInterval(loadEmployeeList, 2000);
    </script>
</body>
</html>