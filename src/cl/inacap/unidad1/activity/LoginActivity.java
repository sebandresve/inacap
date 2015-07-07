package cl.inacap.unidad1.activity;

import cl.inacap.unidad1.clases.ClienteDbHelper;
import cl.inacap.unidad1.clases.Usuario;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		// declaramos el controlador de la BBDD y accedemos en modo escritura
		ClienteDbHelper dbHelper = new ClienteDbHelper(getBaseContext());

		SQLiteDatabase db = dbHelper.getWritableDatabase();

		Toast.makeText(getBaseContext(), "Base de datos preparada",
				Toast.LENGTH_LONG).show();

		Button btn_ingresar = (Button) findViewById(R.id.btn_ingresar);
		btn_ingresar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				validarLoginUsuario();
			}

		});
	}

	// Se realiza la validacion del usuario
	public void validarLoginUsuario() {
		EditText txt_login = (EditText) findViewById(R.id.txt_login);
		EditText txt_contrasena = (EditText) findViewById(R.id.txt_contrasena);

		Usuario usuario = new Usuario();
		if (usuario.validarLogin(txt_login.getText().toString(), txt_contrasena
				.getText().toString())) {
			Toast.makeText(LoginActivity.this, "Usuario correcto",
					Toast.LENGTH_SHORT).show();

			txt_login.setText("");
			txt_contrasena.setText("");

			Intent intent = new Intent(LoginActivity.this,
					ClientesActivity.class);
			LoginActivity.this.startActivity(intent);
		} else {
			Toast.makeText(LoginActivity.this,
					"Usuario o contraseña incorrectos", Toast.LENGTH_SHORT)
					.show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
