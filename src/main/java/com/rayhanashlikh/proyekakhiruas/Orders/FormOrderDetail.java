/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.rayhanashlikh.proyekakhiruas.Orders;

import com.rayhanashlikh.proyekakhiruas.Customer.FormCustomer;
import com.rayhanashlikh.proyekakhiruas.FormLoginNew;
import com.rayhanashlikh.proyekakhiruas.KoneksiMySQL;
import com.rayhanashlikh.proyekakhiruas.Main.FormMain;
import com.rayhanashlikh.proyekakhiruas.Product.FormProducts;
import com.rayhanashlikh.proyekakhiruas.Seller.FormSellers;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Rayhan
 */
public class FormOrderDetail extends javax.swing.JFrame {

    Connection con;
    ResultSet rs;
    Statement stm;
    String current_id;
    
    private Object[][] dataTable = null;
    private String[] header
            = {"Nomor", "Nama Barang", "Satuan", "Jumlah Barang", "Berat Barang", "Harga"};
    
    /**
     * Creates new form AddOrder
     */
    public FormOrderDetail(String current_id) {
        initComponents();
        open_db();
        this.current_id = current_id;
        set_title();
        baca_data();
        titleLabel.setText("Detail Order Customer");
        subTitleLabel.setText("Admin/Order/Detail Order");
        navHome.setBackground(new java.awt.Color(8, 64, 214));
        navOrders.setBackground(new java.awt.Color(80, 117, 243));
        navOrders.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MouseListener[] mouseListeners = navOrders.getMouseListeners();
        for (MouseListener ml : mouseListeners) {
            navOrders.removeMouseListener(ml);
        }
        
        Color ivory = new Color(255, 255, 255);
        tblOrderDetails.setOpaque(true);
        tblOrderDetails.setFillsViewportHeight(true);
        tblOrderDetails.setBackground(ivory);
        tblOrderDetails.getTableHeader().setBackground(ivory);
    }
    
