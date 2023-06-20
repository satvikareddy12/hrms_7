<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
    /* Add some basic styles */
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
    }

    label {
      display: block;
      margin-bottom: 5px;
    }

    input[type="text"],
    input[type="number"] {
      width: 200px;
      padding: 5px;
      margin-bottom: 10px;
    }

    input[type="submit"] {
      padding: 10px 20px;
      background-color: #4CAF50;
      color: white;
      border: none;
      cursor: pointer;
    }
  </style>
</head>
<body>
  <h1>Monthly Pay Slip</h1>

  <form action="getpayslip" method="get">
    <label for="employee-id">Employee ID:</label>
    <input type="text" id="employee-id" name="employee-id" required>

    <label for="employee-name">Employee Name:</label>
    <input type="text" id="employee-name" name="employee-name" required>

    <label for="designation">Designation:</label>
    <input type="text" id="designation" name="designation" required>
	
<label for="basic-pay">Basic Pay:</label>
<input type="number" id="basic-pay" name="basic-pay" required>

<label for="hra">HRA:</label>
<input type="number" id="hra" name="hra" required>

<label for="ta">TA:</label>
<input type="number" id="ta" name="ta" required>

<label for="variable-pay">Variable Pay:</label>
<input type="number" id="variable-pay" name="variable-pay" required>

<label for="earned-leave">Earned Leaves:</label>
<input type="number" id="earned-leave" name="earned-leave" required>

<label for="gratuity">Gratuity:</label>
<input type="number" id="gratuity" name="gratuity" required>

<label for="health-insurance">Health Insurance:</label>
<input type="number" id="health-insurance" name="health-insurance" required>

<label for="pf">PF:</label>
<input type="number" id="pf" name="pf" required>

<label for="unpaid-leaves">Unpaid Leaves:</label>
<input type="number" id="unpaid-leaves" name="unpaid-leaves" required>
<br>
<input type="submit" value="Generate Pay Slip">
</form>
  </body>
</html>

</html>