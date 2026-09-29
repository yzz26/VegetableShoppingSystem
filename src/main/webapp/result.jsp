<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>操作结果 - 蔬菜购物系统</title>
    <style>
        body {
            font-family: "Microsoft YaHei", sans-serif;
            background: #f5f5f5;
            margin: 0;
            padding: 20px;




































































































































































































































































































            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .container {
            max-width: 600px;
            width: 100%;
            background: white;
            padding: 50px 40px;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .icon {
            font-size: 72px;
            margin-bottom: 20px;
            display: block;
        }
        .message {
            font-size: 22px;
            color: #333;
            margin-bottom: 15px;
            line-height: 1.6;
            word-break: break-all;
        }
        .sub-message {
            font-size: 14px;
            color: #999;
            margin-bottom: 35px;
            border-top: 1px solid #eee;
            padding-top: 20px;
        }
        .actions {
            display: flex;
            justify-content: center;
            gap: 20px;
            flex-wrap: wrap;
        }
        .btn {
            display: inline-block;
            padding: 12px 35px;
            border-radius: 6px;
            text-decoration: none;
            font-size: 16px;
            font-weight: bold;
            transition: background 0.3s ease;
            min-width: 140px;
        }
        .btn-primary {
            background: #4caf50;
            color: white;
        }
        .btn-primary:hover {
            background: #388e3c;
        }
        .btn-warning {
            background: #ff9800;
            color: white;
        }
        .btn-warning:hover {
            background: #f57c00;
        }
        .btn-secondary {
            background: #2196f3;
            color: white;
        }
        .btn-secondary:hover {
            background: #1976d2;
        }
        /* 根据消息类型动态显示颜色（后台传参控制，或前端根据关键词匹配） */
        .msg-success { color: #2e7d32; }
        .msg-error { color: #c62828; }
        .msg-info { color: #0d47a1; }
    </style>
</head>
<body>
<div class="container">
    <%
        // 从 request 中获取消息，并判断类型以显示不同图标
        String msg = (String) request.getAttribute("message");
        String icon = "ℹ️"; // 默认信息图标
        String cssClass = "msg-info";

        if (msg != null) {
            if (msg.contains("成功") || msg.contains("感谢")) {
                icon = "🎉";
                cssClass = "msg-success";
            } else if (msg.contains("失败") || msg.contains("错误") || msg.contains("无法")) {
                icon = "❌";
                cssClass = "msg-error";
            } else if (msg.contains("空")) {
                icon = "🛒";
                cssClass = "msg-info";
            }
        }
        request.setAttribute("icon", icon);
        request.setAttribute("cssClass", cssClass);
    %>

    <span class="icon">${icon}</span>

    <div class="message ${cssClass}">
        ${empty message ? '操作已完成' : message}
    </div>

    <div class="sub-message">
        <c:if test="${not empty message}">
            <c:choose>
                <c:when test="${message.contains('成功')}">
                    您的订单已处理，欢迎再次光临！
                </c:when>
                <c:otherwise>
                    您可以继续浏览商品或返回购物车。
                </c:otherwise>
            </c:choose>
        </c:if>
    </div>

    <div class="actions">
        <a href="ProductListServlet" class="btn btn-primary">🌿 继续购物</a>
        <a href="CartServlet" class="btn btn-warning">🛒 查看购物车</a>
    </div>
</div>
</body>
</html>