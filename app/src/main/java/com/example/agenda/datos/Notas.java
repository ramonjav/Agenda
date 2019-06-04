package com.example.agenda.datos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class Notas implements Serializable {

    private Calendar fecha;
    private String texto;
    private String categoria;
    private static String [] categorias = {"Urgente", "Importante", "Normal"};

    public Notas(String texto, String categoria){

        //this.fecha = Calendar.getInstance();
        this.texto = texto;
        this.categoria = comprobarcat(categoria);

    }

    public Notas(Calendar fecha, String texto, String categoria){

        this.fecha = fecha;
        this.texto = texto;
        this.categoria = comprobarcat(categoria);

    }

    private String comprobarcat(String cat){

        String aux = "Normal";

        for(int i = 0; i<categorias.length; i++){

            if(cat.equalsIgnoreCase(categorias[i])){

                aux = categorias[i];

            }
        }

        return aux;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public static String[] getCategorias() {
        return categorias;
    }

    public void setCategorias(String[] categorias) {
        this.categorias = categorias;
    }

    public String getFechaToString(){
        String myFormat = "dd'/'MM'/'yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

        return sdf.format(this.fecha.getTime());

    }

    public int getIndex(){
        int aux = 0;
        for(int i = 0;i<categorias.length;i++){
            if(this.categoria.equalsIgnoreCase(categorias[i])){
                aux = i;
            }
        }
    return aux;
    }

    @Override
    public String toString() {
        return "Notas{" +
                "fecha=" + fecha +
                ", texto='" + texto + '\'' +
                ", categoria='" + categoria + '\'' +
                ", categorias=" + Arrays.toString(categorias) +
                '}';
    }
}
