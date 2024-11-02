<%-- 
    Document   : home
    Created on : Oct 31, 2024, 2:53:55 PM
    Author     : nlong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                position: relative;
            }
            .header .nav {
                position: absolute;
                top: 50%;
                right: 50px;
                transform: translateY(-50%);
                display: flex;
                gap: 15px;
            }
            .header .nav a {
                color: white;
                text-decoration: none;
                font-weight: bold;
            }
            .header .nav a:hover {
                text-decoration: underline;
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
            .welcome-message {
                font-size: 18px;
                margin-bottom: 20px;
            }
            .action-buttons {
                display: flex;
                justify-content: space-around;
                margin-top: 20px;
            }
            .action-button {
                padding: 15px;
                background-color: #008CBA;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                text-align: center;
            }
            .action-button:hover {
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
            .table-container {
                width: 100%;
                overflow-x: auto;
            }
        </style>
    </head>
    <body>
        <div class="header">
            <h1>Welcome to the Home Page</h1>
            <div class="nav">
                <a href="PlanCampaignController" class="action">View Campaign</a>
            </div>
        </div>
        <div class="content">
            <div class="action-buttons">
                <a href="plan/create" class="action-button">Add</a>
                <a href="search.jsp" class="action-button">Search</a>
                <a href="updatePlan" class="action-button">Update</a>
            </div>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Plan ID</th>
                            <th>Start</th>
                            <th>End</th>
                            <th>Department ID</th>
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
            </div>
        </div>
    </body>
</html>
