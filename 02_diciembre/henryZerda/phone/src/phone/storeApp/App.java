package phone.storeApp;

public class App {
    private String nombre;
    private String version;

    public App(String app){
        toApp(app);
    }
    public App(String nombre, String version){
        this.nombre=nombre;
        this.version=version;
    }

    public String getNombre() {
        return nombre;
    }

    public String getVersion() {
        return version;
    }

    public void toApp(String app){
        int indexNombre = app.indexOf("nombre")+9;
        int indexVersion=app.indexOf("version")+10;

        char c='a';
        String word="";

        while (app.charAt(indexNombre)!='"'){
            word+=app.charAt(indexNombre++);
        }
        nombre=word;
        word="";
        while (app.charAt(indexVersion)!='"'){
            word+=app.charAt(indexVersion++);
        }
        version=word;
    }

}
