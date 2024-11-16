package com.example.evaluacion3;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ObtenerRemedios extends AppCompatActivity {

    private ListView listView;
    private EditText searchBar;
    private ArrayList<ModeloRemedios> remediosList;
    private ArrayList<ModeloRemedios> filteredList;
    private CustomAdapter customAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_remedios);

        listView = findViewById(R.id.lv);
        searchBar = findViewById(R.id.searchBar);
        databaseHelper = new DatabaseHelper(this);

        // Cargar todos los remedios
        remediosList = databaseHelper.getAllRemedios();
        filteredList = new ArrayList<>(remediosList); // Comenxar con la lista entera

        customAdapter = new CustomAdapter(this, filteredList);
        listView.setAdapter(customAdapter);

        // La barra de busqueda
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No hacer nada antes que el texto cambie
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterList(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No hacer nada despues de que el texto cambio
            }
        });
    }

    private void filterList(String query) {
        filteredList.clear();

        if (query.isEmpty()) {
            filteredList.addAll(remediosList); // Si la query no tienen nada mostrar todos los items
        } else {
            for (ModeloRemedios remedio : remediosList) {
                if (remedio.getNombre().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(remedio);
                }
            }
        }

        customAdapter.notifyDataSetChanged(); //  Notificar al adaptador de los cambios
    }
}