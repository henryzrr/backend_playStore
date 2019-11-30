package playshopserver;

public class App {
    private String nombre;
    private String version;
    private String activo;

    public App(String nombre, String version, String activo) {
        this.nombre = nombre;
        this.version = version;
        this.activo = activo;
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

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
}
