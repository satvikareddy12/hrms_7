<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Leave Requests</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"
	integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g="
	crossorigin="anonymous"></script>
<style>
/* Add your custom CSS styles here */
body {
	font-family: Arial, sans-serif;
}

h1 {
	color: #333;
	text-align: center;
}

table {
	border-collapse: collapse;
	width: 100%;
	max-width: 800px;
	margin: 0 auto;
	background-color: #fff;
}

th, td {
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
	font-weight: bold;
	color: #333;
}

tr:nth-child(even) {
	background-color: #f9f9f9;
}

.button {
	display: inline-block;
	padding: 8px 12px;
	font-size: 14px;
	font-weight: bold;
	text-align: center;
	text-decoration: none;
	background-color: #4caf50;
	color: #fff;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.button.reject {
	background-color: #f44336;
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
	max-width: 600px;
}

.close {
	color: #aaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
	cursor: pointer;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
}

.form-row {
	margin-bottom: 16px;
}

.form-row label {
	display: block;
	margin-bottom: 4px;
	font-weight: bold;
	color: #333;
}

.form-row input[type="date"] {
	padding: 6px 8px;
	font-size: 14px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

.form-row input[type="submit"] {
	padding: 8px 16px;
	font-size: 14px;
	font-weight: bold;
	text-align: center;
	text-decoration: none;
	background-color: #4caf50;
	color: #fff;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.action-buttons {
	display: flex;
	gap: 8px;
}
</style>
<script>
	function showLeaveModal(employeeId, employeeName, leaveType,
			startLeaveDate, endLeaveDate, reason,leaveId) {
		var modal = document.getElementById("leaveModal");
		var modalContent = document.getElementById("leaveModalContent");

		// Display the modal
		modal.style.display = "block";

		// Set the leave request details in the modal
		document.getElementById("employeeId").value = employeeId;
		document.getElementById("employeeName").value = employeeName;
		document.getElementById("leaveType").value = leaveType;
		document.getElementById("startDate").min = startLeaveDate;
		document.getElementById("startDate").max = endLeaveDate;
		document.getElementById("startDate").value = startLeaveDate;
		document.getElementById("endDate").min = startLeaveDate;
		document.getElementById("endDate").max = endLeaveDate;
		document.getElementById("endDate").value = endLeaveDate;
		document.getElementById("reason").value = reason;
		document.getElementById("leaveId").value = leaveId;

		// Close the modal when the close button is clicked
		var closeButton = document.getElementsByClassName("close")[0];
		closeButton.onclick = function() {
			modal.style.display = "none";
		};
	}

	function acceptLeave() {
		var modal = document.getElementById("leaveModal");
		console.log($("#leaveForm").serialize());
		$.ajax({
			url  : "acceptLeave",
			type : "POST",
			data : $("#leaveForm").serialize(),
			success : function(response){
				console.log(response);
				modal.style.display = "none";
				location.reload();
			},
			error : function(error){
				console.log(error);
			}
		});
	}

	function rejectLeave(id, index) {
		$.ajax({
			url : "rejectLeave",
			type : "POST",
			data : {
				"empId" : id,
				"leaveRequestIndex" : index
			},
			success : function(response) {
				console.log(response);
				location.reload();
			},
			error : function(error) {
				console.log(error);
			}
		});

	}
</script>
</head>
<body>
	<%@ page
		import="java.util.List,java.util.ArrayList,models.EmployeeLeaveModel"%>

	<%
	session.setAttribute("adminId", 123);
	%>
	<h1>Leave Requests</h1><br>
	<table>
		<tr>
			<th>Employee ID</th>
			<th>Employee Name</th>
			<th>Leave Type</th>
			<th>Start Leave Date</th>
			<th>End Leave Date</th>
			<th>Reason</th>
			<th>Action</th>
		</tr>
		<!--  type safety -->
		<%
		List<EmployeeLeaveModel> data = new ArrayList<>();
		Object attribute = request.getAttribute("data");
		if (attribute instanceof List<?>) {
			List<?> attributeList = (List<?>) attribute;
			for (Object item : attributeList) {
				if (item instanceof EmployeeLeaveModel) {
			EmployeeLeaveModel leaveModel = (EmployeeLeaveModel) item;
			data.add(leaveModel);
				}
			}
		}
		for (int i = 0; i < data.size(); i++) {
			EmployeeLeaveModel model = data.get(i);
		%>
		<tr>
			<td><%=model.getEmpId()%></td>
			<td><%=model.getName()%></td>
			<td><%=model.getLeaveType()%></td>
			<td><%=model.getLeaveStartDate()%></td>
			<td><%=model.getLeaveEndDate()%></td>
			<td><%=model.getReason()%></td>
			<td>
				<div class="action-buttons">
					<button class="button"
						onclick="showLeaveModal('<%=model.getEmpId()%>','<%=model.getName()%>','<%=model.getLeaveType()%>','<%=model.getLeaveStartDate()%>','<%=model.getLeaveEndDate()%>','<%=model.getReason()%>','<%=model.getLeaveRequestIndex()%>')">Accept</button>
					<button class="button reject"
						onclick="rejectLeave('<%=model.getEmpId()%>','<%=model.getLeaveRequestIndex()%>')">Reject</button>
				</div>
			</td>
		</tr>
		<%
		}
		%>
	</table>

	<!-- The modal -->
	<div id="leaveModal" class="modal">
		<div id="leaveModalContent" class="modal-content">
			<span class="close">&times;</span>
			<h2>Leave Request Details</h2>
			<form id="leaveForm">
				<div class="form-row">
					<label for="employeeId">Employee ID:</label> <input type="text"
						id="employeeId" name="employeeId" readonly>
				</div>

				<div class="form-row">
					<label for="employeeName">Employee Name:</label> <input type="text"
						id="employeeName" name="name" readonly>
				</div>

				<div class="form-row">
					<label for="leaveId">Leave Request ID:</label> <input type="text"
						id="leaveId" name="leaveRequestIndex" readonly>
				</div>

				<div class="form-row">
					<label for="leaveType">Leave Type:</label> <input type="text"
						id="leaveType" name="leaveType" readonly>
				</div>

				<div class="form-row">
					<label for="startDate">Start Leave Date:</label> <input type="date"
						id="startDate" name="leaveStartDate">
				</div>

				<div class="form-row">
					<label for="endDate">End Leave Date:</label> <input type="date"
						id="endDate" name="leaveEndDate">
				</div>

				<div class="form-row">
					<label for="reason">Reason:</label> <input type="text" id="reason"
						name="reason" readonly>
				</div>

				<div class="form-row">
					<label for="remarks">Remarks:</label>
					<textarea id="remarks" name="remarks"></textarea>
				</div>
				
				<div class="form-row">
					<input type="button" class="button" value="Accept"
						onclick="acceptLeave()">
				</div>
			</form>

		</div>
	</div>
</body>
</html>
