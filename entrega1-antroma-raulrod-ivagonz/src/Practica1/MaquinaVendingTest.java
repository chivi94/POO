package Practica1;

import static org.junit.Assert.*;

import org.junit.Test;

import fabricante.externo.tarjetas.TarjetaMonedero;

public class MaquinaVendingTest {
	// CONSTANTES A USAR
	private final String SALDO_INICIAL_CARGAR_SALDO = "A156Bv09_1zXo894";
	private final String DESCONTAR_SALDO = "6Z1y00Nm31aA-571";
	private final String SALDO_INICIAL_CARGAR_SALDO_MAL = "A156Bv09_1zXo893";
	private final String DESCONTAR_SALDO_MAL = "6Z1y00Nm31aA-570";

	// Comprobaciones para el constructor
	// Clases Validas
	@Test
	public void testInicializarPasandoLineasCorrectas() {
		@SuppressWarnings("unused")
		MaquinaVending maquina = new MaquinaVending(1);
	}

	// Clases no validas

	@Test(expected = IllegalArgumentException.class)
	public void testInicializarConLineasCero() {
		@SuppressWarnings("unused")
		MaquinaVending maquina = new MaquinaVending(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInicializarConLineasNegativas() {
		@SuppressWarnings("unused")
		MaquinaVending maquina = new MaquinaVending(-1);
	}

	// Comprobaciones meterProducto
	// Clases válidas
	@Test
	public void testMeterProductoCantidadValidasLineaCero() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 1);
	}

