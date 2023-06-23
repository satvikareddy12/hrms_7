<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.lang.System" %>
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="0">

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Form</title>
     <style>
        
        h1 {
            color: #333;
        }
        
        form {
            max-width: 500px;
            margin: 0 auto; /* Center align the form */
            margin-top: 20px;
        }
        
        label {
            display: block;
            margin-top: 10px;
        }
        
        input[type="text"],
        input[type="date"],
        textarea {
            width: 100%;
            padding: 5px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            margin-top: 10px;
            cursor: pointer;
            border-radius: 3px;
        }
        
        button.back-button {
    		 background-color: #4CAF50;
             color: white;
             border: none;
             padding: 10px 20px;
             margin-top: 10px;
             cursor: pointer;
             border-radius: 3px;
		}
		 .modal {
        display: block;
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
        margin: 2% auto;
        padding: 20px;
        border: 1px solid #888;
        width: 80%;
        max-width: 800px;
        border-radius: 5px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        text-align: center; /* Center the content */
    }

    .close {
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
        cursor: pointer;
    }		
    </style>   
</head>

<body>

    <h1>Employee Form</h1>
    
      <form  method="post" action="employeeList" >
    
        <label for="emplId">Employee ID:</label>
        <input type="number" name="emplId" id="emplId" required><br>
        
        <label for="emplFirstname">First Name:</label>
        <input type="text" name="emplFirstname" id="emplFirstname" required><br>
        
        <label for="emplLastname">Last Name:</label>
        <input type="text" name="emplLastname" id="emplLastname" required><br>
        
        <label for="emplSurname">Surname:</label>
        <input type="text" name="emplSurname" id="emplSurname" required><br>
        
        <label for="emplRmanagerEmplId">Reporting Manager ID:</label>
        <input type="number" name="emplRmanagerEmplId" id="emplRmanagerEmplId"><br>
        
        <label for="emplHrEmplId">HR Employee ID:</label>
        <input type="number" name="emplHrEmplId" id="emplHrEmplId"><br>
        
        <label for="emplJbgrId">Job Group ID:</label>
        <input type="text" name="emplJbgrId" id="emplJbgrId"><br>
        
        <label for="emplPhoto">Photo:</label>
        <input type="text" name="emplPhoto" id="emplPhoto"><br>
        
        <label for="emplJondate">Joining Date:</label>
        <input type="date" name="emplJondate" id="emplJondate"><br>
        
        <label for="emplDob">Date of Birth:</label>
        <input type="date" name="emplDob" id="emplDob"><br>
        
        <label for="emplDesignation">Designation:</label>
        <input type="text" name="emplDesignation" id="emplDesignation"><br>
        
        <label for="emplOffemail">Official Email:</label>
        <input type="text" name="emplOffemail" id="emplOffemail"><br>
        
        <label for="emplPemail">Personal Email:</label>
        <input type="text" name="emplPemail" id="emplPemail"><br>
        
        <label for="emplMobile">Mobile Number:</label>
        <input type="number" name="emplMobile" id="emplMobile"><br>
        
        <label for="emplAlemail">Alternate Email:</label>
        <input type="text" name="emplAlemail" id="emplAlemail"><br>
        
        <label for="emplBloodgroup">Blood Group:</label>
        <input type="text" name="emplBloodgroup" id="emplBloodgroup"><br>
        
        <label for="emplGender">Gender:</label>
        <input type="text" name="emplGender" id="emplGender"><br>
        
        <label for="emplAddress">Address:</label>
        <textarea name="emplAddress" id="emplAddress"></textarea><br>
        
        <label for="emplFname">Father's Name:</label>
        <input type="text" name="emplFname" id="emplFname"><br>
        
        <label for="empl_ctc">Employee CTC:</label>
        <input type="number" name="empl_ctc" id="empl_ctc"><br>
        
        <label for="empl_basicsal">Basic Salary:</label>
        <input type="number" name="empl_basicsal" id="empl_basicsal"><br>
        
        <label for="empl_fixedsal">Fixed Salary:</label>
        <input type="number" name="empl_fixedsal" id="empl_fixedsal"><br>
        
        <label for="empl_variablesal">Variable Salary:</label>
        <input type="number" name="empl_variablesal" id="empl_variablesal"><br>
        
        <label for="empl_status">Status:</label>
        <input type="text" name="empl_status" id="empl_status"><br>
        
        <input type="submit" value="Submit"><br><br>
      
      </form>
     
    <div id="employeeList">       
    </div>
    
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script>
        function loadEmployeeList() {
    	$.ajax({
        	url: "employeeList", 
        	method: "GET",
        	success: function(response) {
            	$("#employeeList").html(response);
        	},
        	error: function(xhr, status, error) {
            	console.log("Error loading employee list: " + error);
        	}
    	});
	}
    loadEmployeeList();
    setInterval(loadEmployeeList, 2000);
   </script>
</body>
</html>