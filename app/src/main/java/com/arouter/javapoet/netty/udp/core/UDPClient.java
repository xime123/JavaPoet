package com.arouter.javapoet.netty.udp.core;

import android.text.TextUtils;
import android.util.Log;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

public class UDPClient extends UDPChanelInBoundHandler implements Runnable {
    private static final String TAG = UDPClient.class.getSimpleName();
    private Bootstrap mBootStrap;
    private EventLoopGroup mEventLoopGroup;
    private UDPChannelInitializer mInitializer;
    private ExecutorService executorService;
    private UDPConfig udpConfig;
    private volatile boolean hashInited = false;


    public UDPClient(UDPConfig config) {
        if (config == null) {
            init(UDPConfig.newBuilder().build());
        } else {
            init(config);
        }
    }

    public UDPClient() {
        init(UDPConfig.newBuilder().build());
    }

    private void init(UDPConfig config) {
        this.udpConfig = config;
        mBootStrap = new Bootstrap();
        mEventLoopGroup = new NioEventLoopGroup();
        mBootStrap.group(mEventLoopGroup);
        mBootStrap.channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_RCVBUF, config.getSo_rcvbuf())
                .option(ChannelOption.SO_SNDBUF, config.getSo_sndbuf());
        mInitializer = new UDPChannelInitializer(this);
        mInitializer.setIdleStateHandler(new IdleStateHandler(config.getReaderIdleTimeSeconds(), config.getWriterIdleTimeSeconds(), config.getAllIdleTimeSeconds()));
        mBootStrap.handler(mInitializer);
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(this);
    }

    public boolean isHashInited() {
        return this.hashInited;
    }

    @Override
    public void onReceive(String body) {
        Log.i(TAG, "onReceive =====>" + body);
    }

    @Override
    public void run() {
        try {
            ChannelFuture channelFuture = mBootStrap.bind(udpConfig.getBindPort()).sync();
            channelFuture.channel().closeFuture().await();
            this.hashInited = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mEventLoopGroup.shutdownGracefully();
        }
    }

    public void send(String req) {
        if (TextUtils.isEmpty(udpConfig.getIp()) || udpConfig.getPort() == 0) {
            return;
        }
        send(req, udpConfig.getIp(), udpConfig.getPort());
    }

    public void send(String req, String ip, int port) {
        send(new DatagramPacket(Unpooled.copiedBuffer(req, CharsetUtil.UTF_8), new InetSocketAddress("172.26.82.116", 1112)));
        //  send(new DatagramPacket(Unpooled.copiedBuffer(req, CharsetUtil.UTF_8), new InetSocketAddress(ip, port)));
    }

    public boolean close() {
        try {
            return mEventLoopGroup.shutdownGracefully().await(10 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
