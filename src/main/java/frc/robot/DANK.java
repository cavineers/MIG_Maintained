package frc.robot;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class DANK extends WebSocketServer{
    private static DANK m_instance;

    public DANK() throws UnknownHostException {
        super(new InetSocketAddress(Constants.DANK.kPORT));
    }
    
    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        Robot.logger.addInfo("DANK", "WS Connected");
    }
    
    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        Robot.logger.addInfo("DANK", "WS Disconnect");
    }
    
    @Override
    public void onMessage(WebSocket conn, String message) {
        Robot.logger.addInfo("DANK", "WS "+message);
    }
    
    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        Robot.logger.addInfo("DANK", "WS "+message);
    }

    public static DANK getInstance() {
        if (m_instance == null) {
            try {
                m_instance = new DANK();
                m_instance.start();
            } catch (UnknownHostException e) {
                Robot.logger.addInfo("DANK", "Error starting WS");
            }
        }

        return m_instance;
    }
    
    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
            Robot.logger.addInfo("DANK", "Error creating connection");
        } else {
            Robot.logger.addInfo("DANK", "Error occurred");
        }
    }
    
    @Override
    public void onStart() {
        Robot.logger.addInfo("DANK", "WS Server started");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(100);
    }
}