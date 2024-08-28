package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Código para establecer la conexión
                String url = "jdbc:sqlserver://DESKTOP-IS4E0E7;instanceName=SQLEXPRESS;databaseName=inventarioElectronico;encrypt=false;";
                String user = "enzo";
                String password = "1234";
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    
 
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Conexión cerrada exitosamente.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al cerrar la conexión.");
            }
        }
    }
}

