import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/push")
public class PushNotification {
	static Set<Session> userSession = Collections.synchronizedSet(new HashSet<Session>());
	@OnOpen
	public void open(Session ses) throws IOException {
		userSession.add(ses);
		System.out.println("CONNECTED");

	}

	@OnMessage
	public void onMessage( String msg,  Session ses) throws Exception {

		String username =(String) ses.getUserProperties().get("username");

		if(null ==username)
			ses.getUserProperties().put("username", username);	

		ses.getAsyncRemote().sendText("Hello,"+ msg);
	}
	@OnClose
	public void close(Session ses) throws IOException{
		System.out.println("Disconnected");

	}

}
