package com.benja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.benja.conexion.Conexion;

/**
 * Clase DAO para gestionar las operaciones de acceso a datos relacionadas con
 * la entidad Empleado. Proporciona métodos para crear, editar, ver la
 * informacin y mostrar el salario de un empleado de la base de datos.
 */
public class NominaDAO {
	private Connection connection;
	private PreparedStatement statement;

	/**
	 * Crea una nomina con el salario de un empleado
	 * 
	 * @param dni     El DNI del empleado a crear la nomina.
	 * @param salario El salario del empleado.
	 * @throws SQLException Si ocurre un error en la base de datos
	 */
	public void crearNomina(String dni, int salario) throws SQLException {

		String sql = "INSERT INTO nominas (dni, sueldo) VALUES (?, ?)";

		connection = obtenerConexion();

		try {

			statement = connection.prepareStatement(sql);

			statement.setString(1, dni);

			statement.setInt(2, salario);

			statement.executeUpdate();

			statement.close();

			connection.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	/**
	 * Actualiza el sueldo del empleado para la nomina
	 * 
	 * @param dni    El DNI del empleado a actualizar el sueldo.
	 * @param sueldo El sueldo a actualizar
	 * @return actualizado Devuelve true si se ha actualizado correctamente, o false
	 *         si no se actualizo correctamente
	 * @throws SQLException Si ocurre un error en la base de datos.
	 **/
	public boolean actualizarSueldo(String dni, int nuevoSueldo) throws SQLException {

		String sql = "UPDATE nominas SET sueldo = ? WHERE dni = ?";

		boolean actualizado = false;

		connection = obtenerConexion();

		try {

			statement = connection.prepareStatement(sql);

			statement.setInt(1, nuevoSueldo);

			statement.setString(2, dni);

			int filasActualizadas = statement.executeUpdate();

			// Verifica si se actualizó alguna fila

			actualizado = filasActualizadas > 0;

			statement.close();

			connection.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return actualizado;

	}

	/**
	 * Obtiene una conexión a la base de datos desde el pool de conexiones.
	 * 
	 * @return Una conexión a la base de datos.
	 * @throws SQLException Si ocurre un error al obtener la conexión.
	 */
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}
}
