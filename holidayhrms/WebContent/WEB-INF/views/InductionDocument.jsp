<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employment Induction Documents</title>
    <style>
       
        
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
            background-color: #333;
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
        button
        {
         background-color: #333;
         padding: 10px 20px;
         margin: 0 auto;
         color: #fff;
           
        }
        h1,h2
        {
         text-align: center;
        }
        form,label
        {
          text-align: center;
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
    <h1>Employment Induction Documents</h1><br>
    
     <table>
        <thead>
            <tr>
                <th>employee id</th>
                <th>Doc_type</th>
                <th>DOC_path</th>
                <th>verification Status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${doc}" var="document">
                <tr>
                    <td>${document.emplid}</td>
                    <td>${document.emid_idty_id}</td>
                    <td>${document.documentData}</td>
                    <td>${document.verified}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table><br>
    
    <button onclick="showAddDocumentForm()">Add Document</button><br>
    
    <div id="addDocumentForm" class="add-document-form">
        <h2>Add Document</h2>
        <form action="add" method="get" >

            <label for="employmentOffer">Employment Offer ID:</label>
            <input type="text" id="employmentOffer" name="employmentOfferId" required><br>

            <label for="documentType">Document Type:</label>
            <input type="text" id="documentType" name="documentTypeId" required><br>

            <label for="documentData">Document Data:</label>
            <input type="file" id="documentData" name="documentData" required><br>
  
            <label for="processedUser">Processed User:</label>
            <input type="text" id="processedUser" name="processedUserId" required><br>

            <label for="verified">Verified:</label>
            <input type="text" id="verified" name="verified" required><br><br>

            <button type="submit">Insert</button>
        </form>
    </div>
</body>
</html>
