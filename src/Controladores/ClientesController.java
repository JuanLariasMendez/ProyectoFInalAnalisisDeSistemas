/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.ClientesModel;
import Vistas.frmClientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.DateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Pit
 */
public class ClientesController implements ActionListener {

    Connection conex;
    frmClientes VistaClientes;
    ClientesModel ModeloClientes;

    public ClientesController(frmClientes VistaClientes, ClientesModel ModeloClientes) {
        this.VistaClientes = VistaClientes;
        this.ModeloClientes = ModeloClientes;
        this.VistaClientes.tablaCliente.setModel(this.ModeloClientes.MostrarClientes());
        this.VistaClientes.btnClienteCrear.addActionListener(this);
        this.VistaClientes.btnClienteActualizar.addActionListener(this);
        this.VistaClientes.setLocationRelativeTo(null);
        this.VistaClientes.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.VistaClientes.btnClienteCrear) {
            try {
                this.ModeloClientes.ingresarClientes(this.VistaClientes.txtClienteNombre.getText(),
                        this.VistaClientes.txtClienteApellido.getText(),
                        this.VistaClientes.txtClienteTelefono.getText(),
                        this.VistaClientes.txtClienteDPI.getText(),
                        DateFormat.getDateInstance().format(this.VistaClientes.jDateChooser1.getDate()));
                this.VistaClientes.tablaCliente.setModel(this.ModeloClientes.MostrarClientes());
                this.VistaClientes.txtClienteNombre.setText("");
                this.VistaClientes.txtClienteApellido.setText("");
                this.VistaClientes.txtClienteDPI.setText("");
                this.VistaClientes.jDateChooser1.setCalendar(null);
                this.VistaClientes.txtClienteTelefono.setText("");
                this.VistaClientes.txtClienteNombre.requestFocus();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se pudo guardar al Cliente | " + ex);
            }
        }
        if (e.getSource() == this.VistaClientes.btnClienteActualizar) {
            try {
                int idCliente = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del cliente"));
                this.ModeloClientes.ActualizarClientes(this.VistaClientes.txtClienteNombre.getText(),
                        this.VistaClientes.txtClienteApellido.getText(),
                        this.VistaClientes.txtClienteTelefono.getText(),
                        this.VistaClientes.txtClienteDPI.getText(),
                        DateFormat.getDateInstance().format(this.VistaClientes.jDateChooser1.getDate()),
                        idCliente);

                this.VistaClientes.tablaCliente.setModel(this.ModeloClientes.MostrarClientes());
                this.VistaClientes.tablaCliente.setModel(this.ModeloClientes.MostrarClientes());
                this.VistaClientes.txtClienteNombre.setText("");
                this.VistaClientes.txtClienteApellido.setText("");
                this.VistaClientes.txtClienteDPI.setText("");
                this.VistaClientes.txtClienteTelefono.setText("");
                this.VistaClientes.jDateChooser1.setCalendar(null);
                this.VistaClientes.txtClienteNombre.requestFocus();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar al Cliente | " + ex);
            }
        }
    }

}
