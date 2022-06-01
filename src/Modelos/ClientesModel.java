/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Vistas.frmClientes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pit
 */
public class ClientesModel {
    Connection conex;
    frmClientes VistaClientes;
    
    public void ingresarClientes(String nombre, String apellidos, String telefono, String dpi, String fecha) throws SQLException{
        conex = Conexion.conn;
        conex.setAutoCommit(false);
        Statement comando = conex.createStatement();
        comando.execute("insert into estudiante.clientesgym values(null,'" + nombre + "','" + apellidos + "','" + telefono + "','" + dpi + "','" + fecha +"')");
        conex.commit();
        JOptionPane.showMessageDialog(null, "Cliente Agregado Correctamente");
    }
    
    public void ActualizarClientes(String nombre, String apellidos, String telefono, String dpi, String fecha, int idcliente) throws SQLException{
        try{
        conex = Conexion.conn;
        conex.setAutoCommit(false);
        Statement comando = conex.createStatement();
        comando.execute("update estudiante.clientesgym set nombre = '" + nombre + "', apellidos = '" + apellidos + "', telefono = '" + telefono + "', dpi = '" + dpi + "', fecha_Inscripcion = '" + fecha +"' WHERE id_cliente='"+ idcliente + "'");
        conex.commit();
        JOptionPane.showMessageDialog(null, "Cliente Actualizado Correctamente");
        }catch(Exception e){
            conex.rollback();
        }
    }
    
    public DefaultTableModel MostrarClientes()
    {
        DefaultTableModel ModeloEdificios = new DefaultTableModel();
        ModeloEdificios.addColumn("ID Cliente");
        ModeloEdificios.addColumn("Nombre");
        ModeloEdificios.addColumn("Apellidos");
        ModeloEdificios.addColumn("Telefono");
        ModeloEdificios.addColumn("DPI");
        ModeloEdificios.addColumn("Fecha de Inscripcion");
        conex = Conexion.conn;
        try
        {
         Statement st = conex.createStatement();
        ResultSet rs = st.executeQuery("Select * from clientesgym");
        
        while(rs.next())
        {
          ModeloEdificios.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
        }
        }
        catch(Exception e)
        {
         JOptionPane.showMessageDialog(null, "NO se pueden mostrar los Clientes");
        }
        
        return ModeloEdificios;
    }
}