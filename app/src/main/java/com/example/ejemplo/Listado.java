package com.example.ejemplo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Listado extends AppCompatActivity {

    private TableLayout tblistado;
    private String[] cabecera = {"Id", "Nombre", "Apellido"};
    private DynamicTable creaTabla;
    private ArrayList<String[]> datos = new ArrayList<String[]>();
    FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        tblistado = findViewById(R.id.tblistado);
        creaTabla = new DynamicTable(tblistado, getApplicationContext());
        creaTabla.setCabecera(cabecera);
        TraerDatos();
    }

    public void TraerDatos() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.column1,
                FeedReaderContract.FeedEntry.column2
        };
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.name_table,
                projection,
                null, null, null, null, null
        );
        while (cursor.moveToNext()) {
            String[] fila = new String[3];
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.column1));
            String apellido = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.column2));
            fila[0] = itemId + "";
            fila[1] = nombre;
            fila[2] = apellido;
            datos.add(fila);
        }
        cursor.close();
        db.close();
        creaTabla.crearCabecera();
        creaTabla.setDatos(datos);
        creaTabla.crearFilas();
    }
    public void regresar(View vista){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }
}
