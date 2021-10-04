package conexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.Statement;

public class ModificaDB {
	public static void main(String[] args) {
	// TODO Auto-generated method stub
		try {
			//1. Crear la Conexion
			//show VARIABLES WHERE VARIABLE_NAME IN('hostname', 'port') 
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "root", "");
			//2. Crear objeto Statement
			Statement st = miConexion.createStatement();
			//
			String instruccionSql = "UPDATE articulos SET nombre_articulo = 'DuraMax' WHERE codigo_articulo = 31";
			//Ejecutar la instruccion...
			st.executeUpdate(instruccionSql);
			System.out.println("Datos modificados correctamente...");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al insertar: "+e.getMessage());
		}
	}
}
