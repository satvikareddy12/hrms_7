<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="models.Candidate" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List of Candidates</title>
    <style>
        h2 {
            margin-top: 20px;
        }

        table {
            margin: 0 auto;
            margin-top: 20px;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
        }

        th, td {
            padding: 10px;
        }

        th {
            background-color: #333;
            color: #fff;
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

        #filterInput {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border-radius: 5px;
            border: 1px solid #ccc;
            margin-bottom: 10px;
        }

        #tableContainer {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div id="tableContainer">
        <input type="text" id="filterInput" placeholder="Search...">

        <table id="dataTable">
            <thead>
                <tr>
                    <th>Candidate ID</th>
                    <th>Candidate First Name</th>
                    <th>Candidate Last Name</th>
                    <th>Candidate Register Date</th>
                    <th>Candidate Status</th>
                    <th>View Candidate</th>
                    <th>Generate Offer</th>
                </tr>
            </thead>
            <tbody>
                <%
                List<Candidate> candidates = (List<Candidate>) request.getAttribute("candidates");
                if (candidates != null) {
                    for (Candidate candidate : candidates) { %>
                        <tr>
                            <td><%= candidate.getCandId() %></td>
                            <td><%= candidate.getCandFirstName() %></td>
                            <td><%= candidate.getCandLastName() %></td>
                            <td><%= candidate.getCandRDate() %></td>
                            <td><%= candidate.getCandStatus() %></td>
                            <td><a class="view-link" href="viewcandidate?id=<%= candidate.getCandId() %>">View</a></td>
                            
            			
            				
<td>
    <form action="get-candidate-details" method="GET">
        <input type="hidden" name="id" value="<%= candidate.getCandId() %>">
        <button type="submit" class="view-button" style="background-color: #4CAF50; color: #fff;">generate offer letter</button>
    </form>
</td>
            				
            				
                        </tr>
                        
                    <% }
                } %>
            </tbody>
        </table>
    </div>

    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <script>
        function viewCandidate(candidateId) {
            $.ajax({
                url: 'viewCandidate.jsp', // Replace with the actual URL of your JSP page
                type: 'GET',
                data: { candidateId: candidateId },
                success: function (data) {
                    // Display the response in a modal or a specific section of the page
                    // Example: $('#modal').html(data);
                    // Example: $('#content').html(data);
                    console.log('Candidate data:', data);
                },
                error: function (xhr, status, error) {
                    console.log('Error:', error);
                }
            });
        }

        $(document).ready(function () {
            $("#filterInput").on("keyup", function () {
                var value = $(this).val().toLowerCase();
                $("#dataTable tbody tr").filter(function () {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
                });
            });
        });
    </script>
</body>
</html>