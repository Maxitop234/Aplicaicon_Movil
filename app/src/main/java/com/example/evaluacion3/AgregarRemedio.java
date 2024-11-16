package com.example.evaluacion3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class AgregarRemedio extends AppCompatActivity {

    private EditText nombreRemedio, cantidadProducto, fechaVencimiento, agregarMG, agregarDescripcion;
    private Spinner spinnerCategoria;
    private Button btnGrabar, btnSalir;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_remedios);

        // Inicializando vistas
        nombreRemedio = findViewById(R.id.nombreRemedio);
        cantidadProducto = findViewById(R.id.cantidadProducto);
        fechaVencimiento = findViewById(R.id.FechaVencimiento);
        agregarMG = findViewById(R.id.AgregarMG);
        agregarDescripcion = findViewById(R.id.agregarDescripcion);
        spinnerCategoria = findViewById(R.id.spinner);
        btnGrabar = findViewById(R.id.Grabar);
        btnSalir = findViewById(R.id.Salir);

        dbHelper = new DatabaseHelper(this);

        // Botón Grabar
        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreRemedio.getText().toString();
                int cantidad = Integer.parseInt(cantidadProducto.getText().toString());
                String fecha = fechaVencimiento.getText().toString();
                int mg = Integer.parseInt(agregarMG.getText().toString());
                String categoria = spinnerCategoria.getSelectedItem().toString();
                String descripcion = agregarDescripcion.getText().toString();

                if (nombre.isEmpty() || fecha.isEmpty() || categoria.isEmpty() || descripcion.isEmpty()) {
                    Toast.makeText(AgregarRemedio.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    long id = dbHelper.addRemedio(nombre, cantidad, fecha, mg, categoria, descripcion);
                    if (id > 0) {
                        Toast.makeText(AgregarRemedio.this, "Remedio agregado con éxito", Toast.LENGTH_SHORT).show();
                        limpiarCampos();
                    } else {
                        Toast.makeText(AgregarRemedio.this, "Error al agregar remedio", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Botón Salir
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual
            }
        });
    }

    // Método para limpiar los campos después de guardar
    private void limpiarCampos() {
        nombreRemedio.setText("");
        cantidadProducto.setText("");
        fechaVencimiento.setText("");
        agregarMG.setText("");
        agregarDescripcion.setText("");
        spinnerCategoria.setSelection(0);
    }
}