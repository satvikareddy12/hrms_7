<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.input.output.EmployeeOutput" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profile Page</title>
<link rel="stylesheet" type="text/css" href="./css/profile.css">
 
</head>
<body>
     <form action="update_address" method="POST">
       <% EmployeeOutput empdet = (EmployeeOutput) request.getAttribute("empdet"); %>
    
     <div>
      <% String imagePath = request.getContextPath() + "/"; %>
      <div class="profile-picture">
        <img src="<%=imagePath+empdet.getEmplPhoto()%>" alt="profile-picture" height="200px" width="200px">
      </div><br><br><br>

      <div class="form-group">
        <form>
          <label for="emplId">Employee ID</label>
          <input type="number" class="form-control" id="emplId" name="emplId" value="<%=empdet.getEmplId() %>" readonly><br><br>

          <label for="emplFirstname">First Name</label>
          <input type="text" class="form-control" id="emplFirstname" value="<%=empdet.getEmplFirstname() %>" readonly><br><br>

          <label for="emplLastname">Last Name</label>
          <input type="text" class="form-control" id="emplLastname" value="<%=empdet.getEmplLastname() %>" readonly><br><br>

          <label for="emplJbgrId">Job Group ID</label>
          <input type="text" class="form-control" id="emplJbgrId" value="<%=empdet.getEmplJbgrId() %>" readonly><br><br>

          <label for="emplJondate">Joining Date</label>
          <input type="text" class="form-control" id="emplJondate" value="<%=empdet.getEmplJondate() %>" readonly><br><br>

          <label for="emplDob">Date of Birth</label>
          <input type="text" class="form-control" id="emplDob" value="<%=empdet.getEmplDob() %>" readonly><br><br>

          <label for="emplDesignation">Designation</label>
          <input type="text" class="form-control" id="emplDesignation" value="<%=empdet.getEmplDesignation() %>" readonly><br><br>

          <label for="emplOffemail">Official Email</label>
          <input type="text" class="form-control" id="emplOffemail" value="<%=empdet.getEmplOffemail() %>" readonly><br><br>

          <label for="emplPemail">Personal Email</label>
          <input type="text" class="form-control" id="emplPemail" value="<%=empdet.getEmplPemail() %>" readonly><br><br>

          <label for="emplMobile">Mobile</label>
          <input type="text" class="form-control" id="emplMobile" value="<%=empdet.getEmplMobile() %>" readonly><br><br>

          <label for="emplAlemail">Alternate Email</label>
          <input type="text" class="form-control" id="emplAlemail" value="<%=empdet.getEmplAlemail() %>" readonly><br><br>

          <label for="emplBloodgroup">Blood Group</label>
          <input type="text" class="form-control" id="emplBloodgroup" value="<%=empdet.getEmplBloodgroup() %>" readonly><br><br>

          <label for="emplAddress">Address</label>
          <input type="text" class="form-control" id="emplAddress" name="emplAddress" value="<%=empdet.getEmplAddress() %>"><br><br>

          <label for="emplFname">Father's Name</label>
          <input type="text" class="form-control" id="emplFname" value="<%=empdet.getEmplFname() %>" readonly><br><br>

          <label for="emplLuudate">Last Updated Date</label>
          <input type="text" class="form-control" id="emplLuudate" value="<%=empdet.getEmplLuudate() %>" readonly><br><br>

          <label for="emplLuuser">Last Updated User</label>
          <input type="text" class="form-control" id="emplLuuser" value="<%=empdet.getEmplLuuser() %>" readonly><br><br>

          <button type="submit" class="btn btn-primary">Save Address</button>
        </form>
      </div>
    </div>
  </form>
</body>
</html>
