package com.example.personas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.personas.config.SQLiteConexion;
import com.example.personas.config.tablas.Transacciones;

public class Activity_Persona extends AppCompatActivity {

    EditText nombre, apellidos, correo, direccion, edad;
    Button eliminar, guardar;
    int ed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);

        nombre = (EditText) findViewById(R.id.AP_TxtNombre);
        apellidos = (EditText) findViewById(R.id.AP_TxtApellidos);
        correo = (EditText) findViewById(R.id.AP_TxtCorreo);
        direccion = (EditText) findViewById(R.id.AP_TxtDireccion);
        edad = (EditText) findViewById(R.id.AP_TxtEdad);
        eliminar = (Button) findViewById(R.id.AP_BtnEliminar);
        guardar = (Button) findViewById(R.id.AP_BtnModificar);

        Bundle resultado = getIntent().getExtras();
        nombre.setText(resultado.getString("nombre"));
        apellidos.setText(resultado.getString("apellido"));
        ed = resultado.getInt("edad");
        correo.setText(resultado.getString("correo"));
        direccion.setText(resultado.getString("direccion"));
        edad.setText(Integer.toString(ed));

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Eliminar();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Validar() == true){
                    Editar();
                }

            }
        });


    }

    private void Editar() {

        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombre, nombre.getText().toString());
        valores.put(Transacciones.apellido, apellidos.getText().toString());
        valores.put(Transacciones.edad, Integer.parseInt(edad.getText().toString()));
        valores.put(Transacciones.correo, correo.getText().toString());
        valores.put(Transacciones.direccion, direccion.getText().toString());


        try {
            db.update(Transacciones.TbPersonas, valores, "nombre = '"+ nombre.getText().toString() +"'", null);
            Toast.makeText(getApplicationContext(),"Persona Actualizada Correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Activity_Lista.class);
            startActivity(intent);
        } catch (Exception ex) {
            ex.toString();
        } finally {
            db.close();
        }

    }

    public void Eliminar() {


        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();


        try {
            db.delete(Transacciones.TbPersonas, "nombre = '" + nombre.getText().toString() + "'", null);
            Toast.makeText(getApplicationContext(), "Persona Borrada Correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Activity_Lista.class);
            startActivity(intent);

        } catch (Exception ex) {
            ex.toString();
        } finally {
            db.close();
        }
    }

    private boolean Validar(){

        Boolean SN = false;

        if (nombre.getText().toString().equals("")){

            Toast.makeText(getApplicationContext(), "Ingrese un nombre por favor"
                    , Toast.LENGTH_SHORT).show();

        } else if (apellidos.getText().toString().equals("")){

            Toast.makeText(getApplicationContext(), "Ingrese un apellido por favor"
                    , Toast.LENGTH_SHORT).show();

        } else if (edad.getText().toString().equals("")){

            Toast.makeText(getApplicationContext(), "Ingrese una edad por favor"
                    , Toast.LENGTH_SHORT).show();

        } else if (correo.getText().toString().equals("")){

            Toast.makeText(getApplicationContext(), "Ingrese un correo por favor"
                    , Toast.LENGTH_SHORT).show();

        } else if (direccion.getText().toString().equals("")) {

            Toast.makeText(getApplicationContext(), "Ingrese una direccion por favor"
                    , Toast.LENGTH_SHORT).show();
        }else {

            if (edad.getText().toString().matches("[0-9]*")){
                SN = true;
            }else {
                Toast.makeText(getApplicationContext(), "Ingrese una edad valida"
                        , Toast.LENGTH_SHORT).show();
            }
        }

        return SN;

    }

}