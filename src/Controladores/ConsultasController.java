package Controladores;

import Modelos.ConsultasModel;
import Vistas.frmConsultas;
import java.sql.Connection;

public class ConsultasController {

    Connection conex;
    frmConsultas VistaConsultas;
    ConsultasModel ModeloConsultas;

    public ConsultasController(frmConsultas VistaConsultas, ConsultasModel ModeloConsultas) {
        this.VistaConsultas = VistaConsultas;
        this.VistaConsultas.jTable1.setModel(ModeloConsultas.MostrarClientes());
        this.VistaConsultas.setLocationRelativeTo(null);
        this.VistaConsultas.setVisible(true);
    }

}
