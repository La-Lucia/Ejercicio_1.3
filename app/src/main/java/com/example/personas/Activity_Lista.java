package com.example.personas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.personas.config.SQLiteConexion;
import com.example.personas.config.tablas.Personas;
import com.example.personas.config.tablas.Transacciones;

import java.util.ArrayList;

public class Activity_Lista extends AppCompatActivity {

    Button Regresar;
    SQLiteConexion conexion;
    ListView listperson;
    ArrayList<Personas> lista;
    ArrayList<String> listaconcatenada;
    String nombre, apellido, correo, direccion;
    Integer edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        Regresar = (Button) findViewById(R.id.AL_BtnRegresar);
        listperson = (ListView) findViewById(R.id.AL_Lista);

        GetListPerson();
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaconcatenada );
        listperson.setAdapter(adp);


        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        listperson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                nombre = lista.get(i).getNombre();
                apellido = lista.get(i).getApellido();
                edad = lista.get(i).getEdad();
                correo = lista.get(i).getCorreo();
                direccion = lista.get(i).getDireccion();

                listperson.getSelectedView();

                Intent intent = new Intent(getApplicationContext(), Activity_Persona.class);

                intent.putExtra("nombre", nombre);
                intent.putExtra("apellido", apellido);
                intent.putExtra("edad", edad);
                intent.putExtra("correo", correo);
                intent.putExtra("direccion", direccion);

                startActivity(intent);
            }
        });

    }

    private void GetListPerson()
    {
        SQLiteDatabase db = conexion.getReadableDatabase(); // Base de datos en modo de lectura
        Personas listpersonas = null;

        lista = new ArrayList<Personas>();  // Lista de Objetos del tipo personas

        Cursor cursor = db.rawQuery(Transacciones.GetPersonas,null);

        while(cursor.moveToNext())
        {
            listpersonas = new Personas();
            listpersonas.setId(cursor.getInt(0));
            listpersonas.setNombre(cursor.getString(1));
            listpersonas.setApellido(cursor.getString(2));
            listpersonas.setEdad(cursor.getInt(3));
            listpersonas.setCorreo(cursor.getString(4));
            listpersonas.setDireccion(cursor.getString(5));

            lista.add(listpersonas);
        }

        cursor.close();

        LLenarLista();

    }

    private void LLenarLista()
    {
        listaconcatenada = new ArrayList<String>();

        for(int i =0;  i < lista.size(); i++)
        {
            listaconcatenada.add(lista.get(i).getId() + " | " +
                    lista.get(i).getNombre());
        }
    }

}