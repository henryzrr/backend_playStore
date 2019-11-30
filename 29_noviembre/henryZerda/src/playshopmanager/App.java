package playshopmanager;

public class App {
    private String nombre;
    private String version;
    private String activa;


    public App(String nombre, String version, String activa) {
        this.nombre = nombre;
        this.version = version;
        this.activa = activa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getActiva() {
        return activa;
    }

    public void setActiva(String activa) {
        this.activa = activa;
    }
}
