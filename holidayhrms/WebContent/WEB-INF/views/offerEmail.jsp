<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="models.Candidate" %>
<%@page import="models.OfferModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Offer Letter</title>
 <link rel="stylesheet" type="text/css" href="./css/offerEmail.css">
     <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script src="./js/offerEmail.js"></script>
</head>
<body>

 <% OfferModel offerModel = (OfferModel) request.getAttribute("offerModel"); %>
   
Dear <%= offerModel.getCandidateName() %>,

We are delighted to offer you employment for the position of "<%= offerModel.getOfferedJob() %>". As part of this offer, you will be required to undergo a Pre-Employment Internship . During this internship, you will receive comprehensive training in various domains, including technical, functional, and product framework domains. 

Upon successful completion of your training and assessment, your allocation to the compensation packages will be determined based on your performance.

Further Information will be provided after successful join into our organisation.


With best wishes and looking forward to a mutually fruitful association,



<h2>Details:</h2>
<p>HR Mobile Number: <%= offerModel.getAdminMobile() %></p>
<p>HR Email: <%= offerModel.getAdminEmail() %></p>
<p>Offer Date: <%= offerModel.getOfferDate() %></p>
<p>Offered Job: <%= offerModel.getOfferedJob() %></p>
<p>Reporting Date: <%= offerModel.getReportingDate() %></p>
<p>Documents to Bring While Coming:</p>
Please note that the information provided in this offer letter is subject to the specific details and terms mentioned in the formal letter of appointment that you will receive upon joining our organization.
      <ul>
        <c:forEach var="document" items="${offerModel.getDocuments()}">
            <li>${document}</li>
        </c:forEach>
    </ul>
<div class="button-container">
    <form id="sendOfferForm" action="sendOfferLetter" method="post">
        <button id="sendOfferButton" class="send-button" type="submit" onclick="openPopup(event)">Send Offer Letter via Email</button>
    </form>
</div>

<div class="popup" id="popup">
    <img src="tick.png">
    <h2>Thank You!</h2>
    <p>Email has been successfully sent</p> 
    <button type="button" onclick="closePopup()">OK</button>
</div>




    
</body>
</html>