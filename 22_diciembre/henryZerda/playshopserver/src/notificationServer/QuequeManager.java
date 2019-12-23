package notificationServer;

import com.google.gson.Gson;
import playshopServer.App;
import playshopServer.Response;

import java.util.*;

public class QuequeManager {
    private Map<Integer, Queue<App>> messages;
    private Map<Integer, Set<String>> appsPerIUSer;
    private List<Integer> users;

    public QuequeManager(Map<Integer, Queue<App>> messages,Map<Integer, Set<String>> appsPerIUSer, List<Integer> users) {
        this.messages = messages;
        this.appsPerIUSer = appsPerIUSer;
        this.users = users;
    }



    public void enqueueNotification(App app){
        Set<String> apps;
        Queue <App> queue = null;
        for (Integer idUser: users
             ) {
            apps = appsPerIUSer.get(idUser);
            if(apps.contains(app.getName()))
                queue = messages.get(idUser);
                queue.add(app);
                messages.put(idUser,queue);
        }
    }

    public String dequeueNotificacion(int user){
        Queue <App> queue = messages.get(user);
        List <App> apps = new LinkedList<>();
        if(!queue.isEmpty())
            apps.add(queue.poll());
        Response response = new Response(200,apps);
        return new Gson().toJson(response);
    }

}
