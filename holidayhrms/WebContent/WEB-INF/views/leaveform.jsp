<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Leave Form</title>
</head>
<body>
 <div class="form-container">
        <h1>Leave Page</h1>
        <form action="create" method="post">
            <label for="employeeId">Employee ID:</label>
            <input type="text" id="employeeId" name="employeeId"><br><br>
            
            <label for="requestDate">Request Date:</label>
            <input type="date" id="requestDate" name="requestDate"><br><br>
            
            <label for="leaveType">Leave Type:</label>
            <select id="leaveType" name="leaveType">
                <option value="CASL">CASL</option>
                <option value="SICK">SICK</option>
                <option value="OTHR">OTHR</option>
                <option value="FEST">FEST</option>
                <option value="TRIP">TRIP</option>
            </select><br><br>
            
            <label for="reason">Reason:</label>
            <textarea id="reason" name="reason"></textarea><br><br>
            
            <label for="leaveStartDate">Leave Start Date:</label>
            <input type="date" id="leaveStartDate" name="leaveStartDate"><br><br>
            
            <label for="leaveEndDate">Leave End Date:</label>
            <input type="date" id="leaveEndDate" name="leaveEndDate"><br><br>
               
            <input type="submit" value="Submit">
        </form>
    </div>
</body>
</html>