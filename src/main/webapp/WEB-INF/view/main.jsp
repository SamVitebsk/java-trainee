<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Main menu</title>
</head>
<body>
    <h1>Hello From Servlet</h1>
    <p>Welcome : ${name}</p>

    <h2>Menu</h2>
    <ul>
        <li><a href="/warehouse/products">Show product list</a></li>
        <li><a href="/bucket/products">Show products in the bucket</a></li>
        <li><a href="/bucket/clear">Clear bucket</a></li>
        <li><a href="/order/make">Make an order</a></li>
        <li><a href="/order/history">Orders history</a></li>
        <li><a href="/order/not-accepted">Accept saved orders</a></li>
        <li><a href="/logout">Exit</a></li>
    </ul>
</body>
</html>