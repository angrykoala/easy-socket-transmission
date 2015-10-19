//TITLE:SocketMessage
//AUTHOR:demiurosoft
//DESCRIPTIION: A simple class that implements a Socket-based serialized object transmission
//extending this class will allow to send and receive the child object easily

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class EasySocketTransmission implements Serializable {
    public static final int defaultPort=4242;
    public static final String defaultHost="localhost";

    public void send(String host,int port) throws IOException {
        if(checkHost(host)==false) host=defaultHost;
        if(checkPort(port)==false) port=defaultPort;
        Socket socket=new Socket(host,port);
        java.io.OutputStream outputStream =socket.getOutputStream();
        ObjectOutputStream objout=new ObjectOutputStream(outputStream);
        objout.writeObject(this);
        socket.close();
    }
    public void send(String host) throws IOException {
        send(host,defaultPort);
    }
    public void send(int port) throws IOException {
        send(defaultHost,port);
    }
    public void send() throws IOException {
        send(defaultHost,defaultPort);
    }
    public static EasySocketTransmission receive(int port,int timeout) throws IOException, ClassNotFoundException {
        if(checkPort(port)==false) port=defaultPort;
        ServerSocket serverSocket=new ServerSocket(port);
        if(timeout>0)  serverSocket.setSoTimeout(timeout);
        Socket clientSocket=serverSocket.accept();
        ObjectInputStream input=new ObjectInputStream(clientSocket.getInputStream());
        EasySocketTransmission message=(EasySocketTransmission) input.readObject();
        clientSocket.close();
        serverSocket.close();
        return message;
    }
    public static EasySocketTransmission receive(int port) throws ClassNotFoundException, IOException {
        return receive(port,0);
    }
    public static EasySocketTransmission receive() throws ClassNotFoundException, IOException {
        return receive(defaultPort,0);
    }

    private static boolean checkPort(int port) {
        if(port<=0 || port>=65535) return false;
        else return true;
    }
    private static boolean checkHost(String host) {
        if(host==null || host.isEmpty()) return false;
        else return true;
    }
}
