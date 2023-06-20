<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.ApplyPermissions" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Permission Requests</title>
<style>
   h1 {
            color: #333;
            margin-bottom: 30px;
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: #fff;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
            font-weight: bold;
            color: #333;
            text-transform: uppercase;
        }


        .no-employees {
            margin-top: 20px;
            color: #888;
            text-align: center;
        }

        .table-container {
            overflow-x: auto;
        }

        .table-container::-webkit-scrollbar {
            height: 8px;
            background-color: #f4f4f4;
        }

        .table-container::-webkit-scrollbar-thumb {
            background-color: #888;
            border-radius: 4px;
        }

        .table-container::-webkit-scrollbar-track {
            background-color: #f4f4f4;
        }

    .action-buttons {
        display: flex;
    }

    .action-buttons button {
        margin-right: 5px;
    }
</style>
</head>
<body>
    <h2>Permission Requests</h2>
    <table>
        <thead>
            <tr>
                <th>Emp ID</th>
                <th>Permission Date</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
            List<ApplyPermissions> permissions = (List<ApplyPermissions>) request.getAttribute("permissions");
            if (permissions != null) {
                for (ApplyPermissions permi : permissions) {
            %>
            <tr>
                <td align="center"><%= permi.getId().getId() %></td>
                <td align="center"><%= permi.getCurrent_date() %></td> 
                <td align="center"><%= permi.getStart_time() %></td>
                <td align="center"><%= permi.getEnd_time() %></td>
                <td class="action-buttons">
                    <button class="accept-button" onclick="acceptPermission('<%= permi.getId().getId() %>','<%= permi.getId().getEp_index() %>')">Accept</button>
                    <button class="reject-button" onclick="rejectPermission('<%= permi.getId().getId() %>','<%= permi.getId().getEp_index() %>')">Reject</button>
                </td>             
            </tr>
            <% 
                }
            }
            %>
        </tbody>
    </table>
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        function acceptPermission(empId, index) {
            console.log("Accept permission for empId: " + empId + ", index: " + index);
            // Send AJAX request to accept permission
            $.ajax({
                type: 'POST',
                url: 'PermissionActionServlet',
                data: {
                    action: 'accept',
                    empId: empId,
                    index: index
                },
                success: function(response) {
                    console.log(response);
                    // Refresh the page or update the table dynamically
                    location.reload();
                },
                error: function(error) {
                    console.log(error);
                }
            });
        }

        function rejectPermission(empId, index) {
            console.log("Reject permission for empId: " + empId + ", index: " + index);
            // Send AJAX request to reject permission
            $.ajax({
                type: 'POST',
                url: 'PermissionActionServlet',
                data: {
                    action: 'reject',
                    empId: empId,
                    index: index
                },
                success: function(response) {
                    console.log(response);
                    // Refresh the page or update the table dynamically
                    location.reload();
                },
                error: function(error) {
                    console.log(error);
                }
            });
        }
    </script>
</body>
</html>
