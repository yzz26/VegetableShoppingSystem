package com.hbdf.dao;

import com.hbdf.bean.Product;
import com.hbdf.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品数据访问对象
 * 负责商品相关的数据库操作
 */
public class ProductDao {

    /**
     * 查询所有商品
     * @return 商品列表
     */
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, name, price, stock, category FROM product";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setCategory(rs.getString("category"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, stmt, conn);
        }
        return products;
    }

    /**
     * 根据商品名称模糊查询
     * @param keyword 搜索关键词
     * @return 匹配的商品列表
     */
    public List<Product> searchProductsByName(String keyword) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, name, price, stock, category FROM product WHERE name LIKE ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setCategory(rs.getString("category"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return products;
    }

    /**
     * 根据ID查询单个商品
     * @param id 商品编号
     * @return 商品对象
     */
    public Product getProductById(int id) {
        String sql = "SELECT id, name, price, stock, category FROM product WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setCategory(rs.getString("category"));
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return null;
    }
}