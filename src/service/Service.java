/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.product.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

/**
 *
 * @author user
 */
public class Service {
    Connection con;
    
    public int save(Product p) throws SQLException{
        con = DBConnection.getConnection();
        String sql = "INSERT INTO products1 (pcode1, pname1, qty1, price1) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, p.getCode());
        ps.setString(2, p.getName());
        ps.setInt(3, p.getQty());
        ps.setDouble(4, p.getPrice());
        
        int status = ps.executeUpdate();
        return status;  
    }
    
    public int update(Product p) throws SQLException{
        con = DBConnection.getConnection();
        String sql = "update products1 set pname1 = ?, qty1 = ? , price1 = ? where pcode1 = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, p.getName());
        ps.setInt(2, p.getQty());
        ps.setDouble(3, p.getPrice());
        ps.setInt(4, p.getCode());
        int status = ps.executeUpdate();
        return status;
    }
    
    public int delete(int code) throws SQLException{
        con = DBConnection.getConnection();
        String sql = "delete from products1 where pcode1 = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, code);
        int status = ps.executeUpdate();
        return status;
    }
    
    public Product getByID(int code) throws SQLException{
        con = DBConnection.getConnection();
        String sql = "select * from products1 where pcode1 = (?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, code);
        ResultSet rs = ps.executeQuery();
        Product p = new Product();
        while(rs.next()){
            p.setName(rs.getString("pname1"));
            p.setQty(rs.getInt("qty1"));
            p.setPrice(rs.getDouble("price1"));
        }
        return p;
    }

    public List<Product> getAll() throws SQLException{
        con = DBConnection.getConnection();
        String sql = "select * from products1";
        PreparedStatement ps = con.prepareStatement(sql);
        List products1 = new ArrayList();
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Product p = new Product();
            p.setCode(rs.getInt("pcode1"));
            p.setName(rs.getString("pname1"));
            p.setQty(rs.getInt("qty1"));
            p.setPrice(rs.getDouble("price1"));
            products1.add(p);
        }
        return products1;
    }
}
