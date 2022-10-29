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

public class MainActivity extends AppCompatActivity {

    Button Salvar, Lista;
    EditText Nombre, Apellido, Edad, Correo, Direccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Salvar = (Button) findViewById(R.id.MA_BtnGuardar);
        Lista = (Button) findViewById(R.id.MA_BtnPersonas);
        Edad = (EditText) findViewById(R.id.MA_TxtEdad);
        Correo = (EditText) findViewById(R.id.MA_TxtCorreo);
        Nombre = (EditText) findViewById(R.id.MA_TxtNombre);
        Apellido = (EditText) findViewById(R.id.MA_TxtApellidos);
        Direccion = (EditText) findViewById(R.id.MA_TxtDireccion);

        Salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Validar() == true){

                    AgregarPersona();

                }

            }
        });

        Lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Activity_Lista.class);
                startActivity(intent);
            }
        });

    }

    private void AgregarPersona() {

        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombre, Nombre.getText().toString());
        valores.put(Transacciones.apellido, Apellido.getText().toString());
        valores.put(Transacciones.edad, Integer.parseInt(Edad.getText().toString()));
        valores.put(Transacciones.correo, Correo.getText().toString());
        valores.put(Transacciones.direccion, Direccion.getText().toString());

        Long resultado  = db.insert(Transacciones.TbPersonas, Transacciones.id, valores);

        Toast.makeText(getApplicationContext(), "Persona Guardada"
                , Toast.LENGTH_SHORT).show();

        db.close();

        Limpiar();

    }

    private void Limpiar() {

        Nombre.setText("");
        Apellido.setText("");
        Direccion.setText("");
        Correo.setText("");
        Edad.setText("");

    }

    private boolean Validar(){

        Boolean SN = false;

        if (Nombre.getText().toString().equals("")){

            Toast.makeText(getApplicationContext(), "Ingrese un nombre por favor"
                    , Toast.LENGTH_SHORT).show();

        } else if (Apellido.getText().toString().equals("")){

            Toast.makeText(getApplicationContext(), "Ingrese un apellido por favor"
                    , Toast.LENGTH_SHORT).show();

        } else if (Edad.getText().toString().equals("")){

            Toast.makeText(getApplicationContext(), "Ingrese una edad por favor"
                    , Toast.LENGTH_SHORT).show();

        } else if (Correo.getText().toString().equals("")){

            Toast.makeText(getApplicationContext(), "Ingrese un correo por favor"
                    , Toast.LENGTH_SHORT).show();

        } else if (Direccion.getText().toString().equals("")) {

            Toast.makeText(getApplicationContext(), "Ingrese una direccion por favor"
                    , Toast.LENGTH_SHORT).show();
        }else {

            if (Edad.getText().toString().matches("[0-9]*")){
                SN = true;
            }else {
                Toast.makeText(getApplicationContext(), "Ingrese una edad valida"
                        , Toast.LENGTH_SHORT).show();
            }
        }

        return SN;

    }

}