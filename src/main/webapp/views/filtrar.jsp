<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Filtrar Empleados</title>
<link rel="stylesheet" type="text/css" href="./styles/style.css">
</head>
<body>
	<h1>Filtrar Empleados</h1>
	<form action="empleados" method="get">
		<input type="hidden" name="opcion" value="filtrar"> <label
			for="criterio">Buscar por:</label> <select name="criterio" required>
			<option value="nombre">Nombre</option>
			<option value="dni">DNI</option>
			<option value="sexo">Sexo</option>
			<option value="categoria">Categoría</option>
			<option value="anyos">Años Trabajados</option>
		</select> <input type="text" name="valor" placeholder="Valor de búsqueda"
			required>
		<button type="submit">Filtrar</button>
	</form>

	<h1>Resultados</h1>
	<c:choose>
		<c:when test="${not empty lista}">
			<table border="1">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nombre</th>
						<th>DNI</th>
						<th>Sexo</th>
						<th>Categoría</th>
						<th>Años Trabajados</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="empleado" items="${lista}">
						<tr>
							<td><c:out value="${empleado.id}"></c:out></td>
							<td><c:out value="${empleado.nombre}"></c:out></td>
							<td><c:out value="${empleado.dni}"></c:out></td>
							<td><c:out value="${empleado.sexo}"></c:out></td>
							<td><c:out value="${empleado.categoria}"></c:out></td>
							<td><c:out value="${empleado.anyos}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
		<div class="alerta-error"><p>No se encontraron empleados con los criterios especificados.</p></div>
		</c:otherwise>
	</c:choose>

	<table border="1">
		<tr>
			<td><a href="empleados?opcion=crear"> Alta Empleados</a></td>
		</tr>
		<tr>
			<td><a href="empleados?opcion=info"> Listar Empleados</a></td>
		</tr>
		<tr>
			<td><a href="empleados?opcion=salarios"> Buscar Empleados Salario</a></td>
		</tr>
	</table>
</body>
</html>