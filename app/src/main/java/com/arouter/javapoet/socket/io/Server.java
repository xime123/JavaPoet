package com.arouter.javapoet.socket.io;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    final static int PORT = 8000;

    public static void main(String args[]) {
        ServerSocket server = null;
        HandlerExcutorPool pool=null;
        try {
            server = new ServerSocket(PORT);
            System.out.print("server start...");
            pool=new HandlerExcutorPool(50,1000);
            while (true){
                //进行阻塞
                Socket socket = server.accept();
                pool.execute(new ServerHandler(socket));
            }
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
