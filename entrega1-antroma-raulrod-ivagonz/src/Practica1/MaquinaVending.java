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
	 * Mete tantos productos como se le mande en la linea que se le indica.
	 * 
	 * @param producto
	 *            Producto que se desea introducir.
	 *			  No debe ser nulo.
	 * @param linea
	 *            Identificador de la linea en la que vamos a introducir el producto.La
	 *            primera línea tiene como identificador el número 0. Debe ser
	 *            mayor o igual a 0 y menor que el tamaño de la máquina.
	 * @param cantidad
	 *            Cantidad de productos que vamos a introducir.
	 *			  Debe ser positiva.
	 * @throws IllegalArgumentException
	 *             Si se incumplen las condiciones impuestas a los parámetros.
	 */
	public double meterProducto(Producto producto, int linea, int cantidad) {
		if (producto.equals("")){ 
			throw new IllegalArgumentException("No se ha introducido un producto.");
		}
		if (!lineaCorrecta(linea)){ 
			throw new IllegalArgumentException("La linea introducida es incorrecta.");
		}
		if (!cantidadCorrecta(cantidad)){ 
			throw new IllegalArgumentException("La cantidad introducida es erronea.");
		}
		
			getProductos()[linea] = producto;
			getStock()[linea] = cantidad;
	}
	
	/**
	 * Repone el stock de la linea con la cantidad que se le indique.
	 * 
	 * @param linea
	 *            Identificador de la linea en la que vamos a reponer productos. La
	 *            primera línea tiene como identificador el número 0. Debe ser
	 *            mayor o igual a 0 y menor que el tamaño de la máquina.
	 *			  
	 * @param cantidad
	 *            Cantidad de productos que vamos a introducir.
	 *			  Debe ser positiva.
	 * @throws IllegalArgumentException
	 *             Si se incumplen las condiciones impuestas a los parámetros.
	 */
	public double reponerLinea(int linea, int cantidad) {
		if (!lineaCorrecta(linea)) {
			throw new IllegalArgumentException("La linea introducida es incorrecta.");
		}
		if (!cantidadCorrecta(cantidad)){ 
			throw new IllegalArgumentException("La cantidad introducida es erronea.");
		}
		
		cantidadProducto(linea) += cantidad;			
	}
	
	/**
	 * Retira tantos productos como se le mande en la linea que se le indica.
	 * 
	 * @param linea
	 *            Identificador de la linea en la que vamos a retirar productos. La
	 *            primera línea tiene como identificador el número 0. Debe ser
	 *            mayor o igual a 0 y menor que el tamaño de la máquina.
	 * @param cantidad
	 *            Cantidad de producto que vamos a retirar.
	 *			  Debe ser positiva.
	 * @throws IllegalArgumentException
	 *             Si se incumplen las condiciones impuestas a los parámetros.
	 */
	public double sacarProducto(int linea, int cantidad) {
		if (!lineaCorrecta(linea)) {
			throw new IllegalArgumentException("La linea introducida es incorrecta.");
		}
		if (cantidad > cantidadProducto(linea)) {
			throw new IllegalArgumentException("La linea no dispone de tantos productos.");	
		}
		cantidadProducto(linea) -= cantidad;
	}
	
	/**
	 * Vacia la linea que se la indica.
	 * 
	 * @param linea
	 *            Identificador de la linea que vamos a vaciar. La
	 *            primera línea tiene como identificador el número 0. Debe ser
	 *            mayor o igual a 0 y menor que el tamaño de la máquina.
	 * @throws IllegalArgumentException
	 *             Si se incumplen las condiciones impuestas a los parámetros.
	 */
	public double vaciarLinea(int linea) {
		if (!lineaCorrecta(linea)){ 
			throw new IllegalArgumentException("La linea introducida es incorrecta.");
		}		
			getProductos()[linea] = null;
			getStock()[linea] = 0;			
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
	 *            Identificador de la línea consultada. La
	 *            primera línea tiene como identificador el número 0. Debe ser
	 *            mayor o igual a 0 y menor que el tamaño de la máquina.
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

	/**Comprueba si la linea pasada como argumento es o no correcta.
	* @param linea
	 *            Identificador de la línea consultada. La
	 *            primera línea tiene como identificador el número 0. Debe ser
	 *            mayor o igual a 0 y menor que el tamaño de la máquina.
	 * @return Si la linea es o no correcta.
	*
	*/
	public boolean lineaCorrecta(int linea) {
		boolean correcta = true;
		if (linea < 0 || linea > tamanioMaquina()) {
			correcta = false;
		}
		return correcta;
	}
	
	/**Comprueba si el saldo de la tarjeta es suficiente para costear el precio.
	* @param tarjeta
	 *            Tarjeta con la que se pagará el Producto.
	 * @param precio
	 *            precio del Producto a pagar.
	 * @return Si el saldo es o no suficiente.
	*
	*/
	public boolean saldoSuficiente(TarjetaMonedero tarjeta, double precio) {
		boolean haySaldo = true;
		if (tarjeta.getSaldoActual() < precio) {
			haySaldo = false;
		}
		return haySaldo;
	}

	/**Comprueba si hay producto seleccionado.
	* @param linea
	 *            Identificador de la línea consultada. La
	 *            primera línea tiene como identificador el número 0. Debe ser
	 *            mayor o igual a 0 y menor que el tamaño de la máquina.
	 * @return Si hay o no Producto.
	*
	*/
	public boolean hayProducto(int linea) {
		boolean noVacio = true;
		if (getStock()[linea] == 0) {
			noVacio = false;
		}
		return noVacio;
	}
	/**Comprueba si la cantidad pasada como argumento está bien.
	* @param cantidad
	 *            Cantidad de producto que vamos a retirar.
	 *			  Debe ser positiva.
	 * @return Si la cantidad es o no correcta.
	*
	*/
	public boolean cantidadCorrecta(int cantidad){
		boolean correcta = true;
		if (cantidad <= 0) {
			correcta = false;
		}
		return correcta;
	}
	
	/**Obtiene los productos de la máquina.
	*	@return productos que contiene la máquina.
	*/
	public Producto[] getProductos() {
		return productos;
	}
	/**Obtiene todo el stock de la máquina.
	*	@return stock de productos que contiene la máquina.
	*/
	public int[] getStock() {
		return stock;
	}

}
