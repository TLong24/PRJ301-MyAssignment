<%-- 
    Document   : planCampaign
    Created on : Nov 1, 2024, 4:54:04 PM
    Author     : nlong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plan Campaign List</title>
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
            <h1>Plan Campaign List</h1>
        </div>
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Campaign ID </th>
                        <th>Plan ID </th>
                        <th>Product ID </th>
                        <th>Quantity</th>
                        <th>Estimated Effort</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="campaign" items="${requestScope.campaigns}">
                        <tr>
                            <td>${campaign.camid}</td>
                            <td>${campaign.plan.plId}</td>
                            <td>${campaign.product.pid}</td>
                            <td>${campaign.quantity}</td>
                            <td>${campaign.estimatedeffort}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
