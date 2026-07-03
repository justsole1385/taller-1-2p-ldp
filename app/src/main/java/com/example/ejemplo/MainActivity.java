package com.example.ejemplo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText txtid;
    private EditText txtnombre;
    private EditText txtapellido;


    private FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtid = findViewById(R.id.txtid);
        txtnombre = findViewById(R.id.txtnombre);
        txtapellido = findViewById(R.id.txtapellido);

    }
    public void listar(View vista){
        Intent listar = new Intent(this, Listado.class);
        startActivity(listar);
    }

    public void guardar(View vista){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.column1, txtnombre.getText().toString());
        values.put(FeedReaderContract.FeedEntry.column2, txtapellido.getText().toString());
        long newRowId = db.insert(FeedReaderContract.FeedEntry.name_table, null, values);
        Toast.makeText(this, "Registro guardado con ID: " + newRowId, Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void buscar(View vista){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.column1,
                FeedReaderContract.FeedEntry.column2
        };
        String selection = FeedReaderContract.FeedEntry._ID + " = ?";
        String[] selectionArgs = { txtid.getText().toString() };
        String sortOrder = FeedReaderContract.FeedEntry.column1 + " DESC";
        Cursor cursor= db.query(
                FeedReaderContract.FeedEntry.name_table,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        while (cursor.moveToNext()) {
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.column1));
            txtnombre.setText(nombre);
            String apellido = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.column2));
            txtapellido.setText(apellido);
        }
        cursor.close();
        db.close();

        }
    public void eliminar(View vista){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = FeedReaderContract.FeedEntry._ID + " = ?";
        String[] selectionArgs = { txtid.getText().toString() };
        int deletedRows = db.delete(FeedReaderContract.FeedEntry.name_table, selection, selectionArgs);
db.close();
Toast.makeText(getApplicationContext(),"se eliminó "+deletedRows+"registros",Toast.LENGTH_LONG).show();
    }
public void actualizar(View vista){
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        String nombre= txtnombre.getText().toString();
    String apellido= txtapellido.getText().toString();
    ContentValues values = new ContentValues();
    values.put(FeedReaderContract.FeedEntry.column1, nombre);
    values.put(FeedReaderContract.FeedEntry.column2, apellido);
    String selection = FeedReaderContract.FeedEntry._ID + " = ?";
    String[] selectionArgs = { txtid.getText().toString() };
    int count = db.update(
            FeedReaderContract.FeedEntry.name_table,
            values,
            selection,
            selectionArgs);
    Toast.makeText(getApplicationContext(),"se actualizo "+count+" registros",Toast.LENGTH_LONG).show();
    db.close();

}
    }
