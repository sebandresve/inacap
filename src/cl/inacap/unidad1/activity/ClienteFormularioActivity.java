package cl.inacap.unidad1.activity;

import cl.inacap.unidad1.clases.ClienteDbAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ClienteFormularioActivity extends Activity {

	private ClienteDbAdapter dbAdapter;
	private Cursor cursor;

	// Modo del formulario
	private int modo;

	// Identificador del registro que se edita cuando la opción es MODIFICAR
	private long id;

	// Elementos de la vista
	private EditText nombre;
	private EditText direccion;
	private EditText telefono;
	private CheckBox estado;

	private Button boton_guardar;
	private Button boton_cancelar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cliente_formulario);

		Button btn_registrar = (Button) findViewById(R.id.btn_registrar);
		btn_registrar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ClienteFormularioActivity.this,
						RegistrarProductoActivity.class);
				ClienteFormularioActivity.this.startActivity(intent);
			}

		});

		Button btn_mostrar = (Button) findViewById(R.id.btn_mostrar);
		btn_mostrar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ClienteFormularioActivity.this,
						ProductosActivity.class);
				ClienteFormularioActivity.this.startActivity(intent);
			}

		});

		Intent intent = getIntent();
		Bundle extra = intent.getExtras();

		if (extra == null)
			return;

		// Obtenemos los elementos de la vista
		nombre = (EditText) findViewById(R.id.nombre);
		direccion = (EditText) findViewById(R.id.direccion);
		telefono = (EditText) findViewById(R.id.telefono);
		estado = (CheckBox) findViewById(R.id.estado);

		boton_guardar = (Button) findViewById(R.id.boton_guardar);
		boton_cancelar = (Button) findViewById(R.id.boton_cancelar);

		// Creamos el adaptador
		dbAdapter = new ClienteDbAdapter(this);
		dbAdapter.abrir();

		// Obtenemos el identificador del registro si viene indicado
		if (extra.containsKey(ClienteDbAdapter.C_COLUMNA_ID)) {
			id = extra.getLong(ClienteDbAdapter.C_COLUMNA_ID);
			consultar(id);
		}

		// Establecemos el modo del formulario
		establecerModo(extra.getInt(ClientesActivity.C_MODO));

		// Definimos las acciones para los dos botones
		boton_guardar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				guardar();
			}
		});

		boton_cancelar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				cancelar();
			}
		});

	}

	private void establecerModo(int m) {
		this.modo = m;

		if (modo == ClientesActivity.C_VISUALIZAR) {
			this.setTitle(nombre.getText().toString());
			this.setEdicion(false);
			boton_guardar.setVisibility(View.INVISIBLE);
			boton_cancelar.setVisibility(View.INVISIBLE);
		} else if (modo == ClientesActivity.C_CREAR) {
			this.setTitle(R.string.cliente_crear_titulo);
			this.setEdicion(true);
		} else if (modo == ClientesActivity.C_EDITAR) {
			this.setTitle(R.string.cliente_editar_titulo);
			this.setEdicion(true);
			boton_guardar.setVisibility(View.VISIBLE);
			boton_cancelar.setVisibility(View.VISIBLE);
		}
	}

	private void consultar(long id) {

		// Consultamos el centro por el identificador
		cursor = dbAdapter.getRegistro(id);

		nombre.setText(cursor.getString(cursor
				.getColumnIndex(ClienteDbAdapter.C_COLUMNA_NOMBRE)));
		direccion.setText(cursor.getString(cursor
				.getColumnIndex(ClienteDbAdapter.C_COLUMNA_DIRECCION)));
		telefono.setText(cursor.getString(cursor
				.getColumnIndex(ClienteDbAdapter.C_COLUMNA_TELEFONO)));
		estado.setChecked(cursor.getString(
				cursor.getColumnIndex(ClienteDbAdapter.C_COLUMNA_ESTADO))
				.equals("S"));
	}

	private void setEdicion(boolean opcion) {
		nombre.setEnabled(opcion);
		direccion.setEnabled(opcion);
		telefono.setEnabled(opcion);
		estado.setEnabled(opcion);
	}

	private void guardar() {

		// Obtenemos los datos del formulario
		ContentValues reg = new ContentValues();

		// Si estamos en modo edición añadimos el identificador del registro que
		// se utilizará en el update
		if (modo == ClientesActivity.C_EDITAR)
			reg.put(ClienteDbAdapter.C_COLUMNA_ID, id);

		reg.put(ClienteDbAdapter.C_COLUMNA_NOMBRE, nombre.getText().toString());
		reg.put(ClienteDbAdapter.C_COLUMNA_DIRECCION, direccion.getText()
				.toString());
		reg.put(ClienteDbAdapter.C_COLUMNA_TELEFONO, telefono.getText()
				.toString());
		reg.put(ClienteDbAdapter.C_COLUMNA_ESTADO, (estado.isChecked()) ? "S"
				: "N");

		if (modo == ClientesActivity.C_CREAR) {
			dbAdapter.insert(reg);
			Toast.makeText(ClienteFormularioActivity.this,
					R.string.cliente_crear_confirmacion, Toast.LENGTH_SHORT)
					.show();
		} else if (modo == ClientesActivity.C_EDITAR) {
			Toast.makeText(ClienteFormularioActivity.this,
					R.string.cliente_editar_confirmacion, Toast.LENGTH_SHORT)
					.show();
			dbAdapter.update(reg);
		}

		// Devolvemos el control
		setResult(RESULT_OK);
		finish();
	}

	private void cancelar() {
		setResult(RESULT_CANCELED, null);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.clear();

		if (modo == ClientesActivity.C_VISUALIZAR)
			getMenuInflater().inflate(R.menu.cliente_formulario_ver, menu);

		else
			getMenuInflater().inflate(R.menu.cliente_formulario_editar, menu);

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

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_eliminar:
			borrar(id);
			return true;

		case R.id.menu_cancelar:
			cancelar();
			return true;

		case R.id.menu_guardar:
			guardar();
			return true;

		case R.id.menu_editar:
			establecerModo(ClientesActivity.C_EDITAR);
			return true;

		}

		return super.onMenuItemSelected(featureId, item);
	}

	private void borrar(final long id) {

		// Borramos el registro con confirmación
		AlertDialog.Builder dialogEliminar = new AlertDialog.Builder(this);

		dialogEliminar.setIcon(android.R.drawable.ic_dialog_alert);
		dialogEliminar.setTitle(getResources().getString(
				R.string.cliente_eliminar_titulo));
		dialogEliminar.setMessage(getResources().getString(
				R.string.cliente_eliminar_mensaje));
		dialogEliminar.setCancelable(false);

		dialogEliminar.setPositiveButton(
				getResources().getString(android.R.string.ok),
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int boton) {
						dbAdapter.delete(id);
						Toast.makeText(ClienteFormularioActivity.this,
								R.string.cliente_eliminar_confirmacion,
								Toast.LENGTH_SHORT).show();

						// Devolvemos el control
						setResult(RESULT_OK);
						finish();
					}
				});

		dialogEliminar.setNegativeButton(android.R.string.no, null);

		dialogEliminar.show();

	}
}
