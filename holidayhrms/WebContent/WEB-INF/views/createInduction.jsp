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
   
   <style>
        

        .container {
            width: 400px;
            padding: 20px;
            background-color: #f4f4f4;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin: 0 auto;
        }

        h1 {
            text-align: center;
            margin-top: 0;
        }

        label {
            font-weight: bold;
        }

        select, input[type="text"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        select[multiple] {
            height: 100px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
    </style>
   
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