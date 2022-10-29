package com.example.personas.config.tablas;

public class Transacciones {
    //Nombre de la base de datos
    public static final String NameDatabase = "PM01DB";

    /* Creacion de las tablas de la BD */
    public static final String TbPersonas = "personas";

    /* Campos de la tabla personas */
    public static final String id = "id";
    public static final String nombre = "nombre";
    public static final String apellido = "apellido";
    public static final String edad= "edad";
    public static final String correo = "correo";
    public static final String direccion = "direccion";

    // DDL
    public static final String CTPersonas = "CREATE TABLE personas (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " nombre TEXT, apellido TEXT, edad INTEGER, correo TEXT, direccion TEXT)";

    public static final String GetPersonas = "SELECT * FROm " + Transacciones.TbPersonas;

    public static final String DropTPersona = "DROP TABLE IF EXISTS personas";
}
