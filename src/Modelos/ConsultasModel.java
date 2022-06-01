package Modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsultasModel {
    Connection conex;
    
    public DefaultTableModel MostrarClientes()
    {
        DefaultTableModel ModeloEdificios = new DefaultTableModel();
        ModeloEdificios.addColumn("Id ");
        ModeloEdificios.addColumn("Nombre");
        ModeloEdificios.addColumn("Apellido");
        ModeloEdificios.addColumn("Fecha Inscripcion");
        ModeloEdificios.addColumn("Mes Pagado");
        ModeloEdificios.addColumn("Fecha de Pago");
        conex = Conexion.conn;
        try
        {
         Statement st = conex.createStatement();
        ResultSet rs = st.executeQuery("Select clientesgym.id_Cliente, clientesgym.nombre, clientesgym.apellidos, clientesgym.fecha_Inscripcion, pagosgym.mes_pagado, pagosgym.fecha_pago from clientesgym, pagosgym WHERE clientesgym.id_cliente = pagosgym.no_cliente");
        
        while(rs.next())
        {
          ModeloEdificios.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
        }
        }
        catch(Exception e)
        {
         JOptionPane.showMessageDialog(null, "NO se pueden mostrar los Pagos");
        }
        
        return ModeloEdificios;
    }
}
