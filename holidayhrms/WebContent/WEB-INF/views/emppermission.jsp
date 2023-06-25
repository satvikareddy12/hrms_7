<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Permission Application</title>

  <style>
    /* Add modern CSS styles for formatting */
    .container {
      max-width: 600px;
      margin: 0 auto;
      background-color: #fff;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    h1 {
      color: #333;
      text-align: center;
      margin-top: 0;
    }

    label {
      display: block;
      margin-bottom: 5px;
    }

    input[type="number"],
    input[type="date"],
    input[type="time"],
    textarea {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
      font-size: 14px;
      margin-bottom: 10px;
    }

    textarea {
      height: 100px;
    }

    button {
      background-color: #4CAF50;
      color: white;
      padding: 10px 20px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 14px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
    }

    button:hover {
      background-color: #45a049;
    }

    #msg {
      text-align: center;
      margin-top: 10px;
      font-size: 16px;
      color: #555;
    }
  </style>

  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
    $(document).ready(function() {
    	var today = new Date().toISOString().split("T")[0];
    	document.getElementById("current-date").setAttribute("max", today);
    	document.getElementById("current-date").setAttribute("min", today);


      // Update end time when start time changes
      $("#start-time").on("change", function() {
        var startTime = $("#start-time").val();
        if (startTime !== "") {
          var startDateTime = new Date("1970-01-01T" + startTime);
          var endDateTime = new Date(startDateTime.getTime() + 2 * 60 * 60 * 1000);
          var endTime = endDateTime.toTimeString().split(" ")[0];
          $("#end-time").val(endTime);
        }
      });
    });

    function permissionApplied() {
      // Prevent default form submission
      var validationData = {
        permissionsDayCount: <%= (Long)request.getAttribute("PermissionDayCount")%>,
        permissionsMonthCount : <%= (Long)request.getAttribute("PermissionMonthCount")%>
      };

      if (validationData.permissionsMonthCount >= 2) {
          alert("Maximum permissions applied for this Month");
          return;
      }
      
      if (validationData.permissionsDayCount > 0) {
        alert("Maximum permissions applied for today");
        return;
      }
      
      $("#msg").text("Applying....");

      $.ajax({
        type: "POST",
        url: "applyPermission",
        data: $("#permissionForm").serialize(), // Serialize form data
        success: function(response) {
          var containerDiv = $("#msg");
          containerDiv.text("successfully Applied");
        },
        error: function() {
          alert("Error occurred. Please try again later.");
        }
      });
    }
  </script>
</head>
<body>
  <h1>Permission Form</h1>
  <div class="container">
    <form id="permissionForm">
      <label for="id">ID:</label>
      <input type="number" id="id" name="id" required>

      <label for="current-date">Current Date:</label>
      <input type="date" id="current-date" name="current_date" required>

      <label for="start-time">Permission Start Time:</label>
      <input type="time" id="start-time" name="start_time"  required >
 
      <label for="end-time">Permission End Time:</label>
      <input type="time" id="end-time" name="end_time"  required >

      <label for="reason">Reason:</label>
      <textarea id="reason" name="reason" required></textarea>

      <button type="button" onclick="permissionApplied();">Apply</button>
    </form>
    
    <div id="msg"></div>
  </div>
  <div class="main"></div>
</body>
</html>
