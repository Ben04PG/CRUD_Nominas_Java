<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mostrar Salarios</title>
<link rel="stylesheet" type="text/css" href="./styles/style.css">
</head>
<body>
	<h1>Mostrar Salario</h1>

	<form action="empleados" method="get">
		<input type="hidden" name="opcion" value="salarios">
		<table border=1>
			<tr>
				<td>DNI:</td>
				<td><input type="text" name="dni" size="50"></td>
			</tr>
		</table>
		<input type="submit" value="Buscar">
	</form>

	<h1>Info empleado</h1>
	<table>
		<thead>
			<tr>
				<th>Salario</th>
				<th>DNI</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><c:out value="${salario}"></c:out></td>
				<td><c:out value="${empleado.dni}"></c:out></td>
			</tr>
		</tbody>
	</table>

	<table border="1">
		<tr>
			<td><a href="empleados?opcion=crear"> Alta Empleados</a></td>
		</tr>
		<tr>
			<td><a href="empleados?opcion=info"> Listar Empleados</a></td>
		</tr>
		<tr>
			<td><a href="empleados?opcion=filtrar"> Filtrar Empleados</a></td>
		</tr>
	</table>
</body>
</html>