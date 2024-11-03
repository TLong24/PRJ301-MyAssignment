<%-- 
    Document   : editCampaign.jsp
    Created on : Nov 2, 2024, 4:08:59 PM
    Author     : nlong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Campaign</title>
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
                font-weight: bold;
                color: #4CAF50;
            }
            .form-group input, .form-group select {
                padding: 10px;
                width: 100%;
                max-width: 400px;
            }
            .update-button {
                padding: 10px 20px;
                background-color: #008CBA;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            .update-button:hover {
                background-color: #007bb5;
            }
        </style>
    </head>
    <body>
        <div class="header">
            <h1>Edit Campaign</h1>
        </div>
        <div class="content">
            <form action="editCampaign" method="POST">
                <div class="form-group">
                    <label for="plid">Campaign ID:</label>
                    <input type="text" readonly name="camid" value="${campaign.camid}" />
                </div>
                <div class="form-group">
                    <label for="plid">Plan ID:</label>
                    <input type="text" id="plid" name="plid" value="${campaign.plan.plId}" required />
                </div>
                <div class="form-group">
                    <label for="pid">Product ID:</label>
                    <input type="text" id="pid" name="pid" value="${campaign.product.pid}" required />
                </div>
                <div class="form-group">
                    <label for="quantity">Quantity:</label>
                    <input type="number" id="quantity" name="quantity" value="${campaign.quantity}" min="0" required />
                </div>
                <div class="form-group">
                    <label for="estimatedeffort">Estimated Effort:</label>
                    <input type="number" id="estimatedeffort" name="estimatedeffort" value="${campaign.estimatedeffort}" step="0.1" min="0" required />
                </div>
                <button type="submit" class="update-button">Update Campaign</button>
            </form>
        </div>
    </body>
</html>
