<%-- 
    Document   : updatePlan
    Created on : Nov 2, 2024, 8:50:01 AM
    Author     : nlong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Plan</title>
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
                width: 50%;
                background-color: #ffffff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
                text-align: center;
            }
            .form-group {
                margin-bottom: 20px;
                display: flex;
                flex-direction: column;
                align-items: flex-start;
            }
            .form-group label {
                margin-bottom: 5px;
                font-weight: bold;
                color: #4CAF50;
            }
            .form-group input, .form-group select {
                padding: 10px;
                width: 100%;
                max-width: 400px;
                border-radius: 5px;
                border: 1px solid #ddd;
            }
            .update-button {
                padding: 10px 20px;
                background-color: #008CBA;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                margin-top: 20px;
            }
            .update-button:hover {
                background-color: #007bb5;
            }
        </style>
    </head>
    <body>
        <div class="header">
            <h1>Update Plan</h1>
        </div>
        <div class="content">
            <form action="updatePlan" method="POST">
                <div class="form-group">
                    <label for="plid">Plan ID:</label>
                    <input type="text" id="plid" name="plid" value="${requestScope.plid}" required />
                </div>
                <div class="form-group">
                    <label for="start">Start Date:</label>
                    <input type="date" id="start" name="start" value="${requestScope.start}" required />
                </div>
                <div class="form-group">
                    <label for="end">End Date:</label>
                    <input type="date" id="end" name="end" value="${requestScope.end}" required />
                </div>
                <div class="form-group">
                    <label for="did">Department:</label>
                    <select id="did" name="did" required>
                        <c:forEach var="dept" items="${requestScope.departments}">
                            <option 
                                value="${dept.did}"
                                <c:if test="${dept.did == requestScope.did}">selected</c:if>>${dept.dname}
                                </option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    ${requestScope.error}
                </div>
                <button type="submit" class="update-button">Update Plan</button>
            </form>
        </div>
    </body>
</html>


