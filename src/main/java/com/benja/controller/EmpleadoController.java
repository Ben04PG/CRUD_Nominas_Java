package com.benja.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benja.dao.EmpleadoDAO;
import com.benja.dao.NominaDAO;
import com.benja.model.Empleado;

/**
 * Servlet implementation class EmpleadoController Servlet que maneja las
 * operaciones CRUD para los empleados. Se encarga de procesar las solicitudes
 * del cliente y delegar las acciones a la clase EmpleadoDAO para interactuar
 * con la base de datos.
 */
@WebServlet(description = "Administra peticiones para la tabla productos", urlPatterns = { "/empleados" })
public class EmpleadoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet() Constructor por defecto de EmpleadoController.
	 *      Llama al constructor de la superclase HttpServlet.
	 */
	public EmpleadoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *
	 *      Maneja las solicitudes GET del cliente. Dependiendo del parámetro
	 *      "opcion", se redirige la solicitud a la vista que corresponde o realiza
	 *      las operaciones del listado o edición de empleados.
	 * 
	 * @param request  La solicitud HTTP recibida.
	 * @param response La respuesta HTTP que se enviará al cliente.
	 * @throws ServletException Si ocurre un error en la ejecución del servlet.
	 * @throws IOException      Si ocurre un error de entrada/salida.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String opcion = request.getParameter("opcion");

		if (opcion.equals("crear")) {
			System.out.println("Usted a presionado la opcion crear");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/crear.jsp");
			requestDispatcher.forward(request, response);
		} else if (opcion.equals("info")) {
			System.out.println("Usted a presionado la opcion de informacion");
			EmpleadoDAO empleadoDAO = new EmpleadoDAO();
			List<Empleado> lista = new ArrayList<>();
			try {
				lista = empleadoDAO.obtenerEmpleados();
				for (Empleado empleado : lista) {
					System.out.println(empleado);
				}

				request.setAttribute("lista", lista);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/info.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (opcion.equals("salarios")) {
			String dni = request.getParameter("dni");
			EmpleadoDAO empleadoDAO = new EmpleadoDAO();
			if (dni == null || dni.trim().isEmpty()) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/salarios.jsp");
				requestDispatcher.forward(request, response);
				return;
			}
			
			try {
				Empleado empleado = empleadoDAO.obtenerEmpleado(dni);
				if (empleado == null) {
					request.setAttribute("mensajeError", "Ha ocurrido un error al obtener el salario");
					request.setAttribute("salario", null);
				} else {
					int salario = empleado.calcularSueldo(empleado);
					request.setAttribute("empleado", empleado);
					request.setAttribute("salario", salario);
				}
			} catch (SQLException e) {
				request.setAttribute("mensajeError", "Ha ocurrido un error al conectar con la base de datos");
			}
		}
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *
     * Maneja las solicitudes POST del cliente.
     * Procesa la creación o edición de un producto, utilizando los datos enviados en la solicitud.
     * 
     * @param request  La solicitud HTTP recibida.
     * @param response La respuesta HTTP que se enviará al cliente.
     * @throws ServletException Si ocurre un error en la ejecución del servlet.
     * @throws IOException      Si ocurre un error de entrada/salida.
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcion = request.getParameter("opcion");

		if (opcion.equals("guardar")) {
			EmpleadoDAO empleadoDAO = new EmpleadoDAO();
			NominaDAO nominaDAO = new NominaDAO();
			Empleado empleado = new Empleado();
			
			empleado.setNombre(request.getParameter("nombre"));
			empleado.setDni(request.getParameter("dni"));
			empleado.setSexo(request.getParameter("sexo"));
			empleado.setCategoria(Integer.parseInt(request.getParameter("categoria")));
			empleado.setAnyos(Integer.parseInt(request.getParameter("anyos")));
			try {
		        if (empleadoDAO.guardar(empleado)) {
		        	// Calcula el sueldo del empleado
		        	int sueldo = empleado.calcularSueldo(empleado);
		        	// Crear la nomina para el empleado
		        	nominaDAO.crearNomina(empleado.getDni(), sueldo);
		            request.setAttribute("mensajeExito", "El empleado ha sido guardado satisfactoriamente.");
		        } else {
		            request.setAttribute("mensajeError", "Ha ocurrido un error al guardar el empleado");
		        }
		        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/crear.jsp");
		        requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.getSession().setAttribute("mensajeError", "Error en la base de datos: " + e.getMessage());;
	            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/crear.jsp");
	            requestDispatcher.forward(request, response);
			}
		}
		

		// doGet(request, response);
	}


}
