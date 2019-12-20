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
        dbManager = new DBManager();
        fillSuscribredUsers();
        quequeManager = new QuequeManager(messages,appsPerIUSer,users);

    }

    public String getNotification(){
        return quequeManager.dequeueNotificacion(1);
    }

    public void putQueueNotification(App app){
        quequeManager.enqueueNotification(app);
    }
    private void fillSuscribredUsers() {
        for (Integer user:dbManager.getSuscribedUsers()
        ) {
            messages.put(user,new LinkedList<>());
            users.add(user);
            Set<String> set = new HashSet();
            appsPerIUSer.put(user,set);
        }
    }
}
