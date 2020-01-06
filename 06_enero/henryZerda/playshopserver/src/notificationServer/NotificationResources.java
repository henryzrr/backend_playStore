package notificationServer;

import playshopServer.App;

import java.util.*;

public class NotificationResources {
    private   QuequeManager quequeManager;
    private  DBManager dbManager;
    private Map<Integer, Set<String>> appsPerIUSer;
    private Map<Integer, Queue<App>> messages;
    private List<Integer> users;
    public NotificationResources(){
        messages= new HashMap<>();
        appsPerIUSer = new HashMap<>();

        users = new LinkedList<>();
        users.add(1);
        messages.put(1,new LinkedList<>());
        dbManager = new DBManager();
        quequeManager = new QuequeManager(messages,appsPerIUSer,users);

    }

    public String getNotification(){
        return quequeManager.dequeueNotificacion(1);
    }

    public void putQueueNotification(App app){
        quequeManager.enqueueNotification(app);
    }

}
