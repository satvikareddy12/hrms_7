<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.Employee" %>
    
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
  
   <%
        Employee employee = (Employee) request.getAttribute("employee");
        String error = (String) request.getAttribute("error");
        
        if (employee != null) {
    %>
    <label for="employee-id">Employee ID:</label>
    <input type="text" id="id" name="id" value=<%=employee.getEmplId()%>>
   

    <label for="employee-name">Employee Name:</label>
    <input type="text" id="name" name="name" value=<%=employee.getEmplFirstname()+" "+employee.getEmplLastname()%>required>

    <label for="designation">Designation:</label>
    <input type="text" id="designation" name="designation" value=<%=employee.getEmplDesignation() %> required>
	
<label for="basic-pay">Basic Pay:</label>
<input type="number" id="basicPay" name="basicPay" value=<%=employee.getEmpl_basicsal() %> required>

<label for="fixed-pay">Fixed pay:</label>
<input type="number" id="fixedPay" name="fixedPay" value=<%=employee.getEmpl_fixedsal() %> required>

<label for="variable-pay">Variable Pay:</label>
<input type="number" id="variablePay" name="variablePay" value=<%=employee.getEmpl_variablesal() %> required>
<%
        } else if (error != null) {
    %>
            <p class="error-message"><%= error %></p>
    <%
        }
    %>

<label for="earned-leave">Earned Leaves:</label>
<input type="number" id="earnedLeave" name="earnedLeave" required>

<label for="gratuity">Gratuity:</label>
<input type="number" id="gratuity" name="gratuity" required>

<label for="health-insurance">Health Insurance:</label>
<input type="number" id="healthInsurance" name="healthInsurance" required>

<label for="pf">PF:</label>
<input type="number" id="pf" name="pf" required>

<label for="unpaid-leaves">Unpaid Leaves:</label>
<input type="number" id="unpaidLeave" name="unpaidLeave" value="0" required>
<br>
<input type="submit" value="Generate Pay Slip">
</form>
  </body>
</html>

</html>