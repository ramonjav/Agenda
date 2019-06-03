package com.example.agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agenda.datos.Notas;

import java.util.ArrayList;

public class ListadoNotasAdapter extends ArrayAdapter<Notas> {

    private ArrayList<Notas> listanota;
    private Context mcontext;
    private int lastposition = -1;

    public ListadoNotasAdapter(Context context, ArrayList<Notas> listanota) {
        super(context, R.layout.activity_main, listanota);

        this.listanota = listanota;
        this.mcontext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Notas nota = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_nota, parent, false);
        }
        ImageView imagen = (ImageView) convertView.findViewById(R.id.imgcat);
        TextView txtfecha = (TextView) convertView.findViewById(R.id.txtfecha);
        TextView txttexto = (TextView) convertView.findViewById(R.id.txttexto);
        // Populate the data into the template view using the data object
        String [] categorias = nota.getCategorias();
        String categoria = nota.getCategoria();

        if(categoria.equalsIgnoreCase(categorias[0])){
            imagen.setImageResource(R.drawable.ic_notifications_active_black_24dp);
        }else if (categoria.equalsIgnoreCase(categorias[1])){
            imagen.setImageResource(R.drawable.amarillo);
        }else if(categoria.equalsIgnoreCase(categorias[2])){
            imagen.setImageResource(R.drawable.verde);
        }


        txtfecha.setText(nota.getFechaToString());
        txttexto.setText(nota.getTexto());
        // Return the completed view to render on screen
        return convertView;
    }


}
