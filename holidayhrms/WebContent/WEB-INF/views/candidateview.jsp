<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Candidate List</title>
    <style>
      
        h1 {
            color: #333;
            margin-bottom: 30px;
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: #fff;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
            font-weight: bold;
            color: #333;
            text-transform: uppercase;
        }


        .no-employees {
            margin-top: 20px;
            color: #888;
            text-align: center;
        }

        .table-container {
            overflow-x: auto;
        }

        .table-container::-webkit-scrollbar {
            height: 8px;
            background-color: #f4f4f4;
        }

        .table-container::-webkit-scrollbar-thumb {
            background-color: #888;
            border-radius: 4px;
        }

        .table-container::-webkit-scrollbar-track {
            background-color: #f4f4f4;
        }

        /* Custom styles */
        .view-link {
            color: #007bff;
            text-decoration: none;
        }

        .view-link:hover {
            text-decoration: underline;
        }
         .center {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h1>Candidate List</h1>
    <%-- Retrieve the list of employees from the model --%>
    <% List<Object[]> candidates = (List<Object[]>) request.getAttribute("candidates"); %>
    <%-- Check if the list is not null and not empty --%>
    <% if (candidates != null && !candidates.isEmpty()) { %>
        <table>
            <thead>
                <tr>
                    <th>Candidate ID</th>
                    <th>First Name</th>
                    <th>Middle Name</th>
                    <th>Last Name</th>
                    <th>View</th>
                </tr>
            </thead>
            <tbody>
                <%-- Iterate over the list of employees and display the data --%>
                <% for (Object[] candidate : candidates) { %>
                    <tr>
                        <td><%= candidate[0] %></td>
                        <td><%= candidate[1] %></td>
                        <td><%= candidate[2] %></td>
                        <td><%= candidate[3] %></td>
                       <td><a class="view-link" href="viewcandidate?id=<%= candidate[0] %>">View</a></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <div class="center">
        <button onclick="window.location.href='candidate'">Add</button>
        
       </div>
    <% } else { %>
        <p class="no-employees">No candidates found.</p>
    <% } %>
</body>
</html>
