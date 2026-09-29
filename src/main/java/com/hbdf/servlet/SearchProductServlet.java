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
 * 商品搜索Servlet
 * 处理根据商品名称搜索的请求【5分】
 */
@WebServlet("/SearchProductServlet")
public class SearchProductServlet extends HttpServlet {

    private ProductDao productDao = new ProductDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取搜索关键词
        String keyword = request.getParameter("keyword");

        List<Product> products;
        if (keyword != null && !keyword.trim().isEmpty()) {
            // 调用Dao进行模糊查询
            products = productDao.searchProductsByName(keyword.trim());
        } else {
            // 关键词为空时查询所有商品
            products = productDao.getAllProducts();
        }

        request.setAttribute("productList", products);
        request.getRequestDispatcher("/products.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}