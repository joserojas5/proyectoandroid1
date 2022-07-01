package pe.edu.autonoma.proyecto_ua_1.conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String NOMBRE_TABLA1 = "docente";
    public static final String NOMBRE_TABLA2 = "asistencia";
    private static final String NOMBRE_BASEDATOS = "BDCJAVA.db";
    private static final int VERSION = 1;

    private static final String SQLBD = "create table " + NOMBRE_TABLA1+
            "(id integer primary key autoincrement, " +
            "nombre text not null,"+
            " apellido text not null,"+
            " edad integer not null,"+
            " dni text not null unique,"+
            " sexo text not null,"+
            " correo text not null,"+
            " telefono text not null);";
    private static final String SQLBD2 =
            "create table " + NOMBRE_TABLA2 +
                    "(id integer primary key autoincrement," +
                    "id_docente integer not null," +
                    "fecha_asistencia text not null, " +
                    "foreign key(id_docente) references " + NOMBRE_TABLA1 + "(id));";



    public MySQLiteHelper( Context context) {

        super(context, NOMBRE_BASEDATOS, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQLBD);
        database.execSQL(SQLBD2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int antiguaVersion, int nuevaVersion) {
        Log.w(MySQLiteHelper.class.getName(), "Actualizado la Version de la Base de datos Numero:" +
                antiguaVersion + " a " + nuevaVersion);
        db.execSQL("DROP TABLE IF EXISTS " + NOMBRE_TABLA2);
        db.execSQL("DROP TABLE IF EXISTS " + NOMBRE_TABLA1);
        onCreate(db);
    }
}