    private void open_db() {
        try {
            KoneksiMySQL kon = new KoneksiMySQL("localhost", "root",
                    "", "batikraft_new");
            con = kon.getConnection();
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }
    
    public FormOrderDetail() {
        initComponents();
    }
    
    private void set_title() {
        try {
            stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT u.nama AS nama_user FROM orders o "
                    + "INNER JOIN users u ON "
                    + "o.user_id=u.id "
                    + "WHERE o.id="+ current_id +";";
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("nama_user"));
                labelUsername.setText(rs.getString("nama_user"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void baca_data() {
        try {
            stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT "
                    + "op.id AS id, "
                    + "op.nama_barang AS nama_barang, "
                    + "b.satuan AS satuan, "
                    + "op.jumlah AS jumlah_barang, "
                    + "b.berat AS berat_per, "
                    + "op.harga AS harga_barang "
                    + "FROM order_products op "
                    + "INNER JOIN orders o ON "
                    + "op.order_id = o.id "
                    + "INNER JOIN barang b ON "
                    + "op.barang_id = b.id "
                    + "WHERE op.order_id ="+ current_id +";";
//            System.out.println(sql);
            rs = stm.executeQuery(sql);
            ResultSetMetaData meta = rs.getMetaData();
            int col = meta.getColumnCount();
            int baris = 0;
            while (rs.next()) {
                baris = rs.getRow();
            }
            dataTable = new Object[baris][col];
            int x = 0;
            rs.beforeFirst();
            while (rs.next()) {
                dataTable[x][0] = rs.getString("id");
                dataTable[x][1] = rs.getString("nama_barang");
                dataTable[x][2] = rs.getString("satuan");
                dataTable[x][3] = rs.getString("jumlah_barang");
                dataTable[x][4] = (rs.getInt("berat_per") * rs.getInt("jumlah_barang")) + " gram";
                dataTable[x][5] = "Rp" + (rs.getInt("harga_barang") * rs.getInt("jumlah_barang"));
                x++;
            }
            tblOrderDetails.setModel(new DefaultTableModel(dataTable, header));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        sidepane = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        navHome = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        navSeller = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        navOrders = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        navProducts = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        navCustomers = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        subTitleLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrderDetails = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        labelUsername = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidepane.setBackground(new java.awt.Color(8, 36, 161));
        sidepane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Admin Dashboard");
        sidepane.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 180, -1));

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        sidepane.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 200, 10));

        navHome.setBackground(new java.awt.Color(80, 117, 243));
        navHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                navHomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                navHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                navHomeMouseExited(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\Kuliah\\Kuliah\\Semester 4\\UAS\\ProyekAkhirUAS\\src\\icon\\Home.png")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Home");

        javax.swing.GroupLayout navHomeLayout = new javax.swing.GroupLayout(navHome);
        navHome.setLayout(navHomeLayout);
        navHomeLayout.setHorizontalGroup(
            navHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navHomeLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addContainerGap(179, Short.MAX_VALUE))
        );
        navHomeLayout.setVerticalGroup(
            navHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(navHomeLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        sidepane.add(navHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 290, 50));

        navSeller.setBackground(new java.awt.Color(8, 64, 214));
        navSeller.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navSeller.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                navSellerMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                navSellerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                navSellerMouseExited(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon("D:\\Kuliah\\Kuliah\\Semester 4\\UAS\\ProyekAkhirUAS\\src\\icon\\Small Business.png")); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Seller");

        javax.swing.GroupLayout navSellerLayout = new javax.swing.GroupLayout(navSeller);
        navSeller.setLayout(navSellerLayout);
        navSellerLayout.setHorizontalGroup(
            navSellerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navSellerLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel4)
                .addContainerGap(183, Short.MAX_VALUE))
        );
        navSellerLayout.setVerticalGroup(
            navSellerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navSellerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navSellerLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(14, 14, 14))
        );

        sidepane.add(navSeller, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 290, -1));

        navOrders.setBackground(new java.awt.Color(8, 64, 214));
        navOrders.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navOrders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                navOrdersMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                navOrdersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                navOrdersMouseExited(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon("D:\\Kuliah\\Kuliah\\Semester 4\\UAS\\ProyekAkhirUAS\\src\\icon\\Shopping Cart.png")); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Orders");

        javax.swing.GroupLayout navOrdersLayout = new javax.swing.GroupLayout(navOrders);
        navOrders.setLayout(navOrdersLayout);
        navOrdersLayout.setHorizontalGroup(
            navOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navOrdersLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel6)
                .addContainerGap(193, Short.MAX_VALUE))
        );
        navOrdersLayout.setVerticalGroup(
            navOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navOrdersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navOrdersLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(14, 14, 14))
        );

        sidepane.add(navOrders, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, -1, -1));

        navProducts.setBackground(new java.awt.Color(8, 64, 214));
        navProducts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                navProductsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                navProductsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                navProductsMouseExited(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon("D:\\Kuliah\\Kuliah\\Semester 4\\UAS\\ProyekAkhirUAS\\src\\icon\\Cardboard Box.png")); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Products");

        javax.swing.GroupLayout navProductsLayout = new javax.swing.GroupLayout(navProducts);
        navProducts.setLayout(navProductsLayout);
        navProductsLayout.setHorizontalGroup(
            navProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navProductsLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel11)
                .addContainerGap(193, Short.MAX_VALUE))
        );
        navProductsLayout.setVerticalGroup(
            navProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navProductsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navProductsLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(14, 14, 14))
        );

        sidepane.add(navProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, -1, -1));

        navCustomers.setBackground(new java.awt.Color(8, 64, 214));
        navCustomers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navCustomers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                navCustomersMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                navCustomersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                navCustomersMouseExited(evt);
            }
        });

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon("D:\\Kuliah\\Kuliah\\Semester 4\\UAS\\ProyekAkhirUAS\\src\\icon\\Users.png")); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Customers");

        javax.swing.GroupLayout navCustomersLayout = new javax.swing.GroupLayout(navCustomers);
        navCustomers.setLayout(navCustomersLayout);
        navCustomersLayout.setHorizontalGroup(
            navCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navCustomersLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel13)
                .addContainerGap(193, Short.MAX_VALUE))
        );
        navCustomersLayout.setVerticalGroup(
            navCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navCustomersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navCustomersLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(14, 14, 14))
        );

        sidepane.add(navCustomers, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, -1, -1));

        bg.add(sidepane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 620));

        jPanel1.setBackground(new java.awt.Color(81, 155, 232));

        subTitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        subTitleLabel.setForeground(new java.awt.Color(255, 255, 255));
        subTitleLabel.setText("Home");

        titleLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setText("Admin Dashboard");

        jLabel8.setIcon(new javax.swing.ImageIcon("D:\\Kuliah\\Kuliah\\Semester 4\\UAS\\ProyekAkhirUAS\\src\\icon\\Logout.png")); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setIcon(new javax.swing.ImageIcon("D:\\Kuliah\\Kuliah\\Semester 4\\UAS\\ProyekAkhirUAS\\src\\icon\\Back Arrow.png")); // NOI18N
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(358, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(subTitleLabel)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(52, 52, 52))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(subTitleLabel)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(titleLabel)
                .addGap(27, 27, 27))
        );

        bg.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 760, 140));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 51, 51));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("X");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        bg.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 0, 30, 30));

        tblOrderDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nama Customer", "Total Jumlah", "Total Berat", "Total Harga", "Tanggal Pesan", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOrderDetails.setGridColor(new java.awt.Color(204, 204, 204));
        tblOrderDetails.setRowHeight(25);
        tblOrderDetails.setShowHorizontalLines(true);
        tblOrderDetails.setShowVerticalLines(true);
        tblOrderDetails.getTableHeader().setResizingAllowed(false);
        tblOrderDetails.getTableHeader().setReorderingAllowed(false);
        tblOrderDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrderDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblOrderDetails);

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, 670, 310));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Detail Order User :");
        bg.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, 130, 30));

        labelUsername.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelUsername.setText("Username");
        bg.add(labelUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, 250, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 1031, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void navHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_navHomeMouseClicked
        FormMain main = new FormMain();
        main.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_navHomeMouseClicked

    private void navHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_navHomeMouseEntered
        setColor(navHome);
    }//GEN-LAST:event_navHomeMouseEntered

    private void navHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_navHomeMouseExited
        resetColor(navHome);
    }//GEN-LAST:event_navHomeMouseExited

    private void navSellerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_navSellerMouseClicked
        FormSellers seller = new FormSellers();
        seller.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_navSellerMouseClicked

    private void navSellerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_navSellerMouseEntered
        setColor(navSeller);
    }//GEN-LAST:event_navSellerMouseEntered

    private void navSellerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_navSellerMouseExited
        resetColor(navSeller);
    }//GEN-LAST:event_navSellerMouseExited

    private void navOrdersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_navOrdersMouseClicked
        FormOrders orders = new FormOrders();
        orders.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_navOrdersMouseClicked

    private void navOrdersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_navOrdersMouseEntered
        setColor(navOrders);
    }//GEN-LAST:event_navOrdersMouseEntered

    private void navOrdersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_navOrdersMouseExited
        resetColor(navOrders);
    }//GEN-LAST:event_navOrdersMouseExited

    private void navProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_navProductsMouseClicked
        FormProducts products = new FormProducts();
        products.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_navProductsMouseClicked

    private void navProductsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_navProductsMouseEntered
        setColor(navProducts);
    }//GEN-LAST:event_navProductsMouseEntered

    private void navProductsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_navProductsMouseExited
        resetColor(navProducts);
    }//GEN-LAST:event_navProductsMouseExited

    private void navCustomersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_navCustomersMouseClicked
        FormCustomer customer = new FormCustomer();
        customer.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_navCustomersMouseClicked

    private void navCustomersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_navCustomersMouseEntered
        setColor(navCustomers);
    }//GEN-LAST:event_navCustomersMouseEntered

    private void navCustomersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_navCustomersMouseExited
        resetColor(navCustomers);
    }//GEN-LAST:event_navCustomersMouseExited

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        FormLoginNew login = new FormLoginNew();
        login.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel15MouseClicked

    private void tblOrderDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrderDetailsMouseClicked
//        set_id();
    }//GEN-LAST:event_tblOrderDetailsMouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        FormOrders formOrders = new FormOrders();
        formOrders.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_jLabel16MouseClicked

    void setColor(JPanel panel) {
        panel.setBackground(new java.awt.Color(8, 98, 210));
    }

    void resetColor(JPanel panel) {
        panel.setBackground(new java.awt.Color(8, 64, 214));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormOrderDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormOrderDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormOrderDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormOrderDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormOrderDetail().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelUsername;
    private javax.swing.JPanel navCustomers;
    private javax.swing.JPanel navHome;
    private javax.swing.JPanel navOrders;
    private javax.swing.JPanel navProducts;
    private javax.swing.JPanel navSeller;
    private javax.swing.JPanel sidepane;
    private javax.swing.JLabel subTitleLabel;
    private javax.swing.JTable tblOrderDetails;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
