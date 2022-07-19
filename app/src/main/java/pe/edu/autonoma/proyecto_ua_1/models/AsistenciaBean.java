package pe.edu.autonoma.proyecto_ua_1.models;

public class AsistenciaBean {

    /**
     * create table " + NOMBRE_TABLA2 +
     *                     "(id integer primary key autoincrement," +
     *                     "id_docente integer not null," +
     *                     "fecha_asistencia text not null, " +
     *                     "foreign key(id_docente) references " + NOMBRE_TABLA1 + "(id));
     */

    private int id;
    private int idDocente;
    private String fechaAsistencia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public String getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(String fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }
}
