<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@page import="models.Holiday" %>
<%@ page import="java.util.List" %>

<div class="container">
    
    <div class="search-box">
       <input type="text" id="filterInput" placeholder="Search..." onkeyup="filterTable()">

    </div>
    <div class="table-container">
        <table id="dataTable">
            <thead>
                <tr>
                    <th>Year</th>
                    <th>Holiday Date</th>
                    <th>Holiday Title</th>
                    <th>Holiday Type</th>
                </tr>
            </thead>
            <tbody>
                <%
                List<Holiday> holidays = (List<Holiday>) request.getAttribute("holidays");
                if (holidays != null) {
                    for (Holiday holiday : holidays) { %>
                        <tr>
                            <td align="center"><%= holiday.getYear() %></td>
                            <td align="center"><%= holiday.gethday_date() %></td>
                            <td align="center"><%= holiday.gethday_title() %></td>
                            <td align="center"><%= holiday.gethday_type() %></td>
                        </tr>
                    <% }
                } %>
            </tbody>
        </table>
    </div>
</div>

<style>
    /* Styles for holidays.jsp */
    .container {
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-top: 20px;
    }
      .search-box {
        width: 200px;
        margin-top: 0;
        margin-bottom: 10px;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .search-box input[type="text"] {
        height: 30px; /* Adjust the height as desired */
        padding: 5px;
    }
    .table-container {
        width: 100%;
        overflow-x: auto;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        background-color: #fff;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
    }
    th, td {
        padding: 5px 13px;
    }
    th {
        background-color: #333;
        color: #fff;
        font-weight: bold;
        align:center;
    }
    tr:nth-child(even) {
        background-color: #f2f2f2;
    }
    tr:hover {
        background-color: #ddd;
    }
</style>
<script>
    function filterTable() {
        // Get the input value and convert to lowercase
        console.log("function called");
        var input = document.getElementById("filterInput").value.toLowerCase();
        
        // Get the table rows
        var rows = document.getElementById("dataTable").getElementsByTagName("tr");
        
        // Iterate through the rows and hide those that don't match the search input
        for (var i = 0; i < rows.length; i++) {
            var row = rows[i];
            var cells = row.getElementsByTagName("td");
            var match = false;
            
            for (var j = 0; j < cells.length; j++) {
                var cell = cells[j];
                
                // Check if the cell content matches the search input
                if (cell.innerHTML.toLowerCase().indexOf(input) > -1) {
                    match = true;
                    break;
                }
            }
            
            // Show or hide the row based on the match
            if (match) {
                row.style.display = "";
            } else {
                row.style.display = "none";
            }
        }
    }
</script>
