package server;
import java.io.Serializable;

/**
 * Created by pranavdadlani on 11/27/15.
 */
public class ChatMessage implements Serializable {
    String name;
    String message;

    ChatMessage(String name, String message)
    {
        this.name = name;
        this.message=message;
    }
}
