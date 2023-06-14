<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Pennant HRMS</title>
    <link rel="stylesheet" href="style.css" />
 
    <style> 
    * {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Poppins", sans-serif;
}
.sidebar {
  position: fixed;
  height: 100%;
  width: 260px;
  background: #11101d;
  padding: 15px;
  z-index: 99;
}
.logo {
  font-size: 25px;
  padding: 0 15px;
}
.sidebar a {
  color: #fff;
  text-decoration: none;
}
.menu-content {
  position: relative;
  height: 100%;
  width: 100%;
  margin-top: 40px;
  overflow-y: scroll;
}
.menu-content::-webkit-scrollbar {
  display: none;
}
.menu-items {
  height: 100%;
  width: 100%;
  list-style: none;
  transition: all 0.4s ease;
}
.submenu-active .menu-items {
  transform: translateX(-56%);
}
.menu-title {
  color: #fff;
  font-size: 14px;
  padding: 15px 20px;
}
.item a,
.submenu-item {
  padding: 16px;
  display: inline-block;
  width: 100%;
  border-radius: 12px;
}
.item i {
  font-size: 12px;
}
.item a:hover,
.submenu-item:hover,
.submenu .menu-title:hover {
  background: rgba(255, 255, 255, 0.1);
}
.submenu-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: #fff;
  cursor: pointer;
}
.submenu {
  position: absolute;
  height: 100%;
  width: 100%;
  top: 0;
  right: calc(-100% - 26px);
  height: calc(100% + 100vh);
  background: #11101d;
  display: none;
}
.show-submenu ~ .submenu {
  display: block;
}
.submenu .menu-title {
  border-radius: 12px;
  cursor: pointer;
}
.submenu .menu-title i {
  margin-right: 10px;
}
.navbar,
.main {
  left: 260px;
  width: calc(100% - 260px);
  transition: all 0.5s ease;
  z-index: 1000;
}
.sidebar.close ~ .navbar,
.sidebar.close ~ .main {
  left: 0;
  width: 100%;
}
.navbar {
  position: fixed;
  color: #fff;
  padding: 15px 20px;
  font-size: 25px;
  background: #4070f4;
  cursor: pointer;
}
.navbar #sidebar-close {
  cursor: pointer;
}
.main {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
  z-index: 100;
  background: #e7f2fd;
}
.main h1 {
  color: #11101d;
  font-size: 40px;
  text-align: center;
}
    
    </style>
    <!-- Fontawesome CDN Link -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.9.1/dist/chart.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
    
    
    
    <script>
    function getHolidays() {
    	  $.ajax({
    	    type: "POST",
    	    url: "holidaysupdd",
    	    data: {},
    	    success: function(response) {
    	      var containerDiv = $("#main");
    	      containerDiv.html(response);
    	      history.pushState(null, "", "holidaysupd");
    	    },
    	    error: function() {
    	      alert("Error occurred. Please try again later.");
    	    }
    	  });
    	}

    </script>
    
     <script>
    function getHolidays() {
    	  $.ajax({
    	    type: "POST",
    	    url: "holidaysupmd",
    	    data: {},
    	    success: function(response) {
    	      var containerDiv = $("#main");
    	      containerDiv.html(response);

    	    },
    	    error: function() {
    	      alert("Error occurred. Please try again later.");
    	    }
    	  });
    	}

    function getCandidates() {
  	  $.ajax({
  	    type: "POST",
  	    url: "candidates",
  	    data: {},
  	    success: function(response) {
  	      var containerDiv = $("#main");
  	      containerDiv.html(response);
  	    },
  	    error: function() {
  	      alert("Error occurred. Please try again later.");
  	    }
  	  });
  	}

    
    
    function getyourtHolidays() {
  	  $.ajax({
  	    type: "POST",
  	    url: "yourholidayss",
  	    data: {},
  	    success: function(response) {
  	      var containerDiv = $("#main");
  	      containerDiv.html(response);
  	      history.pushState(null, "", "yourholidays");
  	    },
  	    error: function() {
  	      alert("Error occurred. Please try again later.");
  	    }
  	  });
  	}

    </script>
    
    

    
  </head>
  <body>
    <nav class="sidebar">
      <a href="#" class="logo">ADMIN</a>

      <div class="menu-content">
        <ul class="menu-items">
          <div class="menu-title"><i class="ri-dashboard-line"></i>
            DashBoard
          </div>

          <li class="item">
            <a href="#"><i class="ri-bar-chart-line"></i> Profile</a>
          </li>
          
<li class="item">
            <div class="submenu-item">
              <span><i class="ri-handbag-line"></i> Candidates</span>
              <i class="fa-solid fa-chevron-right"></i>
            </div>

            <ul class="menu-items submenu">
              <div class="menu-title">
                <i class="fa-solid fa-chevron-left"></i>
                Back to Candidates
              </div>
             
              <li class="item">
              <a href="javascript:void(0)"  onclick="getHolidays();"><i class="ri-calendar-line"></i> Insert</a>
              </li>
              
               <li class="item">
                <a href="javascript:void(0)"  onclick="getyourtHolidays();"><i class="ri-file-list-line"></i> View</a>
              </li>
              
            </ul>
          </li>

          <li class="item">
            <div class="submenu-item">
              <span><i class="ri-handbag-line"></i> Offer Letters</span>
              <i class="fa-solid fa-chevron-right"></i>
            </div>

            <ul class="menu-items submenu">
              <div class="menu-title">
                <i class="fa-solid fa-chevron-left"></i>
                Back to Offer Letters
              </div>
             
              <li class="item">
              <a href="javascript:void(0)"  onclick="getHolidays();"><i class="ri-calendar-line"></i> Provided</a>
              </li>
              
               <li class="item">
                <a href="javascript:void(0)"  onclick="getCandidates();"><i class="ri-file-list-line"></i> Issue</a>
              </li>
              
            </ul>
          </li>
          
