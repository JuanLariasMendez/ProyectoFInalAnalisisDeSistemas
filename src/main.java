import Controladores.loginController;
import Vistas.frmLogin;
import java.sql.Connection;
/**
 *
 * @author Samayoa
 */
public class main {
public static Connection conexion;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        frmLogin VistaLogin = new frmLogin(null,true);
        loginController nuevoControladorLogin = new loginController(VistaLogin);
    }

}
