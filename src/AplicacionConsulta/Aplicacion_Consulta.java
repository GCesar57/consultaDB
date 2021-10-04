package AplicacionConsulta;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Aplicacion_Consulta {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame mimarco=new Marco_Aplicacion();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mimarco.setVisible(true);
	}

}
class Marco_Aplicacion extends JFrame{
	private Connection conexion;
	private JComboBox secciones;
	private JComboBox paises;
	private JTextArea resultado;
	private PreparedStatement enviaConsultaSeccion;
	private PreparedStatement enviaConsultaPais;
	private PreparedStatement enviaConsultaTodo; //para evaluar las dos posivilidades.
	private final String consultaSeccion = "SELECT nombre_articulo, seccion, precio, pais_origen FROM articulos WHERE seccion=?";
	private final String consultaPais = "SELECT nombre_articulo, seccion, precio, pais_origen FROM articulos WHERE pais_origen=?";
	private final String consultaTodos = "SELECT nombre_articulo, seccion, precio, pais_origen FROM articulos WHERE seccion=? AND pais_origen=?";
	//
	public Marco_Aplicacion(){
		setTitle ("Consulta BBDD");
		setBounds(500,300,400,400);
		setLayout(new BorderLayout());
		JPanel menus=new JPanel();
		menus.setLayout(new FlowLayout());
		secciones=new JComboBox();
		secciones.setEditable(false);
		secciones.addItem("Todos");
		paises=new JComboBox();
		paises.setEditable(false);
		paises.addItem("Todos");
		resultado= new JTextArea(4,50);
		resultado.setEditable(false);
		add(resultado);
		menus.add(secciones);
		menus.add(paises);	
		add(menus, BorderLayout.NORTH);
		add(resultado, BorderLayout.CENTER);
		JButton botonConsulta=new JButton("Consulta");
		//Poner el boton a la escucha...antes de agregarlo a la lamina...
		botonConsulta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ejecutaConsulta();
			}
		});
		add(botonConsulta, BorderLayout.SOUTH);
		
		//
		//Conexion con base de datos...
		try {
			//Crear la conexion
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "root", "");
			Statement sentencia = conexion.createStatement();
			String consulta = "SELECT DISTINCTROW seccion FROM articulos";
			ResultSet rs = sentencia.executeQuery(consulta);
			while(rs.next()) {
				//Agregar los elementos.
				secciones.addItem(rs.getString(1));
			}//
			rs.close();
			//----------Combo 2
			consulta = "SELECT DISTINCTROW pais_origen FROM articulos ORDER BY pais_origen ASC";
			rs = sentencia.executeQuery(consulta);
			while(rs.next()) {
				//Agregar los elementos.
				paises.addItem(rs.getString(1));
			}//
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al conectar: "+e.getMessage());
		}//Catch
	}
	//
	//Metodo del boton
	private void ejecutaConsulta() {
		ResultSet rs = null;
		//almacenar lo que el usuario escogio del combo
		try {
			resultado.setText("");
			String seccion = (String) secciones.getSelectedItem();
			String pais = (String) paises.getSelectedItem();
			//Seleccion de los combobox.
			if(!seccion.equals("Todos") && pais.equals("Todos")) {
				enviaConsultaSeccion = conexion.prepareStatement(consultaSeccion);
				enviaConsultaSeccion.setString(1, seccion);
				rs = enviaConsultaSeccion.executeQuery();
			}else if(seccion.equals("Todos") && !pais.equals("Todos")) {
				enviaConsultaPais = conexion.prepareStatement(consultaPais);
				enviaConsultaPais.setString(1, pais);
				rs = enviaConsultaPais.executeQuery();
			}else if(!seccion.equals("Todos") && !pais.equals("Todos")) {
				enviaConsultaTodo = conexion.prepareStatement(consultaTodos);
				enviaConsultaTodo.setString(1, seccion);
				enviaConsultaTodo.setString(2, pais);
				rs = enviaConsultaTodo.executeQuery();
			}//
			while(rs.next()) {
				resultado.append(rs.getString(1));
				resultado.append(" -- ");
				resultado.append(rs.getString(2));
				resultado.append(" -- ");
				resultado.append(rs.getString(3));
				resultado.append(" -- ");
				resultado.append(rs.getString(4));
				resultado.append("\n");
				//nombre_articulo, seccion, precio, pais_origen 
			}//
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: "+e.getMessage());
		}
		//
	}//
}//




