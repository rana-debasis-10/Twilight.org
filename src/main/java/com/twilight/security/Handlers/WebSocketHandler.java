package com.twilight.security.Handlers;
import com.twilight.objects.Location;
import com.twilight.dataTransferObjects.WebSocketMessage;
import com.twilight.managers.WebsocketSessionManager;
import com.twilight.services.LocationService;
import com.twilight.types.Role;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
    private final WebsocketSessionManager websocketSessionManager;
    private final LocationService locationService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String mobileNumber = (String)session.getAttributes().get("Mobile Number");
        Role role = Role.valueOf((String)session.getAttributes().get("Role"));
        if( role == Role.manager && session.getAttributes().containsKey("Credential") ){
            websocketSessionManager.add((String)session.getAttributes().get("Credential"), session);
            return;
        }
        websocketSessionManager.add(mobileNumber,session);
    }

    @Override
    protected void handleTextMessage(
            WebSocketSession session,
            @NonNull TextMessage message
    ) throws Exception {

        Role role = Role.valueOf((String)session.getAttributes().get("Role"));
        switch (role){
            case Role.customer:
                handleCustomerMessages(session,message);
                break;
            case Role.manager:
                handleManagerMessages(session,message);
                break;
            case Role.driver:
                handleDriverMessages(session,message);
                break;
            default:
                break;

        }

    }

    @Override
    public void afterConnectionClosed(
            WebSocketSession session,
            @NonNull CloseStatus status
    ) {

        String driverMobNo =
                (String) session.getAttributes().get("driverMobNo");

        websocketSessionManager.remove(driverMobNo);

    }

    public void handleDriverMessages(WebSocketSession session,TextMessage message) {
        String mobNo= (String)session.getAttributes().get("Mobile Number");
        try {
            WebSocketMessage message1 = mapper.convertValue(message.getPayload(), WebSocketMessage.class);
            if(message1.message().equals("location-update")){
                locationService.updateLocation(mobNo,mapper.convertValue(message1.payload(), Location.class));
            };
        } catch (IllegalArgumentException ignored) {
        }

    }
    public void handleCustomerMessages(WebSocketSession session,TextMessage message) {
    }
    public void handleManagerMessages(WebSocketSession session,TextMessage message) {
    }

}
