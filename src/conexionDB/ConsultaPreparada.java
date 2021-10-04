package conexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaPreparada {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//Crear la conexion
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "root", "");
			//Preparar la consulta
			PreparedStatement miSentencia = conexion.prepareStatement("SELECT nombre_articulo, fecha, pais_origen FROM articulos WHERE pais_origen=?");
			//Establecer parametros de consulta.
			miSentencia.setString(1, "El Salvador");
			//Ejecutar y recorrer la consulta.
			ResultSet rs = miSentencia.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1)+" - "+rs.getDate(2)+" - "+rs.getString(3));
			}//
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al conectar: "+e.getMessage());
		}
	}

}
