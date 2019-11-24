package playshopserver;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PlainTextDBManager {
    private File db;
    PlainTextDBManager(String DB_LOCATION) throws FileNotFoundException{
        db = new File(DB_LOCATION);
        if(!db.exists()){
            new FileNotFoundException("El archivo "+DB_LOCATION+" no es válido");
        }
    }

    public List<String>selectAll(){
        List<String> lines=new LinkedList<>();
        try {
            Scanner sc = new Scanner(db);
            while (sc.hasNextLine()){
                lines.add(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public List<String>find(String pattern){
        List<String> lines=new LinkedList<>();
        try {
            Scanner sc = new Scanner(db);
            String line;
            while (sc.hasNextLine()){
                line=sc.nextLine();
                if(line.contains(pattern)){
                    lines.add(line);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void insert(String line) throws IOException{
        FileWriter writer = new FileWriter(db,true);
        writer.write(line);
        writer.close();
    }
    public void update(String line, String pattern)throws IOException{
        List<String> lines=new LinkedList<>();
        try {
            Scanner sc = new Scanner(db);
            String auxLine;
            while (sc.hasNextLine()){
                auxLine=sc.nextLine();
                if(auxLine.contains(pattern)){
                    lines.add(line);
                }else{
                    lines.add(auxLine);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(lines.size()!=1){
            throw  new IOException("No existe la linea "+pattern);
        }
        FileWriter writer = new FileWriter(db,false);
        for (String l:lines
             ) {
            writer.write(l);
        }
        writer.close();
    }
    public String findOne(String pattern){
        try {
            String line;
            Scanner sc = new Scanner(db);
            while (sc.hasNextLine()){
                line=sc.nextLine();
                if(line.contains(pattern)){
                    sc.close();
                    return line;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
