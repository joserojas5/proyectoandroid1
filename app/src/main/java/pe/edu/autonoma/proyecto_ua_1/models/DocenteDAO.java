package pe.edu.autonoma.proyecto_ua_1.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import pe.edu.autonoma.proyecto_ua_1.conexion.MySQLiteHelper;

public class DocenteDAO {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    public DocenteDAO(Context context){
        dbHelper = new MySQLiteHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }

    public long insertar(DocenteBean objDocenteBean){
        long estado = 0;
        try{
            ContentValues values = new ContentValues();
            values.put("nombre", objDocenteBean.getNombre());
            values.put("apellido", objDocenteBean.getApellido());
            values.put("edad", objDocenteBean.getEdad());
            values.put("dni", objDocenteBean.getDni());
            values.put("sexo", objDocenteBean.getSexo());
            values.put("correo", objDocenteBean.getCorreo());
            values.put("telefono", objDocenteBean.getTelefono());
            estado = database.insert(MySQLiteHelper.NOMBRE_TABLA1, null, values);
        }catch (Exception e){
            estado = 0;
        }
        return  estado;
    }

    public long eliminarRegistro(int codigo){
        long estado = 0;

        try {
            estado = database.delete(MySQLiteHelper.NOMBRE_TABLA1, "id = ?",
                    new String[]{String.valueOf(codigo)});
        }catch (Exception e){
            estado = 0;
        }
        return estado;
    }

    public long modificarRegistro (String id, String nombre, String apellido, String edad,
                                   String dni, String sexo, String correo, String telefono){
        long estado = 0;
        try {
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("apellido", apellido);
            values.put("edad", edad);
            values.put("dni", dni);
            values.put("sexo", sexo);
            values.put("correo", correo);
            values.put("telefono", telefono);
            estado= database.update(MySQLiteHelper.NOMBRE_TABLA1, values, "codigo=" +
                    id, null);
        }catch (Exception e){
            estado = 0;
        }
        return estado;
    }
    public ArrayList<DocenteBean> listadoPersonas(){
        Cursor c;
        ArrayList<DocenteBean> list = new ArrayList<DocenteBean>();
        c = database.rawQuery("SELECT * FROM docente ", null);
        while (c.moveToNext()){
            DocenteBean objDocenteBean = new DocenteBean();
            objDocenteBean.setId(c.getInt(0));
            objDocenteBean.setNombre(c.getString(1));
            objDocenteBean.setApellido(c.getString(2));
            objDocenteBean.setEdad(c.getInt(3));
            objDocenteBean.setDni(c.getString(4));
            objDocenteBean.setSexo(c.getString(5));
            objDocenteBean.setCorreo(c.getString(6));
            objDocenteBean.setTelefono(c.getString(7));
            list.add(objDocenteBean);
        }
        c.close();
        return list;
    }


}
