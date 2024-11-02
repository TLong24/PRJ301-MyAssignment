<%-- 
    Document   : create
    Created on : Oct 16, 2024, 9:15:47 AM
    Author     : sonnt-local
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PLan Creation</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
    </head>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            width: 60%;
            max-width: 800px;
        }
        h2 {
            text-align: center;
            color: #4CAF50;
        }
        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }
        .form-group {
            display: flex;
            justify-content: space-between;
            align-items: center;
            gap: 10px;
        }
        .form-group label {
            width: 25%;
            font-weight: bold;
            color: #4CAF50;
        }
        .form-group input, .form-group select {
            width: 70%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ddd;
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
            text-align: left;
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
        .submit-button {
            padding: 15px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            align-self: center;
            width: 150px;
        }
        .submit-button:hover {
            background-color: #43a047;
        }
    </style>
    <body>
        <div class="container">
            <h2>Create Plan</h2>
            <form action="create" method="POST"> 
                From: <input type="date" name="from" /> 
                To: <input type="date" name="to"/>
                <br/>
                Workshop: <select name="did">
                    <c:forEach items="${requestScope.depts}" var="d">
                        <option value="${d.did}">${d.dname}</option>
                    </c:forEach>
                </select>
                <br/>
                <table border="1px">
                    <tr>
                        <td>Product</td>
                        <td>Quantity</td>
                        <td>Estimated Effort</td>
                    </tr>
                    <c:forEach items="${requestScope.products}" var="p">
                        <tr>
                            <td>${p.pname}<input type="hidden" name="pid" value="${p.pid}"/></td>
                            <td><input type="number" name="quantity${p.pid}" min="0" step="1"/></td>
                            <td><input type="number" name="effort${p.pid}" min="0" step="0.1"/></td>
                        </tr>   
                    </c:forEach>
                </table>
                <input type="submit" name="Save"/>
            </form>
    </body>
</html>
