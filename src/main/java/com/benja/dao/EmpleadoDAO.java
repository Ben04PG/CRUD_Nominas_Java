package com.benja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.benja.conexion.Conexion;
import com.benja.model.Empleado;

/**
 * Clase DAO para gestionar las operaciones de acceso a datos relacionadas con
 * la entidad Empleado. Proporciona métodos para crear, editar, ver la
 * informacin y mostrar el salario de un empleado de la base de datos.
 */
public class EmpleadoDAO {
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;

	/**
	 * Guarda un nuevo empleado en la base de datos. Primero verifica si el empleado
	 * ya existe. Si no existe, lo inserta.
	 * 
	 * @param empleado El empleado a guardar.
	 * @return true si el empleado se guardó correctamente, false si el empleado ya
	 *         existe.
	 * @throws SQLException Si ocurre un error en la base de datos.
	 */
	public boolean guardar(Empleado empleado) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			connection.setAutoCommit(false);

			// Se verifica si ya existe el producto
			sql = "SELECT COUNT(*) FROM empleados WHERE dni = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, empleado.getDni());
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			int count = resultSet.getInt(1);

			if (count > 0) {
				// El producto ya existe
				return false;
			}

			sql = "INSERT INTO empleados (id, nombre, dni, sexo, categoria, anyos) VALUES(?,?,?,?,?,?)";
			statement = connection.prepareStatement(sql);

			statement.setString(1, null);
			statement.setString(2, empleado.getNombre());
			statement.setString(3, empleado.getDni());
			statement.setString(4, empleado.getSexo());
			statement.setInt(5, empleado.getCategoria());
			statement.setInt(6, empleado.getAnyos());

			estadoOperacion = statement.executeUpdate() > 0;

			connection.commit();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

		return estadoOperacion;
	}

	/**
	 * Edita un empleado existente en la base de datos.
	 * 
	 * @param empleado El empleado con los nuevos valores.
	 * @return true si el empleado se editó correctamente, false en caso contrario.
	 * @throws SQLException Si ocurre un error en la base de datos.
	 */
	public boolean editar(Empleado empleado) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();
		try {
			connection.setAutoCommit(false);
			sql = "UPDATE empleados SET nombre=?, dni=?, sexo=?, categoria=?, anyos=? WHERE id=?";
			statement = connection.prepareStatement(sql);

			statement.setString(2, empleado.getNombre());
			statement.setString(3, empleado.getDni());
			statement.setString(4, empleado.getSexo());
			statement.setInt(5, empleado.getCategoria());
			statement.setInt(6, empleado.getAnyos());

			estadoOperacion = statement.executeUpdate() > 0;
			connection.commit();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

		return estadoOperacion;
	}

	/**
	 * Obtiene una lista de todos los productos de la base de datos.
	 * 
	 * @return Una lista de objetos Producto.
	 * @throws SQLException Si ocurre un error en la base de datos.
	 */
	public List<Empleado> obtenerEmpleados() throws SQLException {
		ResultSet resultSet = null;
		List<Empleado> listaEmpleados = new ArrayList<>();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM empleados";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Empleado e = new Empleado();
				e.setId(resultSet.getInt(1));
				e.setNombre(resultSet.getString(2));
				e.setDni(resultSet.getString(3));
				e.setSexo(resultSet.getString(4));
				e.setCategoria(resultSet.getInt(5));
				e.setAnyos(resultSet.getInt(6));
				listaEmpleados.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaEmpleados;
	}

	/**
	 * Obtiene un Empleado específico de la base de datos según su ID.
	 * 
	 * @param dni El dni del empleado a recuperar.
	 * @return Un Empleado.
	 * @throws SQLException Si ocurre un error en la base de datos.
	 */
	public Empleado obtenerEmpleado(String dni) throws SQLException {
		ResultSet resultSet = null;
		Empleado emp = new Empleado();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM empleados WHERE dni =?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, dni);

			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				emp.setId(resultSet.getInt(1));
				emp.setNombre(resultSet.getString(2));
				emp.setDni(resultSet.getString(3));
				emp.setSexo(resultSet.getString(4));
				emp.setCategoria(resultSet.getInt(5));
				emp.setAnyos(resultSet.getInt(6));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return emp;
	}

	public List<Empleado> filtrarEmpleados(String criterio, String valor) throws SQLException {
		List<Empleado> listaEmpleados = new ArrayList<>();
		ResultSet resultSet = null;

		String sql = "SELECT * FROM empleados WHERE " + criterio + " LIKE ?";
		connection = obtenerConexion();

		try {
			System.out.println("SQL ejecutada: " + sql);
			statement = connection.prepareStatement(sql);
			statement.setString(1, "%" + valor + "%");
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Empleado e = new Empleado();
				e.setId(resultSet.getInt("id"));
				e.setNombre(resultSet.getString("nombre"));
				e.setDni(resultSet.getString("dni"));
				e.setSexo(resultSet.getString("sexo"));
				e.setCategoria(resultSet.getInt("categoria"));
				e.setAnyos(resultSet.getInt("anyos"));
				listaEmpleados.add(e);
			}
			System.out.println("Cantidad de empleados filtrados: " + listaEmpleados.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}

		return listaEmpleados;
	}

	public Empleado obtenerEmpleadoPorId(int id) throws SQLException {
		Empleado empleado = null;
		ResultSet resultSet = null;

		String sql = "SELECT * FROM empleados WHERE id = ?";
		connection = obtenerConexion();

		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				empleado = new Empleado();
				empleado.setId(resultSet.getInt(1));
				empleado.setNombre(resultSet.getString(2));
				empleado.setDni(resultSet.getString(3));
				empleado.setSexo(resultSet.getString(4));
				empleado.setCategoria(resultSet.getInt(5));
				empleado.setAnyos(resultSet.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}

		return empleado;
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
