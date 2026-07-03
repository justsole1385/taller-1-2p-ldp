package com.example.ejemplo;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class DynamicTable {
    private TableLayout tabla;
    private Context contexto;
    private String[] cabecera;

    private ArrayList<String[]> datos;
    private TableRow fila;

    private TextView celda;

    public DynamicTable(TableLayout tabla, Context contexto) {
        this.tabla = tabla;
        this.contexto = contexto;

    }

    public void setCabecera(String[] cabecera) {
        this.cabecera = cabecera;
    }

    public void setDatos(ArrayList<String[]> datos) {
        this.datos = datos;
    }

    private void nuevaFila() {
        fila = new TableRow(contexto);
    }

    private void nuevaCelda() {
        celda = new TextView(contexto);
        celda.setGravity(Gravity.CENTER);
        celda.setTextSize(18);
        celda.setLayoutParams(parametrosCelda());
    }

    public void crearCabecera() {
        nuevaFila();
        for (String titulo : cabecera) {
            nuevaCelda();
            celda.setText(titulo);
            celda.setTextSize(24);
            celda.setBackgroundColor(Color.LTGRAY);
            celda.setTextColor(Color.BLACK);
            fila.addView(celda);
        }
        tabla.addView(fila);
    }

    public void crearFilas() {
        for (String[] datofila : datos) {
            nuevaFila();
            for (String dato : datofila) {
                nuevaCelda();
                celda.setText(dato);
                fila.addView(celda);
            }
            tabla.addView(fila);
        }
    }

    private TableRow.LayoutParams parametrosCelda() {
        TableRow.LayoutParams parametros = new TableRow.LayoutParams();
        parametros.setMargins(5, 5, 5, 5);
        parametros.weight=1;
        return parametros;
    }
}
