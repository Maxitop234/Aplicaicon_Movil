package com.example.evaluacion3;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class CRUDRemedios  extends AppCompatActivity {
    private ModeloRemedios modeloRemedios;
    private EditText ModificarNombreRemedio, ModificarCantidadProducto, ModificarFechaVencimiento, ModificarMG, ModificarSpinner, ModificarDescripcion;
    private Button btnModificar;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_remedios);

        Intent intent = getIntent();
        modeloRemedios = (ModeloRemedios)intent.getSerializableExtra('user');

        databaseHelper = new DatabaseHelper(this);

        ModificarNombreRemedio = (EditText)findViewById(R.id.ModificarNombreRemedio);
        ModificarCantidadProducto = (EditText)findViewById(R.id.ModificarCantidadProducto);
        ModificarFechaVencimiento = (EditText) findViewById(R.id.ModificarFechaVencimiento);
        ModificarMG = (EditText) findViewById(R.id.ModificarMG);
        ModificarSpinner = (Spinner) findViewById(R.id.ModificarSpinner);
        ModificarDescripcion = (EditText) findViewById(R.id.ModificarDescripcion);
        btnModificar = (Button) findViewById(R.id.btnModificar);

        ModificarNombreRemedio.setText(modeloRemedios.getNombre());
        ModificarCantidadProducto.setText(modeloRemedios.getCantidad());
        ModificarFechaVencimiento.setText(modeloRemedios.getFechaVencimiento());
        ModificarMG.setText(modeloRemedios.getMg());
        ModificarSpinner.setText(modeloRemedios.);
        ModificarDescripcion.setText(modeloRemedios.getDescripcion());

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.updateRemedio(modeloRemedios.getId(),ModificarNombreRemedio.getText().toString(), ModificarCantidadProducto.getText().toString(), ModificarFechaVencimiento.getText().toString(), ModificarMG.getText().toString(), ModificarDescripcion.getText().toString()
                );
            }
        });
    }
}
