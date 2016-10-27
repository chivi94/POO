package Practica1;

import org.junit.Test;

public class ProductoTest {
	// COMPROBACIONES PARA EL CONSTRUCTOR
	// Clases válidas
	@Test
	public void precioMayorCeroUpcCorrecto() {
		@SuppressWarnings("unused")
		Producto producto = new Producto(0.01, "KitKat", "111111111117");
	}

	// Clases no válidas
	// Precio
	@Test(expected = IllegalArgumentException.class)
	public void precioNulo() {
		@SuppressWarnings("unused")
		Producto productoNulo = new Producto(0, "KitKat", "111111111117");
	}

	@Test(expected = IllegalArgumentException.class)
	public void precioNegativo() {
		@SuppressWarnings("unused")
		Producto productoNegativo = new Producto(-0.01, "KitKat", "111111111117");
	}

	@Test(expected = IllegalArgumentException.class)
	public void precioMasDeDosDecimales() {
		@SuppressWarnings("unused")
		Producto productoDecimalesMal = new Producto(0.001, "KitKat", "111111111117");
	}

	// Nombre
	@Test(expected = IllegalArgumentException.class)
	public void nombreVacio() {
		@SuppressWarnings("unused")
		Producto productoNombreVacio = new Producto(0.01, "", "111111111117");
	}

	// Este caso está controlado por Java
	@Test(expected = NullPointerException.class)
	public void nombreNulo() {
		@SuppressWarnings("unused")
		Producto productoUpcIncorrecto = new Producto(0.01, null, "111111111117");
	}

	// Upc
	@Test(expected = IllegalArgumentException.class)
	public void upcDigitoControlIncorrecto() {
		@SuppressWarnings("unused")
		Producto productoUpcIncorrecto = new Producto(0.01, "KitKat", "111111111118");
	}

	@Test(expected = IllegalArgumentException.class)
	public void upcCaracterIncorrecto() {
		@SuppressWarnings("unused")
		Producto productoUpcIncorrecto = new Producto(0.01, "KitKat", "11111111a117");
	}

	@Test(expected = IllegalArgumentException.class)
	public void upcVacio() {
		@SuppressWarnings("unused")
		Producto productoUpcIncorrecto = new Producto(0.01, "KitKat", "");
	}

	// Este caso está controlado por Java
	@Test(expected = NullPointerException.class)
	public void upcNulo() {
		@SuppressWarnings("unused")
		Producto productoUpcIncorrecto = new Producto(0.01, "KitKat", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void upcTamanioMayorDoce() {
		@SuppressWarnings("unused")
		Producto productoUpcTamanioMayorDoce = new Producto(0.01, "KitKat", "1111111111117");
	}

	@Test(expected = IllegalArgumentException.class)
	public void upcTamanioMenorDoce() {
		@SuppressWarnings("unused")
		Producto productoUpcTamanioMenorDoce = new Producto(0.01, "KitKat", "11111111117");
	}

	// COMPROBACIONES PARA setPrecio()
	// Clases válidas
	@Test
	public void cambioPrecioCorrecto() {
		Producto producto = new Producto(0.01, "KitKat", "111111111117");
		producto.setPrecio(0.02);
	}

	// Clases no válidas
	@Test(expected = IllegalArgumentException.class)
	public void cambioPrecioNulo() {
		Producto productoNulo = new Producto(0.01, "KitKat", "111111111117");
		productoNulo.setPrecio(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void cambioPrecioNegativo() {
		Producto productoNegativo = new Producto(0.01, "KitKat", "111111111117");
		productoNegativo.setPrecio(-0.01);
	}

	@Test(expected = IllegalArgumentException.class)
	public void cambioPrecioMasDeDosDecimales() {
		Producto productoDecimalesMal = new Producto(0.01, "KitKat", "111111111117");
		productoDecimalesMal.setPrecio(0.001);

	}
}