<li class="item">
            <div class="submenu-item">
              <span><i class="ri-handbag-line"></i> Induction</span>
              <i class="fa-solid fa-chevron-right"></i>
            </div>

            <ul class="menu-items submenu">
              <div class="menu-title">
                <i class="fa-solid fa-chevron-left"></i>
                Back to Induction
              </div>
             
              <li class="item">
              <a href="javascript:void(0)"  onclick="getHolidays();"><i class="ri-calendar-line"></i> Insert</a>
              </li>
              
               <li class="item">
                <a href="javascript:void(0)"  onclick="getyourtHolidays();"><i class="ri-file-list-line"></i> View</a>
              </li>
              
            </ul>
          </li>
          
          <li class="item">
            <div class="submenu-item">
              <span><i class="ri-user-line"></i> Employees</span>
              <i class="fa-solid fa-chevron-right"></i>
            </div>

            <ul class="menu-items submenu">
              <div class="menu-title">
                <i class="fa-solid fa-chevron-left"></i>
                Back to Employees
              </div>
              <li class="item">
                <a href="#"><i class="ri-file-list-line"></i> View Employees</a>
              </li>
              <li class="item">
                <a href="#"><i class="ri-check-double-line"></i> Edit Employees</a>
              </li>
            </ul>
          </li>
          <li class="item">
            <div class="submenu-item">
              <span><i class="ri-user-line"></i> Master Data</span>
              <i class="fa-solid fa-chevron-right"></i>
            </div>

            <ul class="menu-items submenu">
              <div class="menu-title">
                <i class="fa-solid fa-chevron-left"></i>
                Back to Master Data
              </div>
              <li class="item">
                <a href="#"><i class="ri-file-list-line"></i> Holidays</a>
              </li>
              <li class="item">
                <a href="#"><i class="ri-check-double-line"></i> Leaves</a>
              </li>
            </ul>
          </li>
          
           <li class="item">
            <div class="submenu-item">
              <span><i class="ri-user-line"></i> ALMS</span>
              <i class="fa-solid fa-chevron-right"></i>
            </div>

            <ul class="menu-items submenu">
              <div class="menu-title">
                <i class="fa-solid fa-chevron-left"></i>
                Back to ALMS
              </div>
              <li class="item">
                <a href="#"><i class="ri-file-list-line"></i> Attendance</a>
              </li>
              <li class="item">
                <a href="#"><i class="ri-check-double-line"></i> Leaves</a>
              </li>
               <li class="item">
                <a href="#"><i class="ri-check-double-line"></i> Permissions</a>
              </li>
              <li class="item">
                <a href="#"><i class="ri-check-double-line"></i> Approvals</a>
              </li>
            </ul>
          </li>
          
          <li class="item">
            <div class="submenu-item">
              <span><i class="ri-user-line"></i> Reference Documents</span>
              <i class="fa-solid fa-chevron-right"></i>
            </div>

            <ul class="menu-items submenu">
              <div class="menu-title">
                <i class="fa-solid fa-chevron-left"></i>
                Back to Reference Documents
              </div>
              <li class="item">
                <a href="#"><i class="ri-file-list-line"></i> Employee Induction</a>
              </li>
              <li class="item">
                <a href="#"><i class="ri-check-double-line"></i> Employee Reference</a>
              </li>
            </ul>
          </li>
          
          <li class="item">
            <a href="#"><span class="icon"><i class="ri-booklet-line"></i></span> Payrolls</a>
          </li>
          <li class="item">
            <a href="#"><span class="icon"><i class="ri-settings-3-line"></i></span> Settings</a>
          </li>
          <li class="item">
            <a href="#"><span class="icon"><i class="ri-question-line"></i></span> Help</a>
          </li>
          <li class="item">
            <a href="#"><span class="icon"><i class="ri-logout-box-r-line"></i></span> Logout</a>
          </li>
        </ul>
      </div>
    </nav>

    <nav class="navbar">
      <i class="fa-solid fa-bars" id="sidebar-close"></i>
    </nav>

    <main class="main" id="main">
      <h1>Welcome Admin to Pennant Technologies....</h1>
    </main>

   
  </body>
       <script>
       const sidebar = document.querySelector(".sidebar");
       const sidebarClose = document.querySelector("#sidebar-close");
       const menu = document.querySelector(".menu-content");
       const menuItems = document.querySelectorAll(".submenu-item");
       const subMenuTitles = document.querySelectorAll(".submenu .menu-title");

       sidebarClose.addEventListener("click", () => sidebar.classList.toggle("close"));

       menuItems.forEach((item, index) => {
         item.addEventListener("click", () => {
           menu.classList.add("submenu-active");
           item.classList.add("show-submenu");
           menuItems.forEach((item2, index2) => {
             if (index !== index2) {
               item2.classList.remove("show-submenu");
             }
           });
         });
       });

       subMenuTitles.forEach((title) => {
         title.addEventListener("click", () => {
           menu.classList.remove("submenu-active");
         });
       });

</script>
</html>

