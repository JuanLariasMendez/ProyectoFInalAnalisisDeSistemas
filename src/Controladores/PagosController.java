package Controladores;

import Modelos.Conexion;
import Modelos.PagosModel;
import Vistas.frmPagos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Pit
 */
public class PagosController implements ActionListener {

    static String aux = null;
    Connection conex;
    frmPagos VistaPagos;
    PagosModel ModeloPagos;

    public PagosController(frmPagos VistaPagos, PagosModel ModeloPagos) {
        this.VistaPagos = VistaPagos;
        this.ModeloPagos = ModeloPagos;
        this.VistaPagos.btnPagoPagar.addActionListener(this);
        this.VistaPagos.comboBoxPagos.addActionListener(this);
        this.VistaPagos.btnEliminar.addActionListener(this);
        rellenar();
        this.VistaPagos.tablaCliente.setModel(this.ModeloPagos.MostrarClientes());
        this.VistaPagos.setLocationRelativeTo(null);
        this.VistaPagos.setVisible(true);
    }

    public void rellenar() {
        conex = Conexion.conn;
        try {
            Statement st = conex.createStatement();
            ResultSet rs = st.executeQuery("Select * from clientesgym");
            while (rs.next()) {
                VistaPagos.comboBoxPagos.addItem(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO se pueden mostrar los Clientes | " + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.VistaPagos.btnPagoPagar) {
            try {
                this.ModeloPagos.ingresarPagos(DateFormat.getDateInstance().format(this.VistaPagos.jDateChooser1.getDate()),
                        Integer.parseInt(this.VistaPagos.txtPagoCantidad.getText()),
                        this.VistaPagos.comboBoxMes.getSelectedItem().toString(),
                        Integer.parseInt(this.VistaPagos.comboBoxPagos.getSelectedItem().toString()));

                //this.VistaPagos.tablaCliente.setModel(this.ModeloPagos.MostrarPagos());
                this.VistaPagos.jDateChooser1.setCalendar(null);
                this.VistaPagos.txtPagoCantidad.setText("");
                this.VistaPagos.comboBoxMes.setSelectedIndex(0);
                this.VistaPagos.comboBoxPagos.setSelectedIndex(0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "NO se pudo ingresar los pagos | " + ex);
            }
        }
        if (e.getSource() == this.VistaPagos.comboBoxPagos) {
            try {
                Statement st = conex.createStatement();
                ResultSet rs = st.executeQuery("Select * from clientesgym where id_cliente='" + this.VistaPagos.comboBoxPagos.getSelectedItem().toString() + "'");
                while (rs.next()) {
                    this.VistaPagos.lblPagosNombre.setText(rs.getString(2));
                    aux = rs.getString(2);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se pudo mostrar al Usuario | " + ex);
            }
        }
        if (e.getSource() == this.VistaPagos.btnEliminar) {
            try {
                int resp = JOptionPane.showConfirmDialog(null, "¿De verdad desea elminar al usuario " + aux + "?", "Eliminacion en proceso...", JOptionPane.OK_OPTION, JOptionPane.OK_CANCEL_OPTION);
                if (resp == 0) {
                    this.ModeloPagos.EliminarCliente(Integer.parseInt(this.VistaPagos.comboBoxPagos.getSelectedItem().toString()));
                    JOptionPane.showMessageDialog(null, "El usuario y sus pagos se eliminaron correctamente");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar al Usuario | " + ex);
            }
        }
    }
}
