/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vistas;

import Conexion.DetallePedidoRepositorio;
import Conexion.PedidoRepositorio;
import Conexion.ProductoRepositorio;
import Modelos.DetallePedido;
import Modelos.PedidosProductos;
import Modelos.Producto;
import Modelos.Venta;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class RegistrarPedido extends javax.swing.JPanel {

    ProductoRepositorio productoRepo;
    private DefaultListModel<String> modeloLista;
// Lista global temporal para la venta
    private List<Venta> carrito;

    public RegistrarPedido() {
        initComponents();
        carrito = new ArrayList<>();
        popup.setFocusable(false);
        txtCantidad.setEditable(false);
        txtTotal.setEditable(false);
        productoRepo = new ProductoRepositorio();

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
        btnEliminar = new javax.swing.JButton();
        txtProducto = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        btnmenos = new javax.swing.JButton();
        btnmas = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

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
        botonRegistrarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarPedidoActionPerformed(evt);
            }
        });
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

        btnEliminar.setText("ELIMINAR");
        btnEliminar.setActionCommand("Agregar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 550, 117, -1));
        add(txtProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 170, 30));

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 300, 90, 30));

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

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Monto");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 490, 50, 20));
        add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 490, 70, 30));
    }// </editor-fold>//GEN-END:initComponents
    private void actualizarTotal() {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        double total = 0.0;

        for (int i = 0; i < modelo.getRowCount(); i++) {
            double precio = Double.parseDouble(modelo.getValueAt(i, 2).toString());
            int cantidad = Integer.parseInt(modelo.getValueAt(i, 3).toString());
            total += precio * cantidad;
        }

        txtTotal.setText(String.format("%.2f", total)); // mostrar con 2 decimales
    }

    private void dniClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dniClienteActionPerformed

    }//GEN-LAST:event_dniClienteActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String nombre = txtProducto.getText().trim();
        int cantidad = Integer.parseInt(txtCantidad.getText().trim());

        if (nombre.isEmpty() || cantidad <= 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto y cantidad válida");
            return;
        }

        // Buscar el producto en el repositorio
        List<Producto> encontrados = productoRepo.buscarPorNombre(nombre);
        if (encontrados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Producto no encontrado");
            return;
        }

        Producto p = encontrados.get(0); // tomar el primero que coincida
        int idEmpleado = 1; // ejemplo, lo puedes reemplazar por el usuario logeado
        int idProducto = p.getIdProducto();
        LocalDate fecha = LocalDate.now();

        // Crear objeto Venta y agregarlo a la lista temporal
        Venta v = new Venta(idEmpleado, idProducto, cantidad, fecha);
        carrito.add(v);

        // Mostrar en la tabla
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        boolean encontrado = false;

        // Si ya existe en la tabla, actualizar la cantidad
        for (int i = 0; i < modelo.getRowCount(); i++) {
            String nombreFila = modelo.getValueAt(i, 1).toString(); // columna Producto (índice 1)
            if (nombreFila.equals(p.getNombre())) {
                int cantidadActual = Integer.parseInt(modelo.getValueAt(i, 3).toString());
                modelo.setValueAt(cantidadActual + cantidad, i, 3);
                encontrado = true;
                break;
            }
        }

        // Si no estaba, agregar nuevo
        if (!encontrado) {
            modelo.addRow(new Object[]{p.getCategoria(), p.getNombre(), p.getPrecio(), cantidad});
        }

        // Limpiar campos
        txtProducto.setText("");
        txtCantidad.setText("1");
        txtProducto.requestFocus();

        // Actualizar total general
        actualizarTotal();
    }//GEN-LAST:event_btnAgregarActionPerformed

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

    private void botonRegistrarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarPedidoActionPerformed
        // Validar que haya productos en el carrito
        if (carrito.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Agrega al menos un producto antes de registrar el pedido.");
            return;
        }

        // Validar datos del cliente
        String nombre = nombreCliente.getText().trim();
        String dni = dniCliente.getText().trim();
        if (nombre.isEmpty() || dni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa los datos del cliente.");
            return;
        }

        String textoTotal = txtTotal.getText()
                .replace("S/", "")
                .replace("PEN", "")
                .replace(" ", "")
                .trim()
                .replace(",", "."); // reemplaza coma por punto

        System.out.println("Texto total procesado: '" + textoTotal + "'"); // para depurar

        double total = 0.0;
        try {
            total = Double.parseDouble(textoTotal);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "⚠️ Error al convertir el total. Valor recibido: " + txtTotal.getText()
                    + "\nTexto procesado: " + textoTotal);
            return;
        }
        LocalDate fecha = LocalDate.now();

        // 1️⃣ Crear pedido principal
        int idCliente = 1; // (puedes cambiarlo según el cliente seleccionado o buscado)
        PedidosProductos pedido = new PedidosProductos(idCliente, total, fecha);

        PedidoRepositorio pedidoRepo = new PedidoRepositorio();
        int idPedidoGenerado = pedidoRepo.insertarPedido(pedido);

        if (idPedidoGenerado == -1) {
            JOptionPane.showMessageDialog(this, "❌ Error al registrar el pedido principal.");
            return;
        }

        // 2️⃣ Registrar los detalles del pedido
        DetallePedidoRepositorio detalleRepo = new DetallePedidoRepositorio();
        System.out.println("🛒 Productos en carrito: " + carrito.size());

        for (Venta v : carrito) {
            Producto producto = productoRepo.buscarPorId(v.getIdProducto());
            System.out.println("➡️ Venta: " + v);
            if (producto != null) {
                DetallePedido detalle = new DetallePedido(
                        idPedidoGenerado,
                        v.getIdProducto(),
                        v.getCantidad(),
                        producto.getPrecio()
                );
                System.out.println("🧾 Insertando detalle -> PedidoID: " + idPedidoGenerado
                        + ", ProductoID: " + v.getIdProducto()
                        + ", Cantidad: " + v.getCantidad());
                if (producto == null) {
                    System.out.println("⚠️ Producto no encontrado con id: " + v.getIdProducto());
                    continue;
                }
                detalleRepo.insertarDetalle(detalle);
            }
        }

        // 3️⃣ Mostrar mensaje y limpiar interfaz
        JOptionPane.showMessageDialog(this, "✅ Pedido registrado correctamente.");

        carrito.clear();
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);
        txtTotal.setText("");
        txtProducto.setText("");
        txtCantidad.setText("1");
    }//GEN-LAST:event_botonRegistrarPedidoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonRegistrarPedido;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnmas;
    private javax.swing.JButton btnmenos;
    private javax.swing.JTextField direccionCliente;
    private javax.swing.JTextField dniCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nombreCliente;
    private javax.swing.JPopupMenu popup;
    private javax.swing.JTextField telefonoCliente;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
