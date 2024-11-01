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
        <title>JSP Page</title>
    </head>
    <body>
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
