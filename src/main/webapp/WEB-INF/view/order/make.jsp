<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Make Order</title>
</head>
<body>
    <h1>Make Order</h1>
    <p><a href="/main">menu</a></p>

    <table>
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Category</th>
            <th>Count</th>
        </tr>
        <c:forEach var="pair" items="${products}" >
            <tr>
                <td>${pair.key.name}</td>
                <td>${pair.key.price}</td>
                <td>${pair.key.category}</td>
                <td>${pair.value}</td>
            </tr>
        </c:forEach>
    </table>

    <form action="/order/make" method="POST">
        <c:forEach var="pair" items="${totals}" >
            <label>
                ${pair.key.name()} ${pair.value} <input type="radio" name="sum" value="${pair.value}">
            </label>
        </c:forEach>
        <button type="submit">make order</button>
    </form>

</body>
</html>
