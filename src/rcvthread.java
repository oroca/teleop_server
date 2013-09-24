import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;

public class rcvthread implements Runnable {

    private static final int sizeBuf = 50;
    private Socket 			clientSocket;
    private SocketAddress 	clientAddress;
    private int rcvBufSize;
    private byte[] rcvBuf = new byte[sizeBuf];
    
    public rcvthread(Socket clientSocket, SocketAddress clientAddress) {
        this.clientSocket  = clientSocket;
        this.clientAddress = clientAddress;
    }

    public void run() {
	    try {
	        InputStream ins   = clientSocket.getInputStream();
	        OutputStream outs = clientSocket.getOutputStream();

	        while ((rcvBufSize = ins.read(rcvBuf)) != -1) {
	        	String rcvData = new String(rcvBuf, 0, rcvBufSize, "UTF-8");
	        	
	        	if (rcvData.compareTo("Up") == 0)
	            	System.out.println("Go!");
	        	
	            if (rcvData.compareTo("LeftTurn") == 0)
	            	System.out.println("LeftTurn!");   
	            
	            if (rcvData.compareTo("RightTurn") == 0)
	            	System.out.println("RightTurn!");
	            
	            if (rcvData.compareTo("Down") == 0)
	            	System.out.println("Back!");     
	            
	           	if (rcvData.compareTo("Stop") == 0)
	            	System.out.println("Stop!");
	            	
	           	System.out.println("Received data : " + rcvData + " (" + clientAddress + ")");
	            outs.write(rcvBuf, 0, rcvBufSize);
	        }
	        System.out.println(clientSocket.getRemoteSocketAddress() + " Closed");

	    } catch (IOException e) {
	    	System.out.println("Exception: " + e);
	    } finally {
	        try {
	        	clientSocket.close();
	            System.out.println("Disconnected! Client IP : " + clientAddress);
	        } catch (IOException e) {
	        	System.out.println("Exception: " + e);
	        }
	    }
    }
}
