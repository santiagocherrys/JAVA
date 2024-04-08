package entity;

public class Vuelo {
    private int id_vuelo;
    private String destino;
    private String fecha_salida;
    private String hora_salida;
    private int id_avion;
    private Avion avion;

    //Constructores

    public Vuelo(){
        this.avion = new Avion();
    }

    public Vuelo(int id_vuelo, String destino, String fecha_salida, String hora_salida, int id_avion) {
        this.id_vuelo = id_vuelo;
        this.destino = destino;
        this.fecha_salida = fecha_salida;
        this.hora_salida = hora_salida;
        this.id_avion = id_avion;
    }

    //setter and getters

    public int getId_vuelo() {
        return id_vuelo;
    }

    public void setId_vuelo(int id_vuelo) {
        this.id_vuelo = id_vuelo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public int getId_avion() {
        return id_avion;
    }

    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }

    public String getAvion_modelo() {

        return this.avion.getModelo();
    }

    public void setAvion_setModelo(String modelo) {

        this.avion.setModelo(modelo);
    }

    public int getAvion_capacidad(){
        return this.avion.getCapacidad();
    }

    public void setAvion_capacidad(int capacidad){
        this.avion.setCapacidad(capacidad);
    }

    //ToString


    @Override
    public String toString() {
        return "Vuelo{" +
                "id_vuelo=" + id_vuelo +
                ", destino='" + destino + '\'' +
                ", fecha_salida='" + fecha_salida + '\'' +
                ", hora_salida='" + hora_salida + '\'' +
                '}';
    }

    public String imprimirTodo(){
        return "Vuelo{" +
                "id_vuelo=" + id_vuelo +
                ", destino='" + destino + '\'' +
                ", fecha_salida='" + fecha_salida + '\'' +
                ", hora_salida='" + hora_salida + '\'' +
                ", Modelo avion= " + this.avion.getModelo()+
                ", Capacidad Avion= " + this.avion.getCapacidad()+
                '}';
    }
}
