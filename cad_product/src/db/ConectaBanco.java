package db;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConectaBanco {
	private String usuario = "root";
    private String senha = "root";
    private String host = "localhost";
    private String porta = "3306";
    private String bd = "padaria_do_pandoca";
    
    public Connection obtemConexao (){
        try{
            Connection c = DriverManager.getConnection(
                "jdbc:mysql://" + host + ":" + porta + "/" + bd,
                usuario,
                senha
            );
            return c;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
