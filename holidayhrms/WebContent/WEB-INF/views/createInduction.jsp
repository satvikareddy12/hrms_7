<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="models.HrmsEmploymentOffer" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Induction</title>
   
	 <link rel="stylesheet" type="text/css" href="./css/createInduction.css">

   
</head>
<body>
    <div class="container">
        <h1>Create Induction</h1>

        <form action="inductionsave" method="post">
			<label for="IndcId">Induction ID:</label>
        <select id="IndcId" name="IndcId" required>
    <option value="same">Same</option>
    <option value="next">Next</option>
</select>

            <label for="IndcEmofId">Employee Offers:</label>
          <select id="IndcEmofId" name="IndcEmofId" multiple required>
                <% List<Integer> employmentOffers = (List<Integer>) request.getAttribute("employmentOffers"); %>
                <% if (employmentOffers != null && !employmentOffers.isEmpty()) { %>
                    <% for (Integer offer : employmentOffers) { %>
                        <option value="<%= offer %>"><%= offer %></option>
                    <% } %>
                <% } %>
            </select>

            <%
                // Create a SimpleDateFormat object with the desired date format
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                // Get the current date
                Date currentDate = new Date();

                // Format the current date as a string
                String formattedDate = dateFormat.format(currentDate);
                
                int user = 123;
            %>
          <label for="inductionDate">Induction Date:</label>
			<input type="text" id="inductionDate" name="IndcDate" value="<%= formattedDate %>" readonly>


            <label for="authorizedId">Authorized ID:</label>
			<input type="text" id="authorizedId" name="IndcProcessedAusrId" value="<%= user %>" required>

            <label for="IndcStatus">Status:</label>
              <select id="IndcStatus" name="IndcStatus" required>
   			 <option value="PCMP">PCMP</option>
    	    <option value="SUPD">SUPD</option>
   			 <option value="CMPD">CMPD</option>
</select>
            <input type="submit" value="Save">
        </form>
    </div>
   
</body>
</html>