package com.hbdf.servlet;

import com.hbdf.dao.CartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 结算Servlet
 * 处理购物车结算请求【5分】
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {

    private CartDao cartDao = new CartDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取总金额
        double totalPrice = cartDao.getTotalPrice();

        if (totalPrice > 0) {
            // 模拟结算：清空购物车
            cartDao.clearCart();
            // 设置成功信息
            request.setAttribute("message", "🎉 结算成功！感谢您的购买，总金额：￥" + String.format("%.2f", totalPrice));
        } else {
            request.setAttribute("message", "⚠️ 购物车为空，无法结算");
        }

        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}