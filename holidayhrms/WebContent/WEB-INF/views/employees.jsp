<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="models.input.output.EmployeeOutput" %>
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="0">

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee List</title>
    <style>
    table {
        width: 100%;
        border-collapse: collapse;
    }

    th,
    td {
        padding: 10px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    th {
        background-color: #f2f2f2;
    }

    tr:hover {
        background-color: #f5f5f5;
        cursor: pointer;
    }

    .modal {
        display: none;
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0, 0, 0, 0.4);
    }

    .modal-content {
        background-color: #fefefe;
        margin: 10% auto;
        padding: 20px;
        border: 1px solid #888;
        width: 80%;
        max-width: 800px;
        border-radius: 5px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .close {
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
        cursor: pointer;
    }

    .center {
        text-align: center;
        margin-top: 20px;
    }

    .center button {
        padding: 10px 20px;
        font-size: 16px;
        border: none;
        background-color: #333;
        color: #fff;
        cursor: pointer;
        margin-right: 10px;
    }


    .no-employees {
        text-align: center;
        color: #888;
        font-style: italic;
    }
    h1
    {
      text-align: center;
    }
    </style>

    <script>
        // Function to open the modal popup for employee details
        function openModal(employeeId) 
        {
           
        	var modal = document.getElementById("myModal");
            modal.style.display = "block";          
            var url = "get-employee-details?id=" + employeeId;            
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    var employeeDetails = this.responseText;
                    document.getElementById("modalContent").innerHTML = employeeDetails;
                }
            };
            xhttp.open("GET", url, true);
            xhttp.send();
        }
        
     // Function to open the modal popup for inserting a new employee
        function openAddEmployeePopup() 
        {
            var modal = document.getElementById("myModal");
            modal.style.display = "block";          
            var url = "addempl";           
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    var addEmployeeForm = this.responseText;
                    document.getElementById("modalContent").innerHTML = addEmployeeForm;
                }
            };
            xhttp.open("GET", url, true);
            xhttp.send();
                    
        }
        
     // Function to open the modal popup for deleting an employee
        function openDeleteEmployeePopup() 
        {
            var modal = document.getElementById("myModal");
            modal.style.display = "block";           
            var url = "delempl";           
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    var deleteEmployeePage = this.responseText;
                    document.getElementById("modalContent").innerHTML = deleteEmployeePage;
                }
            };
            xhttp.open("GET", url, true);
            xhttp.send();
            
        }


        
        // Function to close the modal popup
        function closeModal() {
            var modal = document.getElementById("myModal");
            modal.style.display = "none";
        }
        
    </script>
</head>

<body>
    <h1>Employee List</h1><br>
    <% List<EmployeeOutput> employees = (List<EmployeeOutput>) request.getAttribute("employees"); %>
    <% if (employees != null && !employees.isEmpty()) { %>
        <table>
            <thead>
                <tr>
                    <th>Employee ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Surname</th>
                    <th>Gender</th>
                    <th>Manager Id</th>
                    <th>HR Id</th>
                    <th>Job Grade</th>
                    <th>Joining Date</th>
                    <th>Date of Birth</th>
                    <th>Designation</th>
                    <th>Office Email</th>
                    <th>Personal Email</th>
                    <th>Mobile number</th>                               
                </tr>
            </thead>
            <tbody>
                <% for (EmployeeOutput employee : employees) { %>
                    <tr onclick="openModal('<%= employee.getEmplId() %>')">
                        <td><%= employee.getEmplId() %></td>
                        <td><%= employee.getEmplFirstname() %></td>
                        <td><%= employee.getEmplLastname() %></td>
                        <td><%= employee.getEmplSurname() %></td>
                        <td><%= employee.getEmplGender() %></td>
                        <td><%= employee.getEmplRmanagerEmplId() %></td>
                        <td><%= employee.getEmplHrEmplId() %></td>
                        <td><%= employee.getEmplJbgrId() %></td>
                        <td><%= employee.getEmplJondate() %></td>
                        <td><%= employee.getEmplDob() %></td>
                        <td><%= employee.getEmplDesignation() %></td>
                        <td><%= employee.getEmplOffemail() %></td>
                        <td><%= employee.getEmplPemail() %></td>
                        <td><%= employee.getEmplMobile() %></td>                       
                    </tr>
                <% } %>
            </tbody>
        </table>

        <!-- Modal content -->
        <div id="myModal" class="modal">
            <div class="modal-content">
                <span class="close" onclick="closeModal()">&times;</span>
                <div id="modalContent"></div>
            </div>
        </div>

         <!-- Add and delete buttons -->
        <div class="center">       
            <button onclick="openAddEmployeePopup();">Add</a>
            <button onclick="openDeleteEmployeePopup()">Delete</button>
        </div>
        
    <% } else { %>
        <p class="no-employees">No employees found.</p>
    <% } %>
    
</body>
</html>
