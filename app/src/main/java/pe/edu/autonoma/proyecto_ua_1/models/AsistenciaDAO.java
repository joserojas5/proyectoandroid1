package pe.edu.autonoma.proyecto_ua_1.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import pe.edu.autonoma.proyecto_ua_1.conexion.MySQLiteHelper;

public class AsistenciaDAO {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    public AsistenciaDAO(Context context){
        dbHelper = new MySQLiteHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public long insertar(AsistenciaBean objAsistenciaBean){
        long estado = 0;
        try{
            ContentValues values = new ContentValues();
            values.put("id_docente", objAsistenciaBean.getIdDocente());
            values.put("fecha_asistencia", objAsistenciaBean.getFechaAsistencia());
            estado = database.insert(MySQLiteHelper.NOMBRE_TABLA2, null, values);
        }catch (Exception e){
            estado = 0;
        }
        return  estado;
    }
    public ArrayList<AsistenciaBean> listadoAsistencia(){
        Cursor c;
        ArrayList<AsistenciaBean> list = new ArrayList<AsistenciaBean>();
        c = database.rawQuery("SELECT * FROM asistencia ", null);
        while (c.moveToNext()){
            AsistenciaBean objAsistenciaBean = new AsistenciaBean();
            objAsistenciaBean.setId(c.getInt(0));
            objAsistenciaBean.setIdDocente(c.getInt(1));
            objAsistenciaBean.setFechaAsistencia(c.getString(2));
            list.add(objAsistenciaBean);
        }
        c.close();
        return list;
    }

    public long eliminarRegistro(int codigo){
        long estado = 0;

        try {
            estado = database.delete(MySQLiteHelper.NOMBRE_TABLA2, "id = ?",
                    new String[]{String.valueOf(codigo)});
        }catch (Exception e){
            estado = 0;
        }
        return estado;
    }
    public long modificarRegistro (String id, String idDocente, String fechaAsistencia){
        long estado = 0;
        try {
            ContentValues values = new ContentValues();
            values.put("id_docente", idDocente);
            values.put("fecha_asistencia", fechaAsistencia);
            estado= database.update(MySQLiteHelper.NOMBRE_TABLA2, values, "codigo=" +
                    id, null);
        }catch (Exception e){
            estado = 0;
        }
        return estado;
    }


}
