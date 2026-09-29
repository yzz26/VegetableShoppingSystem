<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>蔬菜购物系统 - 商品列表</title>
    <style>
        body { font-family: "Microsoft YaHei", sans-serif; background: #f5f5f5; margin: 0; padding: 20px; }
        .container { max-width: 1200px; margin: 0 auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        h1 { color: #2e7d32; text-align: center; }
        .search-box { text-align: center; margin: 20px 0; }
        .search-box input { padding: 10px 15px; width: 300px; border: 2px solid #4caf50; border-radius: 4px; font-size: 16px; }
        .search-box button { padding: 10px 30px; background: #4caf50; color: white; border: none; border-radius: 4px; font-size: 16px; cursor: pointer; }
        .search-box button:hover { background: #388e3c; }
        table { width: 100%; border-collapse: collapse; margin: 20px 0; }
        th { background: #4caf50; color: white; padding: 12px; }
        td { padding: 10px; text-align: center; border-bottom: 1px solid #e0e0e0; }
        tr:hover { background: #f1f8e9; }
        .btn-add { background: #4caf50; color: white; padding: 6px 16px; text-decoration: none; border-radius: 4px; font-size: 14px; }
        .btn-add:hover { background: #388e3c; }
        .btn-cart { display: inline-block; margin-top: 10px; padding: 10px 25px; background: #ff9800; color: white; text-decoration: none; border-radius: 4px; }
        .btn-cart:hover { background: #f57c00; }
        .total-count { text-align: right; color: #666; font-size: 14px; }
    </style>
</head>
<body>
<div class="container">
    <h1>🌿 新鲜蔬菜商城</h1>

    <!-- 搜索功能 -->
    <div class="search-box">
        <form action="SearchProductServlet" method="get">
            <input type="text" name="keyword" placeholder="请输入蔬菜名称搜索..." value="${param.keyword}">
            <button type="submit">搜索</button>
            <a href="ProductListServlet" style="margin-left:10px;color:#4caf50;text-decoration:none;">显示全部</a>
        </form>
    </div>

    <!-- 购物车入口 -->
    <div class="total-count">
        <a href="CartServlet" class="btn-cart">🛒 查看购物车</a>
    </div>

    <!-- 商品列表 -->
    <table>
        <tr>
            <th>编号</th>
            <th>商品名称</th>
            <th>价格（元/斤）</th>
            <th>库存（斤）</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${productList}" var="product">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>￥${product.price}</td>
                <td>${product.stock}</td>
                <td>
                    <a href="AddToCartServlet?id=${product.id}" class="btn-add">加入购物车</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <c:if test="${empty productList}">
        <p style="text-align:center;color:#999;padding:30px;">暂无商品数据</p>
    </c:if>
</div>
</body>
</html>