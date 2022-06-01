/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.ClientesModel;
import Modelos.ConsultasModel;
import Modelos.PagosModel;
import Vistas.frmClientes;
import Vistas.frmConsultas;
import Vistas.frmPagos;
import Vistas.frmPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author dmiranda
 */
public class PrincipalController implements ActionListener {

    frmPrincipal VistaPrincipal;

    /* frmEdificios VistaEdificios;
   EdificiosModel ModeloEdificios;*/

    public PrincipalController(frmPrincipal VistaPrincipal) {
        this.VistaPrincipal = VistaPrincipal;
        //this.VistaEdificios = VistaEdi;
        this.VistaPrincipal.btnClientes.addActionListener(this);
        this.VistaPrincipal.btnConsultas.addActionListener(this);
        this.VistaPrincipal.btnPagos.addActionListener(this);
        //this.VistaPrincipal.setExtendedState(frmPrincipal.MAXIMIZED_BOTH);
        this.VistaPrincipal.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.VistaPrincipal.btnClientes) {
            frmClientes VistaEdificios = new frmClientes(VistaPrincipal, true);
            ClientesModel ModeloEdificios = new ClientesModel();
            ClientesController ControladorEdicios = new ClientesController(VistaEdificios, ModeloEdificios);
        }
        if (e.getSource() == this.VistaPrincipal.btnPagos) {
            frmPagos VistaOficinas = new frmPagos(VistaPrincipal, true);
            PagosModel ModeloOficinas = new PagosModel();
            PagosController ControladorOficinas = new PagosController(VistaOficinas, ModeloOficinas);
        }
        if (e.getSource() == this.VistaPrincipal.btnConsultas) {
            frmConsultas VistaConsultas = new frmConsultas(VistaPrincipal, true);
            ConsultasModel ModeloConsultas = new ConsultasModel();
            ConsultasController ControladorConsultas = new ConsultasController(VistaConsultas, ModeloConsultas);
        }
    }
}
