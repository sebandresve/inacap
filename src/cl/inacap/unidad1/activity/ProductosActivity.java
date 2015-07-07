package cl.inacap.unidad1.activity;

import java.util.ArrayList;

import cl.inacap.unidad1.clases.Producto;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ProductosActivity extends Activity {
	private ArrayAdapter<Producto> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_productos);

		ListView lv_productos = (ListView) findViewById(R.id.lv_productos);

		Producto producto = new Producto();
		ArrayList<Producto> productos = producto.listaProductos();

		adapter = new ArrayAdapter<Producto>(getApplicationContext(),
				android.R.layout.simple_spinner_item, productos);

		lv_productos.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.productos, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
