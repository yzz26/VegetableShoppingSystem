# 蔬菜购物 WEB 系统（VegetableShoppingSystem）

基于 JSP + Servlet + JavaBean + DAO + MySQL 的蔬菜购物 Web 应用，是《基于Web的大数据应用开发》课程项目。

## 📖 项目简介

本项目实现了一个蔬菜购物网站的核心功能，商品首页从数据库中动态读取蔬菜信息，支持按名称搜索、加入购物车、删除购物车商品以及模拟结算。项目采用 MVC 分层架构，使用 Maven 管理依赖，部署在 Tomcat 服务器上。

## ✨ 功能特性

- 🥬 商品首页：从 MySQL 动态读取不少于 30 条蔬菜商品信息
- 🔍 商品搜索：根据商品名称进行模糊查询
- 🛒 加入购物车：将商品添加到购物车，相同商品自动累加数量
- 🗑️ 删除商品：从购物车中移除指定商品
- 💰 购物车结算：模拟结算，清空购物车并显示总金额
- 🌐 中文编码统一：全站 UTF-8，避免乱码

## 🧰 技术栈

| 层次 | 技术 |
|------|------|
| 视图层 | JSP、HTML、CSS、JSTL |
| 控制层 | Servlet |
| 模型层 | JavaBean、DAO、JDBC |
| 数据库 | MySQL 8.0 |
| 服务器 | Apache Tomcat 9.0 |
| 构建工具 | Maven |
| 开发工具 | IntelliJ IDEA |

## 📁 项目结构

VegetableShoppingSystem/
├── src/
│   └── main/
│       ├── java/com/hbdf/
│       │   ├── bean/        # Product、CartItem
│       │   ├── dao/         # ProductDao、CartDao
│       │   ├── servlet/     # 各功能 Servlet
│       │   └── util/        # DBUtil 数据库工具类
│       ├── resources/
│       │   └── db.properties.example
│       └── webapp/
│           ├── products.jsp # 商品列表
│           ├── cart.jsp     # 购物车
│           ├── result.jsp   # 结算结果
│           └── WEB-INF/web.xml
└── pom.xml


## 🗄️ 数据库设计

数据库名：`vegetable_db`

### product 表（商品表）

| 字段 | 类型 | 说明 |
|------|------|------|
| id | INT | 主键，自增 |
| name | VARCHAR(50) | 商品名称 |
| price | DECIMAL(10,2) | 价格（元/斤） |
| stock | INT | 库存（斤） |
| category | VARCHAR(30) | 分类 |

### cart 表（购物车表）

| 字段 | 类型 | 说明 |
|------|------|------|
| id | INT | 主键，自增 |
| product_id | INT | 商品编号 |
| product_name | VARCHAR(50) | 商品名称 |
| price | DECIMAL(10,2) | 单价 |
| quantity | INT | 数量 |

## ⚙️ 环境要求

- JDK 17 或 1.8
- Apache Tomcat 9.0
- MySQL 8.0
- Maven 3.8+
- IntelliJ IDEA（推荐 Ultimate）

## 🚀 快速开始

1. **创建数据库并导入数据**
   ```sql
   CREATE DATABASE vegetable_db DEFAULT CHARACTER SET utf8mb4;
   USE vegetable_db;
   -- 执行项目中的建表与数据插入 SQL

2. **配置数据库连接**

复制 src/main/resources/db.properties.example 为 db.properties，修改你的数据库账号密码：

driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/vegetable_db?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8
user=root
password=你的密码

3. **配置 Tomcat 并部署**
在 IDEA 中配置 Tomcat，部署 VegetableShoppingSystem:war exploded。

4. **访问系统**

http://localhost:8080/VegetableShoppingSystem_war_exploded/ProductListServlet

作者
GitHub：@yzz26

课程：《基于Web的大数据应用开发》

指导教师：张俊刚、刘子赫
