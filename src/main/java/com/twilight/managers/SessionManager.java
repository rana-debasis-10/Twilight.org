package com.twilight.managers;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import tools.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    private final ConcurrentHashMap<String, WebSocketSession> sessions =
            new ConcurrentHashMap<>();


    public void add(String key, WebSocketSession session) {

        sessions.put(key, session);

    }

    public void remove(String key) {

        sessions.remove(key);

    }

    public WebSocketSession get(String key) {

        return sessions.get(key);

    }

    public boolean isOnline(String key) {

        return sessions.containsKey(key);

    }
    public void send(String key, Object payload) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        if(!isOnline(key)){
            return;
        }
        WebSocketSession webSocketSession = get(key);
        webSocketSession.sendMessage(new TextMessage(mapper.writeValueAsString(payload)));
    }

}
