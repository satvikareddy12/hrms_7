<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <title>Pennant HRMS</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="style.css">
    <!---we had linked our css file----->
 <link rel="stylesheet" type="text/css" href="./css/login.css">
     <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="./js/login.js"></script>
          <script src="./js/login2.js"></script>
</head>
<body>
    <div class="full-page">
        <div class="navbar">
            <div>
                <a href='https://www.pennanttech.com/'>PENNANT HRMS</a>
            </div>
            <nav>
                <ul id='MenuItems'>
                    <li><a href='#'>About Us</a></li>
                    <li><a href='#'>Contact</a></li>
                    <li><button class='loginbtn' onclick="document.getElementById('login-form').style.display='block'" style="width:auto;">Login</button></li>
                </ul>
            </nav>
        </div>
       
        <div id='login-form' class='login-page'>
            <div class="form-box">
                <div class='button-box'>
                    <div id='btn'></div>
                    <button type='button'onclick='login()'class='toggle-btn'>Emp</button>
                    <button type='button'onclick='register()'class='toggle-btn'>Admin</button>
                </div>
                <form id='login' class='input-group-login' action ="employee" method="POST">
                    <input type='text' id="empl_email" name ="empl_email" class='input-field'placeholder='Email Id' required >
		    <input type='password'id="empl_password" name="empl_password" class='input-field'placeholder='Enter Password' required>
		      <div class="forgot-password"> <a href="forgot.jsp">Forgot Password?</a></div>
		    <button type='submit'class='submit-btn' >Log in</button>
		 </form>
		 <form id='register' class='input-group-register' action ="admin" method="POST">
                    <input type='text'id="admin_email" name ="admin_email" class='input-field'placeholder='Email Id' required >
		    <input type='password' id="admin_password"  name ="admin_password" class='input-field'placeholder='Enter Password' required>
		    <div class="forgot-password a"> <a href="forgot.jsp">Forgot Password?</a></div>
		    <button type='submit'class='submit-btn' >Log in</button>
	         </form>
            </div>
        </div>
    </div>
     
  
    
    
</body>
</html>