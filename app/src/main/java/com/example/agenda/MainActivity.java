package com.example.agenda;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

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
        GestionFicheros.leerdatos(this);

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
                createsimpledialog(position).show();
                return true;
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ModificarActivity.class);
                intent.putExtra("nota", position);
                startActivity(intent);
            }
        });

    }

    public AlertDialog createsimpledialog(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Eliminando Nota")
                .setMessage("¿Seguro que quieres eliminar esta nota")
        .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listNotas.remove(position);
                lna.notifyDataSetChanged();
            }
        })
        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO: cancelar operacion
            }
        });

        return builder.create();
    }

    protected void onStart() {
        super.onStart();
      lna.notifyDataSetChanged();

    }

    @Override
    protected void onPause() {
        super.onPause();
        GestionFicheros.guardardatos(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.Item_privacidad:
                Toast.makeText(this, "Has pulsado privacidad", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                intent.putExtra("texto", R.string.textolargo);
                startActivity(intent);
                return true;
            case R.id.Item_sincronizar:
                Toast.makeText(this, "Has pulsado sincronizar", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Item_acerca:
                Toast.makeText(this, "Has pulsado Acerca De", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(MainActivity.this, InfoActivity.class);
                intent2.putExtra("texto", R.string.acercade);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
