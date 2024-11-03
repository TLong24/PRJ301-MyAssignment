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
    <!--    <body>
            <div class="header">
                <h1>Plan Campaign List</h1>
            </div>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Campaign ID </th>
                            <th>Plan ID </th>
                            <th>Product Name </th>
                            <th>Quantity</th>
                            <th>Estimated Effort</th>
                        </tr>
                    </thead>
                    <tbody>
    <c:forEach var="campaign" items="${requestScope.campaigns}">
        <tr>
            <td>${campaign.camid}</td>
            <td>${campaign.plan.plId}</td>
            <td>${campaign.product.pname}</td>
            <td>${campaign.quantity}</td>
            <td>${campaign.estimatedeffort}</td>
        </tr>
    </c:forEach>
</tbody>
</table>
</div>
</body>-->

    <body>
        <div class="header">
            <h1>Plan Campaign List</h1>
        </div>
       

        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Campaign ID</th>
                        <th>Plan ID</th>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Estimated Effort</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="campaign" items="${requestScope.campaigns}">
                        <tr>
                            <td>${campaign.camid}</td>
                            <td>${campaign.plan.plId}</td>
                            <td>${campaign.product.pname}</td>
                            <td>${campaign.quantity}</td>
                            <td>${campaign.estimatedeffort}</td>
                            <td>
                                <a href="editCampaign?camid=${campaign.camid}" class="action-button edit-button">Edit</a>
                                <a href="deleteCampaign?camid=${campaign.camid}" class="action-button delete-button">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <c:if test="${not empty alertMessage}">
            <script type="text/javascript">
                alert("${requestScope.alertMessage}");
            </script>
        </c:if>

        <style>
            /* Styling for action buttons */
            .action-button {
                padding: 5px 10px;
                border: none;
                text-decoration: none;
                border-radius: 5px;
                color: white;
                font-weight: bold;
                cursor: pointer;
            }

            .edit-button {
                background-color: #4CAF50; /* Green */
            }

            .edit-button:hover {
                background-color: #45a049;
            }

            .delete-button {
                background-color: #f44336; /* Red */
            }

            .delete-button:hover {
                background-color: #e53935;
            }
        </style>
    </body>
</html>
