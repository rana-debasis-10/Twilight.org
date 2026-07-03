package com.twilight.managers;


import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {

    private final ConcurrentHashMap<String, WebSocketSession> sessions =
            new ConcurrentHashMap<>();


    public void add(String driverMobNo, WebSocketSession session) {

        sessions.put(driverMobNo, session);

    }

    public void remove(String driverMobNo) {

        sessions.remove(driverMobNo);

    }

    public WebSocketSession get(String driverMobNo) {

        return sessions.get(driverMobNo);

    }

    public boolean isOnline(String driverMobNo) {

        return sessions.containsKey(driverMobNo);

    }
    public void send(String driverMobNo, Object payload) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        if(!isOnline(driverMobNo)){
            return;
        }
        WebSocketSession webSocketSession = get(driverMobNo);
        webSocketSession.sendMessage(new TextMessage(mapper.writeValueAsString(payload)));
    }

}
