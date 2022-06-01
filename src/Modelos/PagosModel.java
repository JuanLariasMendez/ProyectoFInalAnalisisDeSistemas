package Modelos;

import Vistas.frmPagos;
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
public class PagosModel {
    Connection conex;
    frmPagos VistaPagos;
    
    public void ingresarPagos(String fecha, float cantidad, String mesPagado, int noCliente) throws SQLException{
        conex = Conexion.conn;
        conex.setAutoCommit(false);
        Statement comando = conex.createStatement();
        comando.execute("insert into estudiante.pagosgym values(null,'" + fecha + "','" + cantidad + "','" + mesPagado + "','" + noCliente + "')");
        conex.commit();
        JOptionPane.showMessageDialog(null, "Pago Realizado Correctamente");
    }
    
    public DefaultTableModel MostrarClientes()
    {
        DefaultTableModel ModeloEdificios = new DefaultTableModel();
        ModeloEdificios.addColumn("ID Pago");
        ModeloEdificios.addColumn("Fecha del Pago");
        ModeloEdificios.addColumn("Cantidad");
        ModeloEdificios.addColumn("Mes Pagado");
        ModeloEdificios.addColumn("No. Cliente");
        //ModeloEdificios.addColumn("Nombre");
        conex = Conexion.conn;
        try
        {
         Statement st = conex.createStatement();
        ResultSet rs = st.executeQuery("Select * from pagosgym");
        
        while(rs.next())
        {
          ModeloEdificios.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
        }
        }
        catch(Exception e)
        {
         JOptionPane.showMessageDialog(null, "NO se pueden mostrar los Pagos");
        }
        
        return ModeloEdificios;
    }
    
    public void EliminarCliente (int idcliente) throws SQLException{
        try{
            conex = Conexion.conn;
            conex.setAutoCommit(false);
            Statement comando = conex.createStatement();
            comando.execute("delete from pagosgym where no_cliente = '" + idcliente + "'");
            comando.execute("delete from clientesgym where id_cliente = '" + idcliente + "'");
            conex.commit();
        }catch(Exception e){
            conex.rollback();
        }
    }
}
