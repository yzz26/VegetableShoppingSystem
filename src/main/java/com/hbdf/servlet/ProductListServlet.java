package com.hbdf.servlet;

import com.hbdf.bean.Product;
import com.hbdf.dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 商品列表Servlet
 * 处理查询所有商品的请求【5分】
 */
@WebServlet("/ProductListServlet")
public class ProductListServlet extends HttpServlet {

    private ProductDao productDao = new ProductDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 调用Dao查询所有商品
        List<Product> products = productDao.getAllProducts();
        // 将数据存入request作用域
        request.setAttribute("productList", products);
        // 转发到JSP页面进行展示
        request.getRequestDispatcher("/products.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}