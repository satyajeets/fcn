package server;
import java.net.Socket;

/**
 * Class representing a user in the system
 * @author satyajeet
 */
public class Member {
    
    String name, ip;
    Socket socket;
    int port;
    
    Member(String name, String ip, int port) {
        this.name = name;
        this.ip = ip;
        this.port = port;
    }
}
