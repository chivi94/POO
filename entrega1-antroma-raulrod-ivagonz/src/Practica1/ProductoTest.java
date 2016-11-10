package Practica1;

import org.junit.Test;

public class ProductoTest {
	// COMPROBACIONES PARA EL CONSTRUCTOR
	// Clases válidas
	@Test
	public void testInicializarConPrecioNombreUpcCorrectos() {
		@SuppressWarnings("unused")
		Producto producto = new Producto(0.01, "KitKat", "111111111117");
	}

	// Clases no válidas
	// Precio
	@Test(expected = IllegalArgumentException.class)
	public void testInicializarConNombreUpcCorrectosPrecioCero() {
		@SuppressWarnings("unused")
		Producto productoNulo = new Producto(0, "KitKat", "111111111117");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInicializarConNombreUpcCorrectosPrecioNegativo() {
		@SuppressWarnings("unused")
		Producto productoNegativo = new Producto(-0.01, "KitKat", "111111111117");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInicializarConNombreUpcCorrectosPrecioMasDeDosDecimales() {
		@SuppressWarnings("unused")
		Producto productoDecimalesMal = new Producto(0.001, "KitKat", "111111111117");
	}

	// Nombre
	@Test(expected = IllegalArgumentException.class)
	public void testInicializarConPrecioUpcCorrectosNombreCadenaVacia() {
		@SuppressWarnings("unused")
		Producto productoNombreVacio = new Producto(0.01, "", "111111111117");
	}

	// Este caso está controlado por Java
	@Test(expected = NullPointerException.class)
	public void testInicializarConPrecioUpcCorrectosNombreNulo() {
		@SuppressWarnings("unused")
		Producto productoUpcIncorrecto = new Producto(0.01, null, "111111111117");
	}

	// Upc
	@Test(expected = IllegalArgumentException.class)
	public void testInicializarConPrecioNombreCorrectosUpcDigitoControlErroneo() {
		@SuppressWarnings("unused")
		Producto productoUpcIncorrecto = new Producto(0.01, "KitKat", "111111111118");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInicializarConPrecioNombreCorrectosUpcCaracterIncorrecto() {
		@SuppressWarnings("unused")
		Producto productoUpcIncorrecto = new Producto(0.01, "KitKat", "11111111a117");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInicializarConPrecioNombreCorrectosUpcCadenaVacia() {
		@SuppressWarnings("unused")
		Producto productoUpcIncorrecto = new Producto(0.01, "KitKat", "");
	}

	// Este caso está controlado por Java
	@Test(expected = NullPointerException.class)
	public void testInicializarConPrecioNombreCorrectosUpcNulo() {
		@SuppressWarnings("unused")
		Producto productoUpcIncorrecto = new Producto(0.01, "KitKat", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInicializarConPrecioNombreCorrectosUpcMasDeDoceDigitos() {
		@SuppressWarnings("unused")
		Producto productoUpcTamanioMayorDoce = new Producto(0.01, "KitKat", "1111111111117");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInicializarConPrecioNombreCorrectosUpcMenosDeDoceDigitos() {
		@SuppressWarnings("unused")
		Producto productoUpcTamanioMenorDoce = new Producto(0.01, "KitKat", "11111111117");
	}

	// COMPROBACIONES PARA setPrecio()
	// Clases válidas
	@Test
	public void testSetPrecioConPrecioValido() {
		Producto producto = new Producto(0.01, "KitKat", "111111111117");
		producto.setPrecio(0.02);
	}

	// Clases no válidas
	@Test(expected = IllegalArgumentException.class)
	public void testSetPrecioConPrecioCero() {
		Producto productoNulo = new Producto(0.01, "KitKat", "111111111117");
		productoNulo.setPrecio(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPrecioConPrecioNegativo() {
		Producto productoNegativo = new Producto(0.01, "KitKat", "111111111117");
		productoNegativo.setPrecio(-0.01);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPrecioConPrecioConMasDeDosDecimales() {
		Producto productoDecimalesMal = new Producto(0.01, "KitKat", "111111111117");
		productoDecimalesMal.setPrecio(0.001);

	}
}
