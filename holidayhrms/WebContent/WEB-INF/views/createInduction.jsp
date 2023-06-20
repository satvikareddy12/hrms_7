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
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            width: 400px;
            padding: 20px;
            background-color: #f4f4f4;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            margin-top: 0;
        }

        label, input, select {
            display: block;
            margin-bottom: 10px;
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

        <form action="/documents/add" method="post">
            <label for="inductionId">Induction ID:</label>
            <input type="text" id="inductionId" name="inductionId" required>

            <label for="employeeOffers">Employee Offers:</label>
          <select id="employeeOffers" name="employeeOffers" multiple required>
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
            %>
            <label for="inductionDate">Induction Date:</label>
            <input type="text" id="inductionDate" name="inductionDate" value="<%= formattedDate %>" readonly>

            <label for="authorizedId">Authorized ID:</label>
            <input type="text" id="authorizedId" name="authorizedId" required>

            <label for="status">Status:</label>
            <input type="text" id="status" name="status" required>

            <input type="submit" value="Save">
        </form>
    </div>
</body>
</html>
