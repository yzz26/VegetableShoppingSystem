<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>蔬菜购物系统 - 购物车</title>
    <style>
        body { font-family: "Microsoft YaHei", sans-serif; background: #f5f5f5; margin: 0; padding: 20px; }
        .container { max-width: 900px; margin: 0 auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        h1 { color: #2e7d32; text-align: center; }
        table { width: 100%; border-collapse: collapse; margin: 20px 0; }
        th { background: #ff9800; color: white; padding: 12px; }
        td { padding: 10px; text-align: center; border-bottom: 1px solid #e0e0e0; }
        tr:hover { background: #fff3e0; }
        .btn-remove { background: #f44336; color: white; padding: 4px 12px; text-decoration: none; border-radius: 4px; font-size: 13px; }
        .btn-remove:hover { background: #d32f2f; }
        .btn-back { display: inline-block; padding: 10px 25px; background: #4caf50; color: white; text-decoration: none; border-radius: 4px; }
        .btn-back:hover { background: #388e3c; }
        .btn-checkout { display: inline-block; padding: 10px 30px; background: #f44336; color: white; text-decoration: none; border-radius: 4px; font-size: 18px; }
        .btn-checkout:hover { background: #c62828; }
        .total-price { font-size: 24px; color: #f44336; text-align: right; padding: 15px 0; border-top: 2px solid #eee; }
        .empty-cart { text-align: center; padding: 50px 0; color: #999; }
        .actions { display: flex; justify-content: space-between; align-items: center; margin-top: 20px; }
    </style>
</head>
<body>
<div class="container">
    <h1>🛒 我的购物车</h1>

    <c:choose>
        <c:when test="${empty cartItems}">
            <div class="empty-cart">
                <p>🛍️ 购物车是空的，快去选购吧！</p>
                <a href="ProductListServlet" class="btn-back">继续购物</a>
            </div>
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th>商品名称</th>
                    <th>单价（元/斤）</th>
                    <th>数量（斤）</th>
                    <th>小计（元）</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${cartItems}" var="item">
                    <tr>
                        <td>${item.productName}</td>
                        <td>￥${item.price}</td>
                        <td>${item.quantity}</td>
                        <td>￥${item.price * item.quantity}</td>
                        <td>
                            <a href="RemoveFromCartServlet?id=${item.productId}" class="btn-remove" onclick="return confirm('确定要移除该商品吗？')">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <div class="total-price">
                总计：￥${totalPrice}
            </div>

            <div class="actions">
                <a href="ProductListServlet" class="btn-back">← 继续购物</a>
                <a href="CheckoutServlet" class="btn-checkout" onclick="return confirm('确认结算购物车？')">💰 结算</a>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>