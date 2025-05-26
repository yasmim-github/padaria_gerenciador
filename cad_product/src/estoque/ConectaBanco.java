package estoque;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaBanco {
    private final String URL = "jdbc:mysql://localhost:3306/padaria_do_pandoca";
    private final String USER = "root";
    private final String PASSWORD = "root";

    public Connection obtemConexao() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

