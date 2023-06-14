<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <title>Online HTML Editor</title>
  <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
    }

    .container {
      width: 600px;
      margin: 50px auto;
      background-color: #fff;
      border: 1px solid #ccc;
      padding: 20px;
    }

    h3 {
      margin-bottom: 10px;
    }

    #filterInput {
      width: 100%;
      padding: 10px;
      font-size: 16px;
      border-radius: 5px;
      border: 1px solid #ccc;
      margin-bottom: 10px;
    }

    #dataTable {
      width: 100%;
      border-collapse: collapse;
    }

    #dataTable th,
    #dataTable td {
      padding: 8px;
      border-bottom: 1px solid #ddd;
    }

    #dataTable th {
      background-color: #f2f2f2;
    }
  </style>
</head>
<body>
  <div class="container">
    <h3>Candidates Data</h3>

    <input type="text" id="filterInput" placeholder="Search...">

    <table id="dataTable">
      <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Details</th>
      </tr>
      <tr>
        <td>01</td>
        <td>Nagaraju</td>
        <td><a href="#">View</a></td>
      </tr>
      <tr>
        <td>02</td>
        <td>Bhaskar</td>
        <td><a href="#">View</a></td>
      </tr>
      <tr>
        <td>03</td>
        <td>Satvika</td>
        <td><a href="#">View</a></td>
      </tr>
      <tr>
        <td>04</td>
        <td>Sowjanya</td>
        <td><a href="#">View</a></td>
      </tr>
      <tr>
        <td>05</td>
        <td>Hussein</td>
        <td><a href="#">View</a></td>
      </tr>
    </table>
  </div>

  <script>
    $(document).ready(function() {
      $("#filterInput").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#dataTable tbody tr").filter(function() {
          $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
        });
      });
    });
  </script>
</body>
</html>
