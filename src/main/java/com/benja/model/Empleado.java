package com.benja.model;


/**
 * Clase que representa un empleado en el sistema.
 * Contiene atributos que describen las propiedades de un empleado
 * y métodos para acceder y modificar esos atributos.
 */
public class Empleado {
	 private int id;
	 private String nombre;
	 private String dni;
	 private String sexo;
	 private int categoria;
	 private int anyos;
	 private int sueldo;
	 
	 /**
	     * Tabla de sueldos base según la categoría del empleado.
	     * 
	     * El sueldo base para la categoría 1 es 50,000, y se incrementa en 20,000 unidades por cada categoría adicional.
	     */
		private static final int SUELDO_BASE[] =
			{50000, 70000, 90000, 110000, 130000,
			150000, 170000, 190000, 210000, 230000};
	 
	 /**
	     * Constructor que inicializa un nuevo objeto Empleado con los valores dados.
	     * 
	     * @param id            El identificador del empleado.
	     * @param nombre        El nombre del empleado.
	     * @param dni           El dni del empleado.
	     * @param sexo          El sexo del empleado.
	     * @param categoria     La categoria del empleado.
	     * @param anyos         Los años trabjados del empleado.
	     */	 
	 public Empleado(int id, String nombre, String dni, String sexo, int categoria, int anyos) {
	  super();
	  this.id = id;
	  this.nombre = nombre;
	  this.dni = dni;
	  this.sexo = sexo;
	  this.categoria = categoria;
	  this.anyos = anyos;
	 }
	 
	 public Empleado() {
	  // TODO Auto-generated constructor stub
	 }
	 
	 public int getId() {
	  return id;
	 }
	 
	 public void setId(int id) {
	  this.id = id;
	 }
	 
	 public String getNombre() {
	  return nombre;
	 }
	 
	 public void setNombre(String nombre) {
	  this.nombre = nombre;
	 }
	 
	 public String getDni() {
	  return dni;
	 }
	 
	 public void setDni(String dni) {
	  this.dni = dni;
	 }
	 
	 public String getSexo() {
	  return sexo;
	 }
	 
	 public void setSexo(String sexo) {
	  this.sexo = sexo;
	 }
	 
	 public int getCategoria() {
		 return categoria;
	 }
	 
	 public void setCategoria(int categoria) {
	  this.categoria = categoria;
	 }
	 
	 public int getAnyos() {
	  return anyos;
	 }
	 
	 public void setAnyos(int anyos) {
	  this.anyos = anyos;
	 }
	 
	 public int getSueldo() {
		 return sueldo;
	 }
	 
	 @Override
	 public String toString() {
	  return "Empleado [id=" + id + ", nombre=" + nombre + ", dni=" + dni + ", sexo=" + sexo
	    + ", categoria=" + categoria + ", anyos=" + anyos + "]";
	 }
	 
	 /**
	     * Calcula el sueldo de un empleado a partir de su categoría y años trabajados.
	     * 
	     * El sueldo se calcula sumando al sueldo base (dependiendo de la categoría) y un extra de 5000 unidades por cada
	     * año trabajado por el empleado.
	     * 
	     * @param empleado el empleado del cual se desea calcular el sueldo.
	     * @return el sueldo calculado para el empleado.
	     */
		public static int calcularSueldo(Empleado empleado) {
			int sueldo;
			int sueldoBase = SUELDO_BASE[empleado.getCategoria()-1];
			return sueldo = sueldoBase + 5000*empleado.anyos;
		}
	 
	}