<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Select Payslips by Month</title>
    <style>
       
        h2 {
            text-align: center;
            color: #333;
        }

        form {
            width: 300px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 10px;
            color: #333;
        }

        select {
            width: 100%;
            padding: 5px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            padding: 10px 20px;
            background-color: #009933;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #008026;
        }
    </style>
</head>
<body>
    <h2>Select Payslips by Month</h2>
    <form action="EmployeeSidePaySlip" method="post">
        <label for="month">Select Month:</label>
 		<select id="month" name="month">
            <option value="1">January</option>
            <option value="2">February</option>
            <option value="3">March</option>
          	<option value="4">April</option>
          	<option value="5">May</option>
          	<option value="6">June</option>
          	<option value="7">July</option>
          <option value="8">August</option>
          <option value="9">September</option>
          <option value="10">October</option>
          <option value="11">November</option>
          <option value="12">December</option>
            <!-- Add more options for each month -->
        </select>
        <br><br>
        <input type="submit" value="View Payslips">
    </form>
</body>
</html>
