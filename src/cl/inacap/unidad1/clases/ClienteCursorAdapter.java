package cl.inacap.unidad1.clases;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ClienteCursorAdapter extends CursorAdapter {

	private ClienteDbAdapter dbAdapter = null;

	public ClienteCursorAdapter(Context context, Cursor c) {
		super(context, c);
		dbAdapter = new ClienteDbAdapter(context);
		dbAdapter.abrir();
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		TextView tv = (TextView) view;

		tv.setText(cursor.getString(cursor
				.getColumnIndex(ClienteDbAdapter.C_COLUMNA_NOMBRE)));

		if (cursor.getString(
				cursor.getColumnIndex(ClienteDbAdapter.C_COLUMNA_ESTADO))
				.equals("S")) {
			tv.setTextColor(Color.BLACK);
		} else {
			tv.setTextColor(Color.GRAY);
		}
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		final LayoutInflater inflater = LayoutInflater.from(context);
		final View view = inflater.inflate(
				android.R.layout.simple_dropdown_item_1line, parent, false);

		return view;
	}
	
}
