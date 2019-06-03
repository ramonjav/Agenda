package com.example.agenda;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.agenda.datos.Notas;

import java.util.ArrayList;

import static com.example.agenda.listDatos.listNotas;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    ListadoNotasAdapter lna;
    FloatingActionButton nuevaNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView)findViewById(R.id.list);
        nuevaNote = (FloatingActionButton)findViewById(R.id.floatingActionButton7);

        //listNotas.add(new Notas("Hola", "Urgente"));

        lna = new ListadoNotasAdapter(this, listNotas);
        lista.setAdapter(lna);

        nuevaNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Crear_Nota.class);

                startActivity(intent);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listNotas.remove(position);
                lna.notifyDataSetChanged();
                return false;
            }
        });

    }

    protected void onStart() {
        super.onStart();

      lna.notifyDataSetChanged();
    }
}
