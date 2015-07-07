package cl.inacap.unidad1.clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

public class ClienteDbAdapter {

	// definimos constante con el nombre de la tabla
	public static final String C_TABLA = "CLIENTE";

	// definimos constantes con el nombre de las columnas de la tabla
	public static final String C_COLUMNA_ID = "_id";
	public static final String C_COLUMNA_NOMBRE = "cli_nombre";
	public static final String C_COLUMNA_DIRECCION = "cli_direccion";
	public static final String C_COLUMNA_TELEFONO = "cli_telefono";
	public static final String C_COLUMNA_ESTADO = "cli_estado";

	private Context contexto;
	private ClienteDbHelper dbHelper;
	private SQLiteDatabase db;

	// definimos lista de columnas de la tabla para utilizarla en las consultas
	// a la base de datos
	private String[] columnas = new String[] { C_COLUMNA_ID, C_COLUMNA_NOMBRE,
			C_COLUMNA_DIRECCION, C_COLUMNA_TELEFONO, C_COLUMNA_ESTADO };

	public ClienteDbAdapter(Context context) {
		this.contexto = context;
	}

	public ClienteDbAdapter abrir() throws SQLException {
		dbHelper = new ClienteDbHelper(contexto);
		db = dbHelper.getWritableDatabase();
		return this;
	}

	public void cerrar() {
		dbHelper.close();
	}

	// devuelve cursor con todas las columnas de la tabla
	public Cursor getCursor() throws SQLException {
		Cursor c = db.query(true, C_TABLA, columnas, null, null, null, null,
				null, null);

		return c;
	}

	// Devuelve cursor con todas las columnas del registro
	public Cursor getRegistro(long id) throws SQLException {
		Cursor c = db.query(true, C_TABLA, columnas, C_COLUMNA_ID + "=" + id,
				null, null, null, null, null);

		// Nos movemos al primer registro de la consulta
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Inserta los valores en un registro de la tabla
	public long insert(ContentValues reg) {
		if (db == null)
			abrir();

		return db.insert(C_TABLA, null, reg);
	}

	// Eliminar el registro con el identificador indicado
	public long delete(long id) {
		if (db == null)
			abrir();

		return db.delete(C_TABLA, "_id=" + id, null);
	}

	// Modificar el registro
	public long update(ContentValues reg) {
		long result = 0;

		if (db == null)
			abrir();

		if (reg.containsKey(C_COLUMNA_ID)) {

			// Obtenemos el id y lo borramos de los valores
			long id = reg.getAsLong(C_COLUMNA_ID);

			reg.remove(C_COLUMNA_ID);

			// Actualizamos el registro con el identificador que hemos extraido
			result = db.update(C_TABLA, reg, "_id=" + id, null);
		}
		return result;
	}

}