	@Test
	public void testMeterProductoCantidadValidasLineaBordeSuperior() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), maquina.tamanioMaquina() - 1, 1);
	}

	// Clases no válidas
	@Test(expected = IllegalArgumentException.class)
	public void testMeterProductoCantidadValidaConLineaNegativa() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), -1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMeterProductoCantidadValidaConLineaSobreBordeSuperior() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), maquina.tamanioMaquina(), 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMeterProductoLineaValidasConCantidadCero() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMeterProductoLineaValidasConCantidadNegativa() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, -1);
	}

	// Comprobaciones para pagar
	// Clases Válidas

	@Test
	public void testPagarPasandoTarjetaCredencialLineasValidosConLineasCero() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 3);
		maquina.pagar(new TarjetaMonedero(SALDO_INICIAL_CARGAR_SALDO), DESCONTAR_SALDO, 0);
	}

	@Test
	public void testPagarPasandoTarjetaCredencialLineasValidosConLineasMenorQueLasCreadas() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 3);
		maquina.pagar(new TarjetaMonedero(SALDO_INICIAL_CARGAR_SALDO), DESCONTAR_SALDO, maquina.tamanioMaquina() - 1);
	}

	// Clases No Validas
	@Test(expected = IllegalArgumentException.class)
	public void testPagarPasandoTarjetaCredencialValidosPeroLineasNegativas() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.pagar(new TarjetaMonedero(SALDO_INICIAL_CARGAR_SALDO), DESCONTAR_SALDO, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPagarPasandoTarjetaCredencialValidosPeroLineasMayoresDeLasCreadas() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 3);
		maquina.pagar(new TarjetaMonedero(SALDO_INICIAL_CARGAR_SALDO), DESCONTAR_SALDO, maquina.tamanioMaquina());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPagarPasandoCredencialLineasValidosPeroTarjetaNull() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.pagar(null, DESCONTAR_SALDO, 1);
	}

	@Test(expected = NullPointerException.class)
	public void testPagarPasandoTarjetaLineasValidosPeroCredencialNull() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 3);
		maquina.pagar(new TarjetaMonedero(SALDO_INICIAL_CARGAR_SALDO), null, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPagarPasandoTarjetaLineasValidosPeroCredencialCadenaVacia() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.pagar(new TarjetaMonedero(SALDO_INICIAL_CARGAR_SALDO), "", 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPagarPasandoTarjetaLineasValidosPeroCredencialIncorrecto() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.pagar(new TarjetaMonedero(SALDO_INICIAL_CARGAR_SALDO), DESCONTAR_SALDO_MAL, 1);
	}

	// Comprobaciones para ObtenerPrecio
	// Clases Validas

	@Test
	public void testObtenerPrecioConLineasCero() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 3);
		maquina.obtenerPrecio(0);
	}

	@Test
	public void testObtenerPrecioConLineasBordeSuperior() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 3);
		maquina.obtenerPrecio(0);
	}

	// Clases no validas
	@Test(expected = IllegalArgumentException.class)
	public void testObtenerPrecioConLineaNegativa() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 3);
		maquina.obtenerPrecio(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testObtenerPrecioConLineaMayorQueBordeSuperior() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 3);
		maquina.obtenerPrecio(maquina.tamanioMaquina());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testObtenerPrecioConLineaSinProducto() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.obtenerPrecio(0);
	}

	// Comprobaciones para productoEnLinea
	// Clases válidas
	@Test
	public void testProductoEnLineaLineaCero() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 3);
		maquina.productoEnLinea(0);
	}

	@Test
	public void testProductoEnLineaLineaBordeSuperior() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 3);
		maquina.productoEnLinea(maquina.tamanioMaquina() - 1);
	}

	// Clases no válidas

	@Test(expected = IllegalArgumentException.class)
	public void testProductoEnLineaLineaNegativa() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 3);
		maquina.productoEnLinea(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProductoEnLineaLineaPorEncimaDelBordeSuperior() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 3);
		maquina.productoEnLinea(maquina.tamanioMaquina());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProductoEnLineaLineaSinProducto() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.productoEnLinea(0);
	}

	// Comprobaciones para CantidadProducto
	// Clases válidas

	@Test
	public void testCantidadProductoLineaCero() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 3);
		maquina.cantidadProducto(0);
	}

	@Test
	public void testCantidadProductoLineaBordeSuperior() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 3);
		maquina.cantidadProducto(maquina.tamanioMaquina() - 1);
	}

	// Clases no válidas

	@Test(expected = IllegalArgumentException.class)
	public void testCantidadProductoLineaNegativa() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 3);
		maquina.cantidadProducto(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCantidadProductoLineaSinProducto() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.cantidadProducto(0);
	}

	// Comprobaciones reponerLinea
	// Clases Válidas
	@Test
	public void testReponerLineaCantidadValidasConLineaCero() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 3);
		maquina.reponerLinea(0, 1);
	}

	@Test
	public void testReponerLineaCantidadValidasConLineaBordeSuperior() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 3);
		maquina.reponerLinea(maquina.tamanioMaquina() - 1, 1);
	}

	// Clases no válidas
	@Test(expected = IllegalArgumentException.class)
	public void testReponerCantidadValidaConLineaNegativa() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), -1, 1);
		maquina.reponerLinea(-1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testReponerCantidadValidaConLineaSobreBordeSuperior() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), maquina.tamanioMaquina(), 1);
		maquina.reponerLinea(maquina.tamanioMaquina(), 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testReponerLineaValidaConCantidadCero() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 0);
		maquina.reponerLinea(0, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testReponerLineaValidaConCantidadNegativa() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, -1);
		maquina.reponerLinea(0, -1);
	}

	// Comprobaciones sacarProducto
	// Clases válidas
	@Test
	public void testSacarProductoConLineaCantidadValidasConLineaCero() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 3);
		maquina.sacarProducto(0, 1);
	}

	@Test
	public void testSacarProductoConLineaCantidadValidasConLineaBordeSuperior() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 3);
		maquina.sacarProducto(maquina.tamanioMaquina() - 1, 1);
	}

	// Clases no válidas
	@Test(expected = IllegalArgumentException.class)
	public void testSacarProductoConLineaNegativa() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), -1, 1);
		maquina.sacarProducto(-1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSacarProductoConLineaSobreBordeSuperior() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), maquina.tamanioMaquina(), 1);
		maquina.sacarProducto(maquina.tamanioMaquina(), 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSacarProductoConCantidadCero() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 0);
		maquina.sacarProducto(0, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSacarProductoConCantidadNegativa() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, -1);
		maquina.sacarProducto(0, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSacarProductoConCantidadSuperiorAlStockDelProducto() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, -1);
		maquina.sacarProducto(0, maquina.cantidadProducto(0) + 1);
	}

	// Comprobaciones vaciarLinea
	// Clases validas
	@Test
	public void testVaciarLineaConLineaCero() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 3);
		maquina.vaciarLinea(0);
	}

	@Test
	public void testVaciarLineaConLineaBordeSuperior() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, 3);
		maquina.vaciarLinea(maquina.tamanioMaquina() - 1);
	}

	// Clases no válidas
	@Test(expected = IllegalArgumentException.class)
	public void testVaciarLineaConLineaNegativa() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, -1);
		maquina.vaciarLinea(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testVaciarLineaConLineaSobreBordeSuperior() {
		MaquinaVending maquina = new MaquinaVending(1);
		maquina.meterProducto(new Producto(0.70, "Patatas fritas", "111111111117"), 0, -1);
		maquina.vaciarLinea(maquina.tamanioMaquina());
	}
}
