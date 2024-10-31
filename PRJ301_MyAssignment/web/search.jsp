<%-- 
    Document   : search.jsp
    Created on : Oct 31, 2024, 6:45:12 PM
    Author     : nlong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Plans</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            display: flex;
            flex-direction: column;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .header {
            width: 100%;
            background-color: #4CAF50;
            color: white;
            padding: 15px;
            text-align: center;
        }
        .content {
            margin-top: 30px;
            width: 80%;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            margin-right: 10px;
        }
        .search-button {
            padding: 10px 20px;
            background-color: #008CBA;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .search-button:hover {
            background-color: #007bb5;
        }
        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: center;
        }
        th {
            background-color: #4CAF50;
            color: white;
            font-weight: bold;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>Search Plans</h1>
    </div>
    <div class="content">
        <form action="searchPlan" method="post">
            <div class="form-group">
                <label for="planId">Plan ID:</label>
                <input type="number" id="planId" name="planId">
            </div>
            <div class="form-group">
                <label for="startDate">Start Date:</label>
                <input type="date" id="startDate" name="startDate">
            </div>
            <div class="form-group">
                <label for="endDate">End Date:</label>
                <input type="date" id="endDate" name="endDate">
            </div>
            <button type="submit" class="search-button">Search</button>
        </form>

        <c:if test="${not empty listPlan}">
            <table>
                <thead>
                    <tr>
                        <th>Plan ID</th>
                        <th>Start</th>
                        <th>End</th>
                        <th>Department name</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="plan" items="${requestScope.listPlan}">
                        <tr>
                            <td>${plan.plId}</td>
                            <td>${plan.start}</td>
                            <td>${plan.end}</td>
                            <td>${plan.dept.dname}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</body>
</html>
