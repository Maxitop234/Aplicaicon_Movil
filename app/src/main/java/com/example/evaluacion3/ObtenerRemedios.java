package com.example.evaluacion3;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ObtenerRemedios extends AppCompatActivity {

    private ListView listView;
    private ArrayList<ModeloRemedios> remediosList;
    private CustomAdapter customAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_remedios);

        listView = findViewById(R.id.lv);
        databaseHelper = new DatabaseHelper(this);

        // la lista es organizada
        remediosList = databaseHelper.getAllRemedios();


        customAdapter = new CustomAdapter(this, remediosList);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ObtenerRemedios.this, CRUDRemedios.class);
                intent.putExtra("remedio", remediosList.get(position));
                startActivity(intent);
            }
        });
    }
}