package cl.inacap.unidad1.clases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ClienteDbHelper extends SQLiteOpenHelper {

	private static int version = 1;
	private static String name = "ClienteDb";
	private static CursorFactory factory = null;

	public ClienteDbHelper(Context context) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		Log.i(this.getClass().toString(), "Creando base de datos");

		db.execSQL("CREATE TABLE CLIENTE(" + " _id INTEGER PRIMARY KEY,"
				+ " cli_nombre TEXT NOT NULL, " + " cli_direccion TEXT, "
				+ " cli_telefono INTEGER" + ")");

		db.execSQL("CREATE UNIQUE INDEX cli_nombre ON CLIENTE(cli_nombre ASC)");

		Log.i(this.getClass().toString(), "Tabla CLIENTE creada");

		// insertamos datos iniciales
		db.execSQL("INSERT INTO CLIENTE(_id, cli_nombre, cli_direccion, cli_telefono) VALUES(1,'Sebastian','Pasaje #084',88971402)");
		db.execSQL("INSERT INTO CLIENTE(_id, cli_nombre, cli_direccion, cli_telefono) VALUES(2,'Diego','Pasaje #085',88971403)");
		db.execSQL("INSERT INTO CLIENTE(_id, cli_nombre, cli_direccion, cli_telefono) VALUES(3,'Sandra','Pasaje #086',88971404)");
		db.execSQL("INSERT INTO CLIENTE(_id, cli_nombre, cli_direccion, cli_telefono) VALUES(4,'Nicole','Pasaje #087',88971405)");

		Log.i(this.getClass().toString(), "Datos iniciales CLIENTE insertados");

		Log.i(this.getClass().toString(), "Base de datos creada");

		// Aplicamos las sucesivas actualizaciones
		upgrade_2(db);
		upgrade_3(db);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Actualización a versión 2
		if (oldVersion < 2) {
			upgrade_2(db);
		}
		// Actualización a versión 3
		if (oldVersion < 3) {
			upgrade_3(db);
		}
	}

	private void upgrade_2(SQLiteDatabase db) {
		//
		// Upgrade versión 3: Incluir estado
		//
		db.execSQL("ALTER TABLE CLIENTE ADD cli_estado   VARCHAR2(1) NOT NULL DEFAULT 'N'");

		Log.i(this.getClass().toString(), "Actualización versión 3 finalizada");
	}

	private void upgrade_3(SQLiteDatabase db) {
		//
		// Upgrade versión 2: definir algunos datos de ejemplo
		//
		db.execSQL("UPDATE CLIENTE SET cli_nombre = 'Sebastián Vásquez Espinoza',"
				+ "              cli_direccion = 'Estero Codegua #084',"
				+ "              cli_telefono = 88971402,"
				+ "              cli_estado = 'S' " + "WHERE _id = 1");

		Log.i(this.getClass().toString(), "Actualización versión 2 finalizada");
	}

}
