public class AppServer {
    private String nombre;
    private String version;
    private Estado estado;

    public AppServer(String nombre, String version, Estado estado) {
        this.nombre = nombre;
        this.version = version;
        this.estado = estado;
    }

}
