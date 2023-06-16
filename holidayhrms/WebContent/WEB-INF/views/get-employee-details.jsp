<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="models.Employee" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Details</title>
     <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        h2 {
            color: #333;
        }

        img {
            max-width: 200px;
            max-height: 200px;
            border-radius: 50%;
            margin-bottom: 20px;
            display: block;
            margin-left: auto;
            margin-right: auto;
            margin-top: 0;
        }

        .details {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .details p {
            margin-bottom: 10px;
        }

        .details h2 {
            margin-top: 0;
        }

        .error-message {
            color: #ff0000;
            font-weight: bold;
        }
        button.back-button {
    		 background-color: #4CAF50;
             color: white;
             border: none;
             padding: 10px 20px;
             margin-top: 10px;
             cursor: pointer;
             border-radius: 3px;
		}		
    </style>
     <script>
    function goBack() {
      window.history.back();
    }
    </script>
</head>
<body>
    <!-- Other HTML code... -->
    <% Employee employee = (Employee) request.getAttribute("employee"); 
       String imagePath = request.getContextPath() + "/";
       String employeeId = request.getParameter("id"); %>
    <% if (employee != null) { %>
        <img src="<%= imagePath + employee.getEmplPhoto() %>" alt="Employee Photo">
        <div class="details">
            <h2>Employee Details for Employee ID: <%= employee.getEmplId() %></h2>
            <p>Employee ID: <%= employee.getEmplId() %></p>
            <p>First Name: <%= employee.getEmplFirstname() %></p>
            <p>Last Name: <%= employee.getEmplLastname() %></p>
            <p>Surname: <%= employee.getEmplSurname() %></p>
            <p>Reporting Manager ID: <%= employee.getEmplRmanagerEmplId() %></p>
            <p>HR Manager ID: <%= employee.getEmplHrEmplId() %></p>
            <p>Job Group ID: <%= employee.getEmplJbgrId() %></p>
            <p>Join Date: <%= employee.getEmplJondate() %></p>
            <p>Date of Birth: <%= employee.getEmplDob() %></p>
            <p>Designation: <%= employee.getEmplDesignation() %></p>
            <p>Official Email: <%= employee.getEmplOffemail() %></p>
            <p>Personal Email: <%= employee.getEmplPemail() %></p>
            <p>Mobile: <%= employee.getEmplMobile() %></p>
            <p>Alternate Email: <%= employee.getEmplAlemail() %></p>
            <p>Blood Group: <%= employee.getEmplBloodgroup() %></p>
            <p>Gender: <%= employee.getEmplGender() %></p>
            <p>Address: <%= employee.getEmplAddress() %></p>
            <p>Father's Name: <%= employee.getEmplFname() %></p>
            <p>Last Update Date: <%= employee.getEmplLuudate() %></p>
            <p>Last Update User ID: <%= employee.getEmplLuuser() %></p>
            <p>Employee CTC: <%= employee.getEmpl_ctc() %></p>
            <p>Basic Salary: <%= employee.getEmpl_basicsal() %></p>
            <p>Fixed Salary: <%= employee.getEmpl_fixedsal() %></p>
            <p>Variable Salary: <%= employee.getEmpl_variablesal() %></p>
            <p>Status: <%= employee.getEmpl_status() %></p>
            <button class="back-button" onclick="goBack()">Go Back</button>
        </div>
    <% } else { %>
        <p>No employee found with ID <%= employeeId %></p>
    <% } %>
</body>
</html>
