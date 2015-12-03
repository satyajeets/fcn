package server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Client extends Thread {
    
    ServerSocket receiver;
    int port;
    String name;
    
    Client(String name, int port) {
        this.name = name;
        this.port = port;
        try {
            receiver = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void run() {
        ObjectInputStream ois;
        Socket s;
        Message m;
        ChatMessage cm;
        try {
            while ( true ) {                
                s = receiver.accept();                
                ois = new ObjectInputStream(s.getInputStream());
                m = (Message) ois.readObject();
                cm = (ChatMessage) m.data;
                System.out.println(cm.name + " : " + cm.message);
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
    
    void startClient() {
        Socket s;
        Message m;
        ChatMessage cm;
        ObjectOutputStream oos;
        ObjectInputStream ois;
        JoinPacket jp;
        try {
            //join the chat server
            jp = new JoinPacket(name, port);
            m = new Message("join", jp);
            s = new Socket(InetAddress.getByName("localhost"),5000);
            oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(m);
            
            while ( true ) {
                Thread.sleep(3000);
                cm = new ChatMessage(name, "Msg from " + name);
                m = new Message("chat", cm);
                s = new Socket(InetAddress.getByName("localhost"),5000);
                oos = new ObjectOutputStream(s.getOutputStream());
                oos.writeObject(m);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String args[]) {
        Client c1 = new Client(args[0], Integer.parseInt(args[1]));
        c1.start();
        c1.startClient();
    }
}
