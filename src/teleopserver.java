import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class teleopserver {
	
	private static ServerSocket serverSocket;
	private static int port = 8888;
	
	public static void main(String[] args) {
	    try {
	    	System.out.println("Start Teleop Server : " + InetAddress.getLocalHost() + "(" + port + ")");
			
			serverSocket = new ServerSocket(port);
			    		
			while (true) {
			
				Socket clientSocket = serverSocket.accept();
				
				Thread thread = new Thread(new rcvthread(clientSocket, clientSocket.getRemoteSocketAddress()));
				thread.start();
				
				if(clientSocket.isConnected()) {
					System.out.println("Connected Client IP : " + clientSocket.getRemoteSocketAddress());
				}
			}
	    } catch (IOException e) {
	    	System.out.println("Exception: " + e);
	    } 
	}
}
