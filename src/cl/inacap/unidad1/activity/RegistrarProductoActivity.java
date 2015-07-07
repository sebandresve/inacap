package cl.inacap.unidad1.activity;

import cl.inacap.unidad1.clases.Producto;
import cl.inacap.unidad1.clases.Usuario;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarProductoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registrar_producto);

		Button btn_guardar = (Button) findViewById(R.id.btn_guardar);
		btn_guardar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText txt_nombre_producto = (EditText) findViewById(R.id.txt_nombre_producto);
				EditText txt_cantidad = (EditText) findViewById(R.id.txt_cantidad);
				EditText txt_fecha = (EditText) findViewById(R.id.txt_fecha);
				EditText txt_precio = (EditText) findViewById(R.id.txt_precio);

				/*
				 * Producto producto = new Producto();
				 * 
				 * producto.addProducto(txt_nombre_producto.getText().toString(),
				 * Integer.parseInt(txt_cantidad.getText().toString()),
				 * Integer.parseInt(txt_fecha.getText().toString()),
				 * Integer.parseInt(txt_precio.getText().toString()));
				 */

				Toast.makeText(RegistrarProductoActivity.this,
						"Producto Guardado", Toast.LENGTH_SHORT).show();

				txt_nombre_producto.setText("");
				txt_cantidad.setText("");
				txt_fecha.setText("");
				txt_precio.setText("");
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registrar_producto, menu);
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
