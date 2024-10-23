package com.benja.conexion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;


/**
 * Clase para manejar la conexión a la base de datos utilizando una pool de conexiones.
 * Utilizamos la biblioteca de Apache Commons DBCP para poder gestionar el acceso a la base de datos.
 */
public class Conexion {
	/**
     * Instancia de BasicDataSource que representa la pool de conexiones.
     */
	private static BasicDataSource dataSource = null;

	/**
     * Obtenemos la instancia de DataSource. Si este aún no ha sido creado, 
     * inicializamos la pool de conexiones con las configuraciones necesarias.
     * 
     * @return DataSource configurado con las propiedades de conexión.
     */
	private static DataSource getDataSource() {
		if (dataSource == null) {
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
			dataSource.setUsername("root");
			dataSource.setPassword("123456");
			dataSource.setUrl("jdbc:mariadb://localhost:3306/nomina?useTimezone=true&serverTimezone=UTC");
			dataSource.setInitialSize(20);
			dataSource.setMaxIdle(15);
			dataSource.setMaxTotal(20);
			
		}
		return dataSource;
	}

	
	/**
     * Obtenemos la conexión de la base de datos a través de la pool de conexiones.
     * 
     * @return Connection objeto que representa la conexión a la base de datos.
     * @throws SQLException si hay un error al obtener la conexión.
     */
	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}
}
