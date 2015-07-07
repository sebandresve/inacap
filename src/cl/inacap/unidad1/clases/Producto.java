package cl.inacap.unidad1.clases;

import java.util.ArrayList;

public class Producto {
	public int id_producto;
	public String nombre_producto;
	public int cantidad_producto;
	public int fecha_entrega_producto;
	public int precio_producto;

	// Se genera y obtiene la lista de productos
	public ArrayList<Producto> listaProductos() {
		ArrayList<Producto> lista = new ArrayList<Producto>();

		Producto producto = new Producto();
		producto.id_producto = 1;
		producto.nombre_producto = "Cuaderno";
		producto.cantidad_producto = 10;
		producto.fecha_entrega_producto = 31 / 05 / 2015;
		producto.precio_producto = 990;

		lista.add(producto);

		producto = new Producto();
		producto.id_producto = 2;
		producto.nombre_producto = "Lapiz";
		producto.cantidad_producto = 5;
		producto.fecha_entrega_producto = 31 / 05 / 2015;
		producto.precio_producto = 350;

		lista.add(producto);

		producto = new Producto();
		producto.id_producto = 3;
		producto.nombre_producto = "Mochila";
		producto.cantidad_producto = 1;
		producto.fecha_entrega_producto = 31 / 05 / 2015;
		producto.precio_producto = 9990;

		lista.add(producto);

		producto = new Producto();
		producto.id_producto = 4;
		producto.nombre_producto = "Notebook";
		producto.cantidad_producto = 1;
		producto.fecha_entrega_producto = 31 / 05 / 2015;
		producto.precio_producto = 350000;

		lista.add(producto);

		producto = new Producto();
		producto.id_producto = 5;
		producto.nombre_producto = "Borrador";
		producto.cantidad_producto = 3;
		producto.fecha_entrega_producto = 31 / 05 / 2015;
		producto.precio_producto = 2000;

		lista.add(producto);

		return lista;
	}

	// agrega producto
	public void addProducto(String nombre, int cantidad, int fecha, int precio) {
		ArrayList<Producto> lista = new ArrayList<Producto>();

		Producto producto = new Producto();
		producto.nombre_producto = nombre;
		producto.cantidad_producto = cantidad;
		producto.fecha_entrega_producto = fecha;
		producto.precio_producto = precio;

		lista.add(producto);
	}

	// Forma String de la clase
	public String toString() {
		return String.valueOf(this.nombre_producto) + ": Cantidad("
				+ this.cantidad_producto + ") Total: $"
				+ this.cantidad_producto * this.precio_producto;
	}
}
