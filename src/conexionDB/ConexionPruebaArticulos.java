package conexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConexionPruebaArticulos {
	public static void main(String[] args) {
	// TODO Auto-generated method stub
		try {
			//1. Crear la Conexion
			//show VARIABLES WHERE VARIABLE_NAME IN('hostname', 'port') 
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "root", "");
			//2. Crear objeto Statement
			Statement st = miConexion.createStatement();
			//3. Ejecutar la instruccion sql
			ResultSet result = st.executeQuery("SELECT * FROM articulos");
			//4. Recorrer el ResultSet
			while(result.next()) {
				System.out.println(result.getString("seccion")+" "+result.getString("nombre_articulo")+result.getString("fecha")+" "+result.getString("pais_origen"));
			}//
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al conectar: "+e.getMessage());
		}
	}
}
