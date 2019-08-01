package com.arouter.javapoet.netty.udp;

import com.arouter.javapoet.netty.udp.core.UDPClient;

public class UDPClientManager {
    private UDPClient mUdpClient;

    private UDPClientManager() {
        if (SingletonHolder.manager != null) {
            throw new IllegalStateException("manager  is not null");
        }
    }

    private static class SingletonHolder {
        private static volatile UDPClientManager manager = new UDPClientManager();
    }

    public static UDPClientManager getInstance() {
        return SingletonHolder.manager;
    }

    public void init() {
        mUdpClient = new UDPClient();
    }


}
