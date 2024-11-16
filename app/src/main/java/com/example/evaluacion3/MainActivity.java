package com.example.evaluacion3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd, btnCrud, btnVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Le asignamos a las variables por id los botones
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCrud = (Button) findViewById(R.id.btnCrud);
        btnVer = (Button) findViewById(R.id.btnVer);

        //Boton para poder ir a la vista Agregar Remedios
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentadd = new Intent(MainActivity.this,AgregarRemedio.class);
                startActivity(intentadd);
            }
        });

        //Boton para poder ir a la vista Modificar Remedios
        btnCrud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentver = new Intent(MainActivity.this, ObtenerRemedios.class);
                startActivity(intentver);
            }
        });

        //Boton para poder ir a la vista Ver Remedios
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentver = new Intent(MainActivity.this, ObtenerRemedios.class);
                startActivity(intentver);
            }
        });
    }
}