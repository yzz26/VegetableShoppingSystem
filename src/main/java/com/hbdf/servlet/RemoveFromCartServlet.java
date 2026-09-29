package com.hbdf.servlet;

import com.hbdf.dao.CartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 从购物车删除Servlet
 * 处理从购物车中删除商品的请求【5分】
 */
@WebServlet("/RemoveFromCartServlet")
public class RemoveFromCartServlet extends HttpServlet {

    private CartDao cartDao = new CartDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取商品ID
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int productId = Integer.parseInt(idParam);
                // 调用Dao删除购物车中的商品
                cartDao.removeFromCart(productId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // 重定向到购物车页面
        response.sendRedirect("CartServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}