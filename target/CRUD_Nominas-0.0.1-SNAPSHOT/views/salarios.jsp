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
		<table border=1>
			<tr>
				<td>DNI:</td>
				<td><input type="text" name="dni" size="50"></td>
			</tr>
		</table>
		<input type="submit" value="Buscar">
	</form>
	
	<%Empleado empleado = (Empleado) request.getAttribute("empleado"); %>
	
	<h2>Info empleado</h2>
	<p>Salario</p><%=empleado.getSueldo() %>
	<p>DNI</p><%=empleado.getDni() %>
</body>
</html>