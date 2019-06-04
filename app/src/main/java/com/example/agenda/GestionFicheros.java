package com.example.agenda;

import android.content.Context;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GestionFicheros {

    public static void guardardatos(Context context){


        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput("notas.txt", Context.MODE_PRIVATE);
            ObjectOutputStream cos = new ObjectOutputStream(fos);
            cos.writeObject(listDatos.listNotas);
            cos.close();
            fos.close();
            Toast.makeText(context, "Guardando Datos...", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void leerdatos(Context context){

        FileInputStream fis = null;
        try {
            fis = context.openFileInput("notas.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            listDatos.listNotas = (ArrayList)ois.readObject();
            ois.close();
            fis.close();
            Toast.makeText(context, "Leyendo Datos...", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
