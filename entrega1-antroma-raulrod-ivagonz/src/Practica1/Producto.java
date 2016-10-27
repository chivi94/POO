package Practica1;

/**
 * Implementación de un Producto. Se podrá consultar su información.
 * 
 * Primera parte de la práctica para la asignatura de Programación Orientada a
 * Objetos. Escuela de Ingeniería Informática, Universidad de Valladolid.
 * 
 * @author raulrod antroma ivagonz
 * 
 */

public class Producto {
	private double precio;
	private String nombre;
	private String upc;

	/**
	 * Inicialización con precio, nombre de producto e identificador.
	 * 
	 * @param precio
	 *            Valor del Producto. Debe ser mayor o igual que 0, con 2
	 *            decimales como máximo.
	 * @param nombre
	 *            Nombre del Producto.
	 * @param upc
	 *            Cadena que identifica el Producto. Deber ser correcto. Es
	 *            correcto si: Tiene 12 caracteres y el dígito número 12 es
	 *            correcto(está obtenido a partir del resto).
	 * @throws IllegalArgumentException
	 *             Si se incumplen las condiciones impuestas a los parámetros.
	 */
	public Producto(double precio, String nombre, String upc) {

		if (precio <= 0) {
			throw new IllegalArgumentException("Precio menor o igual que cero.");
		}
		if (!comprobarDecimalesPrecio(precio)) {
			throw new IllegalArgumentException("Precio con más de 2 decimales.");
		}

		if (nombre.equals("")) {
			throw new IllegalArgumentException("No se ha introducido el nombre.");
		}

		if (upc.equals("")) {
			throw new IllegalArgumentException("No se ha introducido el UPC.");
		}
		if (!comprobarDigitoControl(upc)) {
			throw new IllegalArgumentException("Dígito de control no válido.");
		}
		if (!comprobarUpc(upc)) {
			throw new IllegalArgumentException("Carácter incorrecto dentro del UPC.");
		}
		if (upc.length() != 12) {
			throw new IllegalArgumentException("Longitud de identificador distinta a 12");
		}
		this.precio = precio;
		this.nombre = nombre;
		this.upc = upc;

	}

	private boolean comprobarDecimalesPrecio(double precio) {
		boolean correcto = true;
		int precioEntero = (int) (precio * 100);
		if ((precio * 100) - precioEntero != 0) {
			correcto = false;
		}
		return correcto;
	}

	// Usado para comprobar si el dígito de control está mal.
	private boolean comprobarDigitoControl(String upc) {
		// Valor ascii para obtener la cifra decimal correcta
		int ascii = 48;
		// Digito de control del upc del producto
		int control = ((int) upc.charAt(upc.length() - 1)) - ascii;
		// Resultado de la suma de la obtención del upc
		int s = 0;
		// Variable usada para almacenar el dígito del upc
		int caracter = 0;
		// Variable usada para obtener el dígito de control
		int resto = 0;
		// Dígito de control obtenido al final de la ejecución de todo el código
		int d = 0;

		boolean correcto = false;
		for (int i = 0; i < upc.length() - 1; i++) {
			caracter = ((int) upc.charAt(i)) - ascii;
			if (i % 2 == 0) {
				s += caracter * 3;
			} else {
				s += caracter;
			}
		}
		resto = s % 10;
		d = 10 - resto;
		if (d == control) {
			correcto = true;
		}

		return correcto;
	}

	// Comprueba si el Upc tiene algún carácter que no sea un número
	private boolean comprobarUpc(String upc) {
		boolean correcto = true;
		int caracter = 0;
		int ascii = 48;
		int i = 0;
		while (correcto && i < upc.length()) {
			caracter = ((int) upc.charAt(i)) - ascii;
			if (caracter < 0 || caracter > 9) {
				correcto = false;
			}
			i++;
		}
		return correcto;
	}
	
	/**
	 * @return precio asociado al Producto.
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Permite modificar el precio del Producto.
	 * 
	 * @param precio
	 *            Nuevo valor del Producto. Debe ser mayor de 0 y tener 2
	 *            decimales.
	 */
	public void setPrecio(double precio) {
		if (precio <= 0) {
			throw new IllegalArgumentException("Precio menor o igual que cero.");
		}
		if (!comprobarDecimalesPrecio(precio)) {
			throw new IllegalArgumentException("Precio con más de 2 decimales.");
		}
		this.precio = precio;
	}
	
	/**
	 * @return nombre asociado al Producto.
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @return Upc asociado al Producto.
	 */
	public String getUpc() {
		return upc;
	}

}
