/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vistas;

import Conexion.ProductoRepositorio;
import Modelos.Producto;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 *
 * @author David
 */
public class RegistrarPedido extends javax.swing.JPanel {

    private DefaultListModel<String> modeloLista;

    public RegistrarPedido() {
        initComponents();
        popup.setFocusable(false);
        txtCantidad.setEditable(false);
        ProductoRepositorio productoRepo = new ProductoRepositorio();

        // Crear la lista y el modelo
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        JList<String> listaSugerencias = new JList<>(modeloLista);
        listaSugerencias.setFocusable(false);
        popup.add(new JScrollPane(listaSugerencias));

        // Evento: cuando se escribe en el campo de texto
        txtProducto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String texto = txtProducto.getText().trim();

                if (!texto.isEmpty()) {
                    modeloLista.clear();

                    List<Producto> resultados = productoRepo.buscarPorNombre(texto);
                    for (Producto p : resultados) {
                        modeloLista.addElement(p.getNombre());
                    }

                    if (!modeloLista.isEmpty()) {
                        listaSugerencias.setSelectedIndex(0);
                        popup.show(txtProducto, 0, txtProducto.getHeight());
                    } else {
                        popup.setVisible(false);
                    }
                } else {
                    popup.setVisible(false);
                }
            }
        });

        // Evento: cuando se hace doble clic en una sugerencia
        listaSugerencias.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String seleccionado = listaSugerencias.getSelectedValue();
                    txtProducto.setText(seleccionado);
                    popup.setVisible(false);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popup = new javax.swing.JPopupMenu();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nombreCliente = new javax.swing.JTextField();
        telefonoCliente = new javax.swing.JTextField();
        direccionCliente = new javax.swing.JTextField();
        botonRegistrarPedido = new javax.swing.JButton();
        dniCliente = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        txtProducto = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        btnmenos = new javax.swing.JButton();
        btnmas = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("DATOS DEL CLIENTE");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 4, 147, 38));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Nombre Completo:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 49, 127, 34));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Telefono:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 49, 97, 34));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Direccion:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 149, 92, 34));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("CANTIDAD");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 110, 30));
        add(nombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 89, 240, 50));
        add(telefonoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 89, 240, 50));
        add(direccionCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 179, 240, 51));

        botonRegistrarPedido.setBackground(new java.awt.Color(255, 153, 255));
        botonRegistrarPedido.setForeground(new java.awt.Color(255, 255, 255));
        botonRegistrarPedido.setText("REGISTRAR PEDIDO");
        add(botonRegistrarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 549, 248, 41));

        dniCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dniClienteActionPerformed(evt);
            }
        });
        add(dniCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 179, 240, 51));

        jLabel11.setText("DNI:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 159, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Categoria", "Producto", "Precio", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 475, 94));

        jButton1.setText("ELIMINAR");
        jButton1.setActionCommand("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 550, 117, -1));
        add(txtProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 170, 30));

        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 300, 90, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("PRODUCTO");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 147, 41));

        txtCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidad.setText("1");
        add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 300, 40, 30));

        btnmenos.setText("-");
        btnmenos.setToolTipText("");
        btnmenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmenosActionPerformed(evt);
            }
        });
        add(btnmenos, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, -1, -1));

        btnmas.setText("+");
        btnmas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmasActionPerformed(evt);
            }
        });
        add(btnmas, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 300, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void dniClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dniClienteActionPerformed

    }//GEN-LAST:event_dniClienteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnmasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmasActionPerformed
        int valor = Integer.parseInt(txtCantidad.getText());
        valor++; // aumentar
        txtCantidad.setText(String.valueOf(valor));
    }//GEN-LAST:event_btnmasActionPerformed

    private void btnmenosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmenosActionPerformed
        int valor = Integer.parseInt(txtCantidad.getText());
        if (valor > 1) { // mínimo 1
            valor--; // disminuir
            txtCantidad.setText(String.valueOf(valor));
        }
    }//GEN-LAST:event_btnmenosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonRegistrarPedido;
    private javax.swing.JButton btnmas;
    private javax.swing.JButton btnmenos;
    private javax.swing.JTextField direccionCliente;
    private javax.swing.JTextField dniCliente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nombreCliente;
    private javax.swing.JPopupMenu popup;
    private javax.swing.JTextField telefonoCliente;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtProducto;
    // End of variables declaration//GEN-END:variables
}
