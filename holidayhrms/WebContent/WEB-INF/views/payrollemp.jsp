<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Enter Candidate ID</title>
    <style>
        
        h1 {
            color: #333;
            text-align: center;
        }
        
        form {
            margin-top: 20px;
            text-align: center;
        }
        
        label {
            display: block;
            margin-bottom: 10px;
            text-align: center;
        }
        
        input[type="text"] {
            padding: 5px;
            width: 200px;
        }
        
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #333;
            color: #fff;
            border: none;
            cursor: pointer;
            margin: 0 auto;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
  <script>
  

    function payroll() {

      $.ajax({
        type: "POST",
        url: "getpayslip", 
        data: $("#payrollemp").serialize(),
        success: function(response) {
          var containerDiv = $(".main");
          containerDiv.html(response);
        },
        error: function() {
          alert("Error occurred. Please try again later.");
        }
      });
    }
 
  </script>
  
  
    
</head>
<body>
    <h1>Enter Employee ID</h1><br>
    <form id="payrollemp" method="post">
        <label for="employeeeId">Employee ID:</label>
        <input type="text" id="empl_id" name="empl_id" required><br><br>
        <input type="button" onclick="payroll();" value="Get Details">
    </form>
     </div>
  <div class="main"></div>
  
</body>
</html>
