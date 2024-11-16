package com.example.evaluacion3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class CRUDRemedios extends AppCompatActivity {
    private ModeloRemedios modeloRemedios;
    private EditText ModificarNombreRemedio, ModificarCantidadProducto, ModificarFechaVencimiento, ModificarMG, ModificarDescripcion;
    private Spinner ModificarSpinner;
    private Button btnModificar;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_remedios);

        Intent intent = getIntent();
        modeloRemedios = (ModeloRemedios) intent.getSerializableExtra("user");

        databaseHelper = new DatabaseHelper(this);

        ModificarNombreRemedio = findViewById(R.id.ModificarNombreRemedio);
        ModificarCantidadProducto = findViewById(R.id.ModificarCantidadProducto);
        ModificarFechaVencimiento = findViewById(R.id.ModificarFechaVencimiento);
        ModificarMG = findViewById(R.id.ModificarMG);
        ModificarSpinner = findViewById(R.id.ModificarSpinner);
        ModificarDescripcion = findViewById(R.id.ModificarDescripcion);
        btnModificar = findViewById(R.id.btnModificar);

        // Configurar el Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Remedios, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ModificarSpinner.setAdapter(adapter);

        // Llenar los campos con los datos actuales
        ModificarNombreRemedio.setText(modeloRemedios.getNombre());
        ModificarCantidadProducto.setText(String.valueOf(modeloRemedios.getCantidad()));
        ModificarFechaVencimiento.setText(modeloRemedios.getFechaVencimiento());
        ModificarMG.setText(String.valueOf(modeloRemedios.getMg()));
        ModificarDescripcion.setText(modeloRemedios.getDescripcion());

        // Seleccionar el valor en el Spinner
        if (modeloRemedios.getCategoria() != null) {
            int spinnerPosition = adapter.getPosition(modeloRemedios.getCategoria());
            ModificarSpinner.setSelection(spinnerPosition);
        }

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = ModificarNombreRemedio.getText().toString();
                int cantidad = Integer.parseInt(ModificarCantidadProducto.getText().toString());
                String fechaVencimiento = ModificarFechaVencimiento.getText().toString();
                int mg = Integer.parseInt(ModificarMG.getText().toString());
                String categoria = ModificarSpinner.getSelectedItem().toString();
                String descripcion = ModificarDescripcion.getText().toString();

                // Actualizar los datos en la base de datos
                databaseHelper.updateRemedio(modeloRemedios.getId(), nombre, cantidad, fechaVencimiento, mg, categoria, descripcion);
            }
        });
    }
}
