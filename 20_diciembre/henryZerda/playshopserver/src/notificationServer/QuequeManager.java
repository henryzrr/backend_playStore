package notificationServer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class QuequeManager {
    private Map<Integer, Queue<String>> messages;

    public QuequeManager(DBManager dbManager) {
        messages = new HashMap<>();
        fillSuscribredUsers(dbManager);
    }

    private void fillSuscribredUsers(DBManager dbManager) {
        for (Integer user:dbManager.getSuscribedUsers()
             ) {
            messages.put(user,new LinkedList<>());
        }
    }

    public Response enqueueNotification(String message,int user){
        Queue <String> queue = messages.get(user);
        queue.add(message);
        messages.put(user,queue);
        return new Response(200,"");
    }

    public Response dequeueNotificacion(int user){
        return new Response(200,messages.get(user).poll());
    }

}
