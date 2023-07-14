/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rayhanashlikh.proyekakhiruas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rayhan
 */
public class KoneksiMySQL {

    String url, usr, pwd, dbn;

    public KoneksiMySQL(String dbn) {
        this.url = "jdbc:mysql://localhost/" + dbn;
        this.usr = "root";
        this.pwd = "";
    }

    public KoneksiMySQL(String host, String user, String pass,
            String dbn) {
        this.url = "jdbc:mysql://" + host + "/" + dbn;
        this.usr = user;
        this.pwd = pass;
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(this.url, this.usr,
                    this.pwd);
//            System.out.println("Mantab");
        } catch (ClassNotFoundException e) {
            System.out.println("Error #1 : " + e.getMessage());
            System.exit(0);
        } catch (SQLException e) {
            System.out.println("Error #2 : " + e.getMessage());
            System.exit(0);
        }
        return con;
    }

    public static void main(String args[]) {
        try {
            KoneksiMySQL kon = new KoneksiMySQL("batikraft_new");
            Connection c = kon.getConnection();
            System.out.println(c.isValid(0));
        } catch (SQLException ex) {
            Logger.getLogger(KoneksiMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
