package conexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.Statement;

public class BorraDB {
	public static void main(String[] args) {
	// TODO Auto-generated method stub
		try {
			//1. Crear la Conexion
			//show VARIABLES WHERE VARIABLE_NAME IN('hostname', 'port') 
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "root", "");
			//2. Crear objeto Statement
			Statement st = miConexion.createStatement();
			//
			String instruccionSql = "DELETE FROM articulos WHERE codigo_articulo = 32";
			//Ejecutar la instruccion...
			st.executeUpdate(instruccionSql);
			System.out.println("Datos eliminados correctamente...");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al eliminar: "+e.getMessage());
		}
	}
}
