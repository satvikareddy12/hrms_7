<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employment Induction Documents</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        
        h1, h2 {
            color: #333;
            margin-bottom: 20px;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
        }
        
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ccc;
        }
        
        tr:hover {
            background-color: #f5f5f5;
        }
        
        label {
            display: inline-block;
            width: 150px;
            margin-bottom: 10px;
            font-weight: bold;
        }
        
        input[type="text"],
        input[type="file"] {
            padding: 5px;
            width: 300px;
        }
        
        button[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            cursor: pointer;
        }
        
        button[type="submit"]:hover {
            background-color: #45a049;
        }
        
        .add-document-form {
            display: none;
        }
        
        .show-form {
            display: block;
        }
    </style>
    <script>
        function showAddDocumentForm() {
            var form = document.getElementById("addDocumentForm");
            form.classList.toggle("show-form");
        }
    </script>
</head>
<body>
    <h1>Employment Induction Documents</h1>
    
    <table>
        <thead>
            <tr>
                <th>Document Index</th>
                <th>Employee ID</th>
                <th>Employment Offer</th>
                <th>Document Type</th>
                <th>Document Data</th>
                <th>Processed User</th>
                <th>Verified</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${documents}" var="document">
                <tr>
                    <td>${document.documentIndex}</td>
                    <td>${document.emplid}</td>
                    <td>${document.employmentOffer}</td>
                    <td>${document.documentType}</td>
                    <td>${document.documentData}</td>
                    <td>${document.processedUser}</td>
                    <td>${document.verified}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <button onclick="showAddDocumentForm()">Add Document</button>
    
    <div id="addDocumentForm" class="add-document-form">
        <h2>Add Document</h2>
        <form action="add" method="post" enctype="multipart/form-data">
        
         <label for="emplid">Employment ID:</label>
            <input type="text" id="emplid" name="emplid" required><br>

            <label for="employmentOffer">Employment Offer ID:</label>
            <input type="text" id="employmentOffer" name="employmentOffer" required><br>

            <label for="documentType">Document Type:</label>
            <input type="text" id="documentType" name="documentType" required><br>

            <label for="documentData">Document Data:</label>
            <input type="file" id="documentData" name="documentData" required><br>

            <label for="processedUser">Processed User:</label>
            <input type="text" id="processedUser" name="processedUser" required><br>

            <label for="verified">Verified:</label>
            <input type="text" id="verified" name="verified" required><br>

            <button type="submit">Add Document</button>
        </form>
    </div>
</body>
</html>
