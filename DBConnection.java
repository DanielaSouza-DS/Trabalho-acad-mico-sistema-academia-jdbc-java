package dados;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:h2:mem:test"; // ou "jdbc:h2:mem:testdb"
    private static final String USER = "sa";
    private static final String PASS = "";
    private static Connection con = null;

    public static Connection getConnection() throws SQLException {
        if (con == null) {
            con = DriverManager.getConnection(URL, USER, PASS);
            JOptionPane.showMessageDialog(null, "Conexão Estabelecida com H2!");
        }
        return con;
    }
}
