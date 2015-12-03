package server;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by pranavdadlani on 11/27/15.
 */
public class Message implements Serializable{
    String type;
    Object data;
    Message(String type,Object data)
    {
        this.type=type;
        this.data=data;
    }
}
