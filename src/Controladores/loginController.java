/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Conexion;
import Vistas.frmLogin;
import Vistas.frmPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author dmiranda
 */
public class loginController implements ActionListener{
 frmLogin VistaLogin;
 

    public loginController(frmLogin VistaLogin) {
        this.VistaLogin = VistaLogin;
        
        
        this.VistaLogin.setLocationRelativeTo(null);
        this.VistaLogin.btnLoginIngresar.addActionListener(this);
        this.VistaLogin.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource()==this.VistaLogin.btnLoginIngresar)
      {
        try
        {
        Conexion conect = new Conexion(this.VistaLogin.txtUser.getText(),this.VistaLogin.txtPass.getText());
        if(Conexion.conn.isValid(0))
        {
        this.VistaLogin.dispose();
        frmPrincipal VistaPrincipal = new frmPrincipal();
        /*frmEdificios VistaEdificios = new frmEdificios(VistaPrincipal,true);
        EdificiosModel ModeloEdificios = new EdificiosModel();*/
        PrincipalController nuevoControladorPrincipal =  new PrincipalController(VistaPrincipal); 
        }
        }
        catch(Exception ex)
        {
           
        }
      }
    }
 
 
}
