package com.example.evaluacion3;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

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
        modeloRemedios = (ModeloRemedios) intent.getSerializableExtra("remedio");

        databaseHelper = new DatabaseHelper(this);

        ModificarNombreRemedio = findViewById(R.id.ModificarNombreRemedio);
        ModificarCantidadProducto = findViewById(R.id.ModificarCantidadProducto);
        ModificarFechaVencimiento = findViewById(R.id.ModificarFechaVencimiento);
        ModificarMG = findViewById(R.id.ModificarMG);
        ModificarSpinner = findViewById(R.id.ModificarSpinner);
        ModificarDescripcion = findViewById(R.id.ModificarDescripcion);
        btnModificar = findViewById(R.id.btnModificar);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Remedios, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ModificarSpinner.setAdapter(adapter);

        ModificarNombreRemedio.setText(modeloRemedios.getNombre());
        ModificarCantidadProducto.setText(String.valueOf(modeloRemedios.getCantidad()));
        ModificarFechaVencimiento.setText(modeloRemedios.getFechaVencimiento());
        ModificarMG.setText(String.valueOf(modeloRemedios.getMg()));
        ModificarDescripcion.setText(modeloRemedios.getDescripcion());

        if (modeloRemedios.getCategoria() != null) {
            int spinnerPosition = adapter.getPosition(modeloRemedios.getCategoria());
            ModificarSpinner.setSelection(spinnerPosition);
        }

        ModificarFechaVencimiento.setFocusable(false);
        ModificarFechaVencimiento.setClickable(true);
        ModificarFechaVencimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CRUDRemedios.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String fecha = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                ModificarFechaVencimiento.setText(fecha);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = ModificarNombreRemedio.getText().toString();
                String cantidadStr = ModificarCantidadProducto.getText().toString();
                String fechaVencimiento = ModificarFechaVencimiento.getText().toString();
                String mgStr = ModificarMG.getText().toString();
                String categoria = ModificarSpinner.getSelectedItem().toString();
                String descripcion = ModificarDescripcion.getText().toString();

                if (nombre.isEmpty() || cantidadStr.isEmpty() || fechaVencimiento.isEmpty() || mgStr.isEmpty() || categoria.isEmpty() || descripcion.isEmpty()) {
                    Toast.makeText(CRUDRemedios.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int cantidad = Integer.parseInt(cantidadStr);
                    int mg = Integer.parseInt(mgStr);

                    int isUpdated = databaseHelper.updateRemedio(modeloRemedios.getId(), nombre, cantidad, fechaVencimiento, mg, categoria, descripcion);

                    if (isUpdated > 0) {
                        Toast.makeText(CRUDRemedios.this, "Remedio actualizado con éxito", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(CRUDRemedios.this, "Error al actualizar remedio", Toast.LENGTH_SHORT).show();
                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(CRUDRemedios.this, "Por favor ingresa valores numéricos válidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
