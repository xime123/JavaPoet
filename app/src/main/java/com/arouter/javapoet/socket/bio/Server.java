package com.arouter.javapoet.socket.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    final static int PORT = 8000;

    public static void main(String args[]) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT);
            System.out.print("server start...");
            //进行阻塞
            Socket socket = server.accept();
            new Thread(new ServerHandler(socket)).start();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server!=null){
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
