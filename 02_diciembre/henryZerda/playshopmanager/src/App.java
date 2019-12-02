public class App {
    private String nombre;
    private String version;
    private Estado estado;

    public App(String nombre, String version, Estado estado) {
        this.nombre = nombre;
        this.version = version;
        this.estado=estado;
    }
    public App(String app){
        toApp(app);
    }
    public String getNombre() {
        return nombre;
    }

    public String getVersion() {
        return version;
    }

    public String getEstado() {
        return estado.toString();
    }

    public String toJson(){
        return "{" +
                "\"nombre\":\"" +nombre+"\","+
                "\"version\":\"" +version+"\","+
                "\"estado\":\""+estado.toString()+"\""+
                "}";
    }
    public void toApp(String app){
        int indexNombre = app.indexOf("nombre")+9;
        int indexVersion=app.indexOf("version")+10;
        int indexEstado= app.indexOf("estado")+9;

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
        word="";
        while (app.charAt(indexEstado)!='"'){
            word+=app.charAt(indexEstado++);
        }
        estado=word.equals("activa")?Estado.activa:Estado.inactiva;
    }
}