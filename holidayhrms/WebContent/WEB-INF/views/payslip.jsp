<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  %>
<%@ page import="models.input.output.EmployeePayRollOutputModel" %>
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
  <% EmployeePayRollOutputModel pay = (EmployeePayRollOutputModel) request.getAttribute("pay");
      %>
  <div class="container">
    <div class="container1">
      <div class="sub-container sub-container1">
      <h2>Details</h2>
        <p><strong>Employee ID:</strong><%=pay.getId() %> </p>
        <p><strong>Employee Name:</strong><%=pay.getName()  %> </p>
        <p><strong>Designation:</strong><%=pay.getDesignation()  %> </p>
        <p><strong>Basic Pay:</strong><%=pay.getBasicPay() %> </p>
        <p><strong>HRA:</strong><%=pay.getHra() %> </p>
        <p><strong>Special Allowances:</strong><%=pay.getTa()%></p>
        <p><strong>Variable Pay:</strong><%=pay.getVariablePay()%> </p>
      </div>
      <div class="sub-container sub-container2">
      <h2>Deductions</h2>
        <p><strong>Gratuity:</strong><%=pay.getGratuity()%> </p>
        <p><strong>Health Insurance:</strong><%=pay.getHealthInsurance()%> </p>
        <p><strong>PF:</strong><%=pay.getPf()%> </p>
        <p><strong>Extra Leaves:</strong><%=pay.getUnpaidLeave()%> </p>
      </div>
    </div>
    <div class="container2"><h2>Pay</h2>
      <p><strong>Gross Pay:</strong><%=pay.getGp()%> </p>
      <p><strong>Deductions:</strong> 	<%=pay.getDeduction()%></p>
      <p><strong>Net Pay:</strong><%=pay.getTotal()%></p>
    </div>
  </div>



		
</body>
</html>