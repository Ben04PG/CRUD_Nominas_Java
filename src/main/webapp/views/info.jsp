<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Empleados</title>
<link rel="stylesheet" type="text/css" href="./styles/style.css">
</head>
<body>
	<h1>Listar Empleados</h1>
	<table border="1">
		<tr>
			<td>Id</td>
			<td>Nombre</td>
			<td>DNI</td>
			<td>Sexo</td>
			<td>Categoria</td>
			<td>Años</td>
		</tr>
		<c:forEach var="empleado" items="${lista}">
			<tr>
				<td><c:out value="${ empleado.id}"></c:out></td>
				<td><c:out value="${ empleado.nombre}"></c:out></td>
				<td><c:out value="${ empleado.dni}"></c:out></td>
				<td><c:out value="${ empleado.sexo}"></c:out></td>
				<td><c:out value="${ empleado.categoria}"></c:out></td>
				<td><c:out value="${ empleado.anyos}"></c:out></td>
			</tr>
		</c:forEach>
	</table>
	
	<table border="1">
		<tr>
			<td><a href="empleados?opcion=crear"> Alta Empleados</a></td>
		</tr>
		<tr>
			<td><a href="empleados?opcion=salarios"> Buscar Empleados Salario</a></td>
		</tr>
		<tr>
			<td><a href="empleados?opcion=filtrar"> Filtrar Empleados</a></td>
		</tr>
	</table>
</body>
</html>