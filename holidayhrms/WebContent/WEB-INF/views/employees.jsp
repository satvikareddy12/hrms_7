<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee List</title>
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
    <h1>Employee List</h1>
    <%-- Retrieve the list of employees from the model --%>
    <% List<Object[]> employees = (List<Object[]>) request.getAttribute("employees"); %>
    <%-- Check if the list is not null and not empty --%>
    <% if (employees != null && !employees.isEmpty()) { %>
        <table>
            <thead>
                <tr>
                    <th>Employee ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Surname</th>
                    <th>View</th>
                    <th>Edit</th>
                </tr>
            </thead>
            <tbody>
                <%-- Iterate over the list of employees and display the data --%>
                <% for (Object[] employee : employees) { %>
                    <tr>
                        <td><%= employee[0] %></td>
                        <td><%= employee[1] %></td>
                        <td><%= employee[2] %></td>
                        <td><%= employee[3] %></td>
                       <td><a class="view-link" href="get-employee-details?id=<%= employee[0] %>">View</a></td>
                       <td><a class="view-link" href="updempl?id=<%= employee[0] %>">Edit</a></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <div class="center">
        <button onclick="window.location.href='addempl'">Add</button>
        <button onclick="window.location.href='delempl'">Delete</button>
        
       </div>
    <% } else { %>
        <p class="no-employees">No employees found.</p>
    <% } %>
</body>
</html>
