package com.hbdf.dao;

import com.hbdf.bean.CartItem;
import com.hbdf.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车数据访问对象
 * 负责购物车相关的数据库操作（添加和删除）
 */
public class CartDao {

    /**
     * 添加商品到购物车【5分】
     * @param cartItem 购物车条目
     * @return 是否添加成功
     */
    public boolean addToCart(CartItem cartItem) {
        // 先检查该商品是否已在购物车中
        String checkSql = "SELECT quantity FROM cart WHERE product_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            // 检查是否存在
            pstmt = conn.prepareStatement(checkSql);
            pstmt.setInt(1, cartItem.getProductId());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // 已存在，更新数量
                int existingQty = rs.getInt("quantity");
                String updateSql = "UPDATE cart SET quantity = ? WHERE product_id = ?";
                pstmt = conn.prepareStatement(updateSql);
                pstmt.setInt(1, existingQty + cartItem.getQuantity());
                pstmt.setInt(2, cartItem.getProductId());
                return pstmt.executeUpdate() > 0;
            } else {
                // 不存在，插入新记录
                String insertSql = "INSERT INTO cart (product_id, product_name, price, quantity) VALUES (?, ?, ?, ?)";
                pstmt = conn.prepareStatement(insertSql);
                pstmt.setInt(1, cartItem.getProductId());
                pstmt.setString(2, cartItem.getProductName());
                pstmt.setDouble(3, cartItem.getPrice());
                pstmt.setInt(4, cartItem.getQuantity());
                return pstmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
    }

    /**
     * 从购物车中删除商品【5分】
     * @param productId 商品编号
     * @return 是否删除成功
     */
    public boolean removeFromCart(int productId) {
        String sql = "DELETE FROM cart WHERE product_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(pstmt, conn);
        }
    }

    /**
     * 获取购物车所有商品
     * @return 购物车商品列表
     */
    public List<CartItem> getCartItems() {
        List<CartItem> items = new ArrayList<>();
        String sql = "SELECT product_id, product_name, price, quantity FROM cart";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                CartItem item = new CartItem();
                item.setProductId(rs.getInt("product_id"));
                item.setProductName(rs.getString("product_name"));
                item.setPrice(rs.getDouble("price"));
                item.setQuantity(rs.getInt("quantity"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, stmt, conn);
        }
        return items;
    }

    /**
     * 清空购物车（结算后调用）
     */
    public boolean clearCart() {
        String sql = "DELETE FROM cart";
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            return stmt.executeUpdate(sql) >= 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(stmt, conn);
        }
    }

    /**
     * 计算购物车总金额
     * @return 总金额
     */
    public double getTotalPrice() {
        String sql = "SELECT SUM(price * quantity) AS total FROM cart";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, stmt, conn);
        }
        return 0.0;
    }
}