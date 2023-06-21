<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.Employee" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profile Page</title>
  <!-- Bootstrap CSS -->
  <!-- Custom CSS -->
 <style>
    /* Add your custom CSS styles here */
    .profile-picture {
      width: 150px;
      height: 150px;
      border-radius: 50%;
      object-fit: cover;
      margin-right: 20px;
      align-items: center;
      background-color: #f2f2f2;
      color: #666666;
      font-size: 48px;
      font-weight: bold;
      text-transform: uppercase;
    }
    
    /* Add custom styles to form elements */
    .form-group label {
      font-weight: bold;
    }
    
    .form-group input[type="text"] {
      background-color: #f2f2f2;
      border: none;
      color: #666666;
    }
    
    .form-group input[type="text"]:read-only {
      background-color: #ffffff;
    }
    
    .form-group input[type="text"]:focus {
      outline: none;
    }
    
    .form-group button {
      margin-top: 10px;
    }
  </style>
</head>
<body>
  <form action="update_address" method="POST">
    <% Employee empdet = (Employee) request.getAttribute("empdet"); %>

    <!-- Profile section -->
    <div class="col-md-4">
      <div class="d-flex justify-content-center">
        <% String imagePath = request.getContextPath() + "/"; %>
        <div class="profile-picture">
          <img src="<%=imagePath+empdet.getEmplPhoto()%>" alt="profile-picture" height="200px" width="200px">
        </div>
      </div>
    </div>

    <div class="col-md-8">
      <div class="card">
        <div class="card-header"></div>
        <div class="card-body">
          <div class="form-group">
            <div class="form-row" style="display: none;">
              <div class="form-group">
                <label for="emplId">Employee ID</label>
                <input type="text" class="form-control" id="emplId" name="emplId" value="<%=empdet.getEmplId() %>" readonly>
              </div>
            </div>
            <label for="emplFirstname">First Name</label>
            <input type="text" class="form-control" id="emplFirstname" value="<%=empdet.getEmplFirstname() %>" readonly>
          </div>
          <div class="form-group">
            <label for="emplLastname">Last Name</label>
            <input type="text" class="form-control" id="emplLastname" value="<%=empdet.getEmplLastname() %>" readonly>
          </div>
          <div class="form-row">
            <div class="form-group">
              <label for="emplJbgrId">Job Group ID</label>
              <input type="text" class="form-control" id="emplJbgrId" value="<%=empdet.getEmplJbgrId() %>" readonly>
            </div>
            <div class="form-group">
              <label for="emplJondate">Joining Date</label>
              <input type="text" class="form-control" id="emplJondate" value="<%=empdet.getEmplJondate() %>" readonly>
            </div>
          </div>
          <div class="form-row">
            <div class="form-group">
              <label for="emplDob">Date of Birth</label>
              <input type="text" class="form-control" id="emplDob" value="<%=empdet.getEmplDob() %>" readonly>
            </div>
            <div class="form-group">
              <label for="emplDesignation">Designation</label>
              <input type="text" class="form-control" id="emplDesignation" value="<%=empdet.getEmplDesignation() %>" readonly>
            </div>
          </div>
          <div class="form-row">
            <div class="form-group">
              <label for="emplOffemail">Official Email</label>
              <input type="text" class="form-control" id="emplOffemail" value="<%=empdet.getEmplOffemail() %>" readonly>
            </div>
            <div class="form-group">
              <label for="emplPemail">Personal Email</label>
              <input type="text" class="form-control" id="emplPemail" value="<%=empdet.getEmplPemail() %>" readonly>
            </div>
          </div>
          <div class="form-row">
            <div class="form-group">
              <label for="emplMobile">Mobile</label>
              <input type="text" class="form-control" id="emplMobile" value="<%=empdet.getEmplMobile() %>" readonly>
            </div>
            <div class="form-group">
              <label for="emplAlemail">Alternate Email</label>
              <input type="text" class="form-control" id="emplAlemail" value="<%=empdet.getEmplAlemail() %>" readonly>
            </div>
          </div>
          <div class="form-row">
            <div class="form-group">
              <label for="emplBloodgroup">Blood Group</label>
              <input type="text" class="form-control" id="emplBloodgroup" value="<%=empdet.getEmplBloodgroup() %>" readonly>
            </div>
            <div class="form-group">
              <label for="emplGender">Gender</label>
              <input type="text" class="form-control" id="emplGender" value="<%=empdet.getEmplGender() %>" readonly>
            </div>
          </div>
          <div class="form-group">
            <label for="emplAddress">Address</label>
            <input type="text" class="form-control" id="emplAddress" name="emplAddress" value="<%=empdet.getEmplAddress() %>">
          </div>
          <div class="form-group">
            <label for="emplFname">Father's Name</label>
            <input type="text" class="form-control" id="emplFname" value="<%=empdet.getEmplFname() %>" readonly>
          </div>
          <div class="form-group">
            <label for="emplLuudate">Last Updated Date</label>
            <input type="text" class="form-control" id="emplLuudate" value="<%=empdet.getEmplLuudate() %>" readonly>
          </div>
          <div class="form-group">
            <label for="emplLuuser">Last Updated User</label>
            <input type="text" class="form-control" id="emplLuuser" value="<%=empdet.getEmplLuuser() %>" readonly>
          </div>
          <button type="submit" class="btn btn-primary">Save Address</button>
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>
