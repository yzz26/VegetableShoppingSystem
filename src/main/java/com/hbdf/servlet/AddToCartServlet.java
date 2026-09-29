package com.hbdf.servlet;

import com.hbdf.bean.CartItem;
import com.hbdf.bean.Product;
import com.hbdf.dao.CartDao;
import com.hbdf.dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 添加到购物车Servlet
 * 处理将商品加入购物车的请求【5分】
 */
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {

    private ProductDao productDao = new ProductDao();
    private CartDao cartDao = new CartDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取商品ID
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int productId = Integer.parseInt(idParam);
                // 查询商品信息
                Product product = productDao.getProductById(productId);

                if (product != null) {
                    // 创建购物车条目
                    CartItem cartItem = new CartItem();
                    cartItem.setProductId(product.getId());
                    cartItem.setProductName(product.getName());
                    cartItem.setPrice(product.getPrice());
                    cartItem.setQuantity(1); // 默认添加1斤

                    // 调用Dao添加到购物车
                    cartDao.addToCart(cartItem);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // 重定向到商品列表页面
        response.sendRedirect("ProductListServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}