<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Employee</h1>
<style>
    /* Add some basic styles */
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
    }

    .container {
      display: flex;
      height: 100%;
    }

    .container1 {
      flex: 1;
      display: flex;
      flex-direction: column;
    }

    .container2 {
      flex: 1;
    }

    .sub-container {
      flex: 1;
      margin-bottom: 20px;
    }

    .sub-container1 {
      display: flex;
      flex-direction: column;
    }

    .sub-container2 {
      display: flex;
      flex-direction: column;
      justify-content: space-between;
    }

    p {
      margin-bottom: 10px;
    }
  </style>
</head>
<body>
  <h2>Pay Slip</h2>

  <div class="container">
    <div class="container1">
      <div class="sub-container sub-container1">
      <h2>Details</h2>
        <p><strong>Employee ID:</strong><%=request.getAttribute("id") %> </p>
        <p><strong>Employee Name:</strong><%=request.getAttribute("name")  %> </p>
        <p><strong>Designation:</strong><%=request.getAttribute("dest")  %> </p>
        <p><strong>Basic Pay:</strong><%=request.getAttribute("basicPay")  %> </p>
        <p><strong>HRA:</strong><%=request.getAttribute("hra")  %> </p>
        <p><strong>TA:</strong><%=request.getAttribute("ta")  %></p>
        <p><strong>Variable Pay:</strong><%=request.getAttribute("variablePay")  %> </p>
        <p><strong>Earned Leaves:</strong><%=request.getAttribute("earnedLeave")  %> </p>
      </div>
      <div class="sub-container sub-container2">
      <h2>Deductions</h2>
        <p><strong>Gratuity:</strong><%=request.getAttribute("gratuity")  %> </p>
        <p><strong>Health Insurance:</strong><%=request.getAttribute("healthInsurance")  %> </p>
        <p><strong>PF:</strong><%=request.getAttribute("pf")  %> </p>
        <p><strong>Extra Leaves:</strong><%=request.getAttribute("unpaidLeave")  %> </p>
      </div>
    </div>
    <div class="container2"><h2>Pay</h2>
      <p><strong>Gross Pay:</strong><%=request.getAttribute("gp")%> </p>
      <p><strong>Deductions:</strong> 	<%=request.getAttribute("deduction") %></p>
      <p><strong>Net Pay:</strong><%=request.getAttribute("total") %></p>
    </div>
  </div>



		
</body>
</html>