package entity;

public class Reservacion {
    private int id_reservacion;
    private int id_pasajero;
    private int id_vuelo;
    private String fecha_reservacion;
    private String asiento;
    private Pasajero pasajero;
    private Vuelo vuelo;

    //Constructores

    public Reservacion(){
        this.pasajero = new Pasajero();
        this.vuelo = new Vuelo();
    }

    public Reservacion(int id_reservacion, int id_pasajero, int id_vuelo, String fecha_reservacion, String asiento) {
        this.id_reservacion = id_reservacion;
        this.id_pasajero = id_pasajero;
        this.id_vuelo = id_vuelo;
        this.fecha_reservacion = fecha_reservacion;
        this.asiento = asiento;
    }

    //Setters and Getters

    public int getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(int id_reservacion) {
        this.id_reservacion = id_reservacion;
    }

    public int getId_pasajero() {
        return id_pasajero;
    }

    public void setId_pasajero(int id_pasajero) {
        this.id_pasajero = id_pasajero;
    }

    public int getId_vuelo() {
        return id_vuelo;
    }

    public void setId_vuelo(int id_vuelo) {
        this.id_vuelo = id_vuelo;
    }

    public String getFecha_reservacion() {
        return fecha_reservacion;
    }

    public void setFecha_reservacion(String fecha_reservacion) {
        this.fecha_reservacion = fecha_reservacion;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public void setPasajero_nombre(String nombre){
        this.pasajero.setNombre(nombre);
    }

    public String getPasajero_nombre(){
        return this.pasajero.getNombre();
    }

    public void setPasajero_apellido(String apellido){
        this.pasajero.setApellido(apellido);
    }

    public String getPasajero_apellido(){
        return this.pasajero.getApellido();
    }

    public void setPasajero_documento_identidad(String documento){
        this.pasajero.setDocumento_identidad(documento);
    }

    public String getPasajero_documento_identidad(){
        return this.pasajero.getDocumento_identidad();
    }

    public void setVuelo_destino(String destino){
        this.vuelo.setDestino(destino);
    }

    public String getVuelo_destino(){
        return this.vuelo.getDestino();
    }

    public void setVuelo_fecha_salida(String fecha_salida){
        this.vuelo.setFecha_salida(fecha_salida);
    }

    public String getVuelo_fecha_salida(){
        return this.vuelo.getFecha_salida();
    }

    public void setVuelo_hora_salida(String hora_salida){
        this.vuelo.setHora_salida(hora_salida);
    }

    public String getVuelo_hora_salida(){
        return this.vuelo.getHora_salida();
    }

    //toString

    @Override
    public String toString() {
        return "Reservacion {" +
                "id_reservacion=" + id_reservacion +
                ", id_pasajero=" + id_pasajero +
                ", id_vuelo=" + id_vuelo +
                ", fecha_reservacion='" + fecha_reservacion +
                ", asiento='" + asiento +
                '}';
    }
    public String imprimirTodo(){
        return "Reservacion{" +
                "id_reservacion=" + id_reservacion +
                ", pasajero nombre=" + this.getPasajero_nombre() +
                ", apellidos=" + this.getPasajero_apellido() +
                ", destino=" + this.getVuelo_destino() +
                ", fecha salida= "+ this.getVuelo_fecha_salida() +
                ", hora_salida= " + this.getVuelo_hora_salida() +
                ", fecha_reservacion='" + fecha_reservacion  +
                ", asiento='" + asiento +
                '}';
    }
}


