<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="models.input.output.CandidateIO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Candidate List</title>
    <link rel="stylesheet" type="text/css" href="./css/candidateView.css">
     <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script src="./js/candidateView.js"></script>
  
</head>
<body>
    <h1>Candidate List</h1>
    <%-- Retrieve the list of employees from the model --%>
    <% List<CandidateIO> candidates = (List<CandidateIO>) request.getAttribute("candidates"); %>
    <%-- Check if the list is not null and not empty --%>
    <% if (candidates != null && !candidates.isEmpty()) { %>
        <table>
            <thead>
                <tr>
                    <th>Candidate ID</th>
                    <th>First Name</th>
                    <th>Middle Name</th>
                    <th>Last Name</th>
                    <th>Registered Date</th>
                    <th>Gender</th>
                    <th>Date of Birth</th>
                    <th>Email</th>
                    <th>Mobile</th>
                    <th>Status</th>                   
                </tr>
            </thead>
            <tbody>
                <%-- Iterate over the list of employees and display the data --%>
                <% for (CandidateIO candidate : candidates) { %>
                     <tr onclick="openModal('<%= candidate.getCandId() %>')">
                        <td><%= candidate.getCandId() %></td>
                        <td><%= candidate.getCandFirstName() %></td>
                        <td><%= candidate.getCandMiddleName() %></td>
                        <td><%= candidate.getCandLastName() %></td>
                        <td><%= candidate.getCandRDate() %></td>
                        <td><%= candidate.getCandGender() %></td>
                        <td><%= candidate.getCandDOB() %></td>
                        <td><%= candidate.getCandEmail() %></td>
                        <td><%= candidate.getCandMobile() %></td>
                        <td><%= candidate.getCandStatus() %></td>                      
                    </tr>
                <% } %>
            </tbody>
        </table>
         <!-- Modal popup content -->
        <div id="myModal" class="modal">
            <div class="modal-content">
                <span class="close" onclick="closeModal()">&times;</span>
                <div id="modalContent"></div>
            </div>
        </div>
        <div class="center">
           <button onclick="openAddCandidatePopup()">Add</button> 
       </div>
    <% } else { %>
        <p class="no-employees">No candidates found.</p>
    <% } %>
</body>
</html>
