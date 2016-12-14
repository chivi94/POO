package Practica2;

import java.util.ArrayList;

/**
 * Implementacion de un Pack de Producto. Se podrá consultar su información.
 * 
 * Segunda parte de la práctica para la asignatura de Programación Orientada a
 * Objetos. Escuela de Ingeniería Informática, Universidad de Valladolid.
 * 
 * @author raulrod antroma ivagonz
 *
 */
public class Pack extends Vendible {

	private ArrayList<Producto> productos;
	private String identificador;
	private static final int CANTIDADMAXIMA = 10;

	/**
	 * Inicializacion de pack con nombre, identificador, precio y los productos
	 * que lo forman.
	 * 
	 * @see Vendible#Vendible(String nombre, String identificador, double
	 *      precio)
	 * @param productos
	 *            Array de objetos Producto.
	 * @assert.pre productos.length mayor o igual a 2
	 * @assert.pre !producto.equals(productos[i])
	 * @assert.pre productos.length menor o igual que 10
	 * @assert.pre identificador.length() igual a 8
	 */
	public Pack(String nombre, String identificador,
			Producto [] productos) {
		// TODO Auto-generated constructor stub
		super(nombre, identificador, 0.1);
		// assert (productos.size() >= 2);
		assert (!productosRepetidos(productos));
		assert (productos.length <= CANTIDADMAXIMA);
		assert (identificador.length() == 8);
		this.productos = volcar(productos);

	}

	/**
	 * Comprueba que no existan productos repetidos dentro del mismo pack
	 * 
	 * @param productos
	 *            Array para realizar la comprobacion.
	 * @return true si hay repetidos, false si no hay repetidos
	 */
	public static boolean productosRepetidos(Producto [] productos) {
		for (int i = 0; i < productos.length; i++) {
			for (int j = 0; j < productos.length; j++) {
				if (productos[i].equals(productos[j]) && i != j) {
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * Cuenta el numero de productos dentro de un pack
	 * 
	 * @return cantidad de productos dentro del pack
	 */
	public int cantidadProductos() {
		return productos.size();
	}

	/**
	 * Comprueba si un producto pertenece al pack
	 * 
	 * @param producto
	 *            que le pasamos para que compruebe
	 * @return true si el pack lo contiene, false en caso contrario
	 */
	public boolean containsProducto(Producto producto) {
		for (int i = 0; i < productos.size(); i++) {
			if (producto.equals(productos.get(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo para eliminar un producto contenido en un pack
	 * 
	 * @param producto
	 *            que deseamos eliminar del pack
	 * @assert.pre la cantidad del pack es mayor que 2
	 * @assert.pre el producto a quitar esta dentro del pack
	 */
	public void quitarProducto(Producto producto) {
		assert (cantidadProductos() > 2);
		assert (containsProducto(producto));
		for (int i = 0; i < productos.size(); i++) {
			if (producto.equals(productos.get(i))) {
				productos.remove(i);
			}
		}
	}

	/**
	 * Metodo para agregar un prodructo a un pack
	 * 
	 * @param producto
	 *            que deseamos agregar al pack
	 * @assert.pre la cantidad contenida en el pack es menor que el maximo
	 * @assert.pre el producto no esta contenido en el pack
	 */
	public void agregarProducto(Producto producto) {
		assert (cantidadProductos() < CANTIDADMAXIMA);
		assert (!containsProducto(producto));
		int i = 0;
		boolean encontrado = false;

		while (!encontrado && i < productos.size()) {
			if (productos.get(i) == null) {
				productos.add(producto);
				encontrado = true;
			}
			i++;
		}

	}
	
	private ArrayList<Producto> volcar (Producto [] productos){
		ArrayList<Producto> a = new ArrayList<Producto>();
		for (int i = 0; i < productos.length; i++) {
			a.add(productos[i]);
		}
		return a;
	}

	/**
	 * Calcula y retorna el precio de un pack.
	 * 
	 * @return suma de los precios del pack * 0.8
	 */
	@Override
	public double getPrecio() {
		// TODO Auto-generated method stub
		double suma = 0;
		for (int i = 0; i < productos.size(); i++) {
			suma += productos.get(i).getPrecio();
		}
		return 0.8 * suma;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public String getIdentificador() {
		return identificador;
	}

	/**
	 * Crea un string a partir de los nombres de los productos contenidos en el
	 * pack
	 * 
	 * @return string con el nombre de los productos contenidos en el pack
	 */
	@Override
	public String toString() {
		String componentes = "";
		for (int i = 0; i < productos.size(); i++) {
			componentes += productos.get(i).getNombre() + "\n";
		}
		return componentes;
	}

}
