package com.example.agenda;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.agenda.datos.Notas;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.example.agenda.listDatos.listNotas;

public class Crear_Nota extends AppCompatActivity {

    EditText nueva;
    EditText fecha;
    Spinner categoria;
    Button guardar;
    Button cancel;

    String myFormat = "dd '/' MMM '/' yyyy";
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
    Calendar mycalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear__nota);

        nueva = (EditText)findViewById(R.id.txtnewnote);
        fecha = (EditText)findViewById(R.id.txtdate);
        categoria = (Spinner)findViewById(R.id.spinner);
        guardar = (Button)findViewById(R.id.btnguardar);
        cancel = (Button)findViewById(R.id.btncancel);

        fecha.setText(sdf.format(mycalendar.getTime()));


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Notas.getCategorias());

        categoria.setAdapter(dataAdapter);

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                leerfecha(mycalendar);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoria2 = categoria.getSelectedItem().toString();
                String textto = nueva.getText().toString();

                listNotas.add(0,  new Notas(mycalendar, textto, categoria2));

                finish();
            }
        });
    }

    private void leerfecha(Calendar fecha){

        int year = fecha.get(Calendar.YEAR);
        int month = fecha.get(Calendar.MONTH);
        int day = fecha.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dtp = new DatePickerDialog(Crear_Nota.this, datepickerListener, year, month, day);

        dtp.setTitle("Selecciona fecha");
        dtp.show();
    }

    private  DatePickerDialog.OnDateSetListener datepickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            mycalendar.set(Calendar.YEAR, year);
            mycalendar.set(Calendar.MONTH, month);
            mycalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            fecha.setText(sdf.format(mycalendar.getTime()));

        }
    };

}
