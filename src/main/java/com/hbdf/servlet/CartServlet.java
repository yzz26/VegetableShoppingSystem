package com.hbdf.servlet;

import com.hbdf.bean.CartItem;
import com.hbdf.dao.CartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 购物车查看Servlet
 * 处理查看购物车的请求
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

    private CartDao cartDao = new CartDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取购物车商品列表
        List<CartItem> cartItems = cartDao.getCartItems();
        // 计算总价
        double totalPrice = cartDao.getTotalPrice();

        request.setAttribute("cartItems", cartItems);
        request.setAttribute("totalPrice", totalPrice);
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}