<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.time.LocalDate" %>
<%@page import="models.Candidate" %>
<%@page import="models.Employee" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" type="text/css" href="./css/candidateOfferForm.css">
     <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script src="./js/candidateOfferForm.js"></script>
</head>
<body>


<form action="email" method="POST" >

  <h1>New Offer Letter</h1>
           <%  Candidate candidate = (Candidate)request.getAttribute("candidate"); %> 
           <%  Employee hr = (Employee)request.getAttribute("hr"); %> 
           <%  List <String> listOfDocuments =(List<String>) request.getAttribute("listOfDocuments"); %> 


 <div class="form-group">
    <label for="candidate-name">Candidate Name:</label>
<input type="hidden" id="candidateId" name="candidateId" value="<%= candidate.getCandId() %>" readonly>
  </div>
  

   <div class="form-group">
    <label for="candidate-name">Candidate Name:</label>
<input type="text" id="candidateName" name="candidateName" value="<%= candidate.getCandFirstName() + candidate.getCandLastName() %>" readonly>
  </div>
<div class="form-group">
    <label for="hr-mobile">Admin Mobile:</label>
    <input type="text" id="adminMobile" name="adminMobile" value="<%= hr.getEmplMobile()%>" readonly>
  </div>

  <div class="form-group">
    <label for="hr-email">Admin Email:</label>
    <input type="email" id="adminEmail" name="adminEmail" value="<%= hr.getEmplOffemail() %>"readonly>
  </div>

  <div class="form-group">
    <label for="offer-date">Offer Date:</label>
    <input type="text" id="offerDate" name="offerDate" value="<%= LocalDate.now().toString() %>" readonly>
  </div>

  <div class="form-group">
    <label for="offered-job">Offered Job:</label>
    <input type="text" id="offeredJob" name="offeredJob">
  </div>

  <div class="form-group">
    <label for="reporting-date">Reporting Date:</label>
    <input type="date" id="reportingDate" name="reportingDate">
  </div>

  

 

  <div id="components-container">
    <!-- Components will be dynamically added here -->
  </div>

<button class="add-component-button" onclick="addComponent(); event.preventDefault()">Add Component</button>
  <br>
  



  <button class="create-button" onclick="displaySelectedOptions()">Create</button>
</form>

</body>
</html>