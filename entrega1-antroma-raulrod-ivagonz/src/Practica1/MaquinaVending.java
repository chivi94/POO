package Practica1;

import fabricante.externo.tarjetas.TarjetaMonedero;

/**
 * Implementación de una Maquina de Vending. Se podrá consultar su información.
 * 
 * Primera parte de la práctica para la asignatura de Programación Orientada a
 * Objetos. Escuela de Ingeniería Informática, Universidad de Valladolid.
 * 
 * @author raulrod antroma ivagonz
 *
 */
public class MaquinaVending {

	private Producto[] productos;
	private int[] stock;

	/**
	 * Inicializa una Maquina de Vending. Las líneas debe ser un número positivo
	 * 
	 * @param tamanio
	 *            Entero que indica la cantidad de líneas que tiene la máquina.
	 * @throws IllegalArgumentException
	 *             Si se incumplen las condiciones impuestas al argumento.
	 */
	public MaquinaVending(int lineas) {
		// TODO Auto-generated constructor stub
		if (lineas <= 0) {
			throw new IllegalArgumentException("Cantidad de líneas no válida. Debe ser mayor de 0.");
		}
		productos = new Producto[lineas];
		stock = new int[lineas];
	}

	/**
	 * Procesará el pago del producto elegido.
	 * 
	 * @param tarjeta
	 *            TarjetaMonedero para realizar el pago. No debe ser nula.
	 * @param credencialDescuento
	 *            Credencial para descontar el dinero del producto a la tarjeta.
	 *            Debe ser correcta.
	 * @param linea
	 *            Linea en la que se encuentra el producto seleccionado. La
	 *            primera línea tiene como identificador el número 0. Debe ser
	 *            mayor o igual a 0 y menor que el tamaño de la máquina.
	 * @throws IllegalArgumentException
	 *             Si se incumplen las condiciones impuestas a los parámetros.
	 */
	public void pagar(TarjetaMonedero tarjeta, String credencialDescuento, int linea) {
		if (credencialDescuento.equals("") || credencialDescuento.length() != 16) {
			throw new IllegalArgumentException("Credencial de la tarjeta errónea.");
		}
		if (!lineaCorrecta(linea)) {
			throw new IllegalArgumentException("Identificador de línea no válido.");
		}
		if (!hayProducto(linea)) {
			throw new IllegalArgumentException("No hay producto en la línea indicada.");
		}
		double precio = getProductos()[linea].getPrecio();
		if (saldoSuficiente(tarjeta, precio) && hayProducto(linea)) {
			tarjeta.descontarDelSaldo(credencialDescuento, precio);
			getStock()[linea]--;
		}
	}

	/**
	 * Dada una linea, informa del precio del Producto que contiene. Debe ser
	 * mayor o igual a 0 y menor que el tamaño de la máquina.
	 * 
	 * @param linea
	 *            Identificador de la línea seleccionada.
	 * @return El precio del Producto seleccionado.
	 * @throws IllegalArgumentException
	 *             Si se incumple la condición impuesta al parámetro.
	 */
	public double obtenerPrecio(int linea) {
		if (!hayProducto(linea)) {
			throw new IllegalArgumentException("No hay producto en esa línea.");
		}
		if (!lineaCorrecta(linea)) {
			throw new IllegalArgumentException("Identificador de línea incorrecto.");
		}
		return getProductos()[linea].getPrecio();
	}

	/**
	 * Obtiene el tamaño de la máquina.
	 * 
	 * @return Entero que indica la cantidad de Productos que puede contener la
	 *         máquina.
	 */
	public int tamanioMaquina() {
		return getProductos().length;
	}

	/**
	 * Dada una línea, obtiene el tipo de producto que tiene. Debe ser mayor o
	 * igual a 0 y menor que el tamaño de la máquina.
	 * 
	 * @param linea
	 *            Identificador de la línea consultada.
	 * @return Cadena que indica el Producto de la línea.
	 * @throws IllegalArgumentException
	 *             Si se incumple la condición impuesta al parámetro.
	 */
	public String productoEnLinea(int linea) {
		if (!lineaCorrecta(linea)) {
			throw new IllegalArgumentException("Línea introducida no válida");
		}
		if (!hayProducto(linea)) {
			throw new IllegalArgumentException("No hay producto en esa línea.");
		}
		return getProductos()[linea].getNombre();
	}

	/**
	 * Dada una línea, obtiene la cantidad de producto que tiene. Debe ser mayor
	 * o igual a 0 y menor que el tamaño de la máquina.
	 * 
	 * @param linea
	 *            Identificador de la línea consultada.
	 * @return Cantidad de Producto.
	 * @throws IllegalArgumentException
	 *             Si se incumple la condición impuesta al parámetro.
	 */
	public int cantidadProducto(int linea) {
		if (!lineaCorrecta(linea)) {
			throw new IllegalArgumentException("Línea introducida no válida");
		}
		if (!hayProducto(linea)) {
			throw new IllegalArgumentException("No hay producto en esa línea.");
		}
		return getStock()[linea];
	}

	// Comprueba si la línea pasada como parámetro es o no correcta.
	private boolean lineaCorrecta(int linea) {
		boolean correcta = true;
		if (linea < 0 || linea > tamanioMaquina()) {
			correcta = false;
		}
		return correcta;
	}

	// Comprueba si el saldo de la tarjeta es suficiente para costear el precio
	private boolean saldoSuficiente(TarjetaMonedero tarjeta, double precio) {
		boolean haySaldo = true;
		if (tarjeta.getSaldoActual() < precio) {
			haySaldo = false;
		}
		return haySaldo;
	}

	// Comprueba si hay producto seleccionado
	private boolean hayProducto(int linea) {
		boolean vacio = false;
		if (getStock()[linea] == 0) {
			vacio = true;
		}
		return vacio;
	}

	public Producto[] getProductos() {
		return productos;
	}

	public int[] getStock() {
		return stock;
	}

}
