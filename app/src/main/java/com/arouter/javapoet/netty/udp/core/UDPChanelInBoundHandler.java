package com.arouter.javapoet.netty.udp.core;

import android.util.Log;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public abstract class UDPChanelInBoundHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    private ChannelHandlerContext mHandlerContext;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Log.e("UDPChanelInBoundHandler", "channelActive");
        super.channelActive(ctx);
        mHandlerContext = ctx;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Log.e("UDPChanelInBoundHandler", "channelInactive");
        super.channelInactive(ctx);
        mHandlerContext.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        Log.e("UDPChanelInBoundHandler", "channelReadComplete");
        super.channelReadComplete(ctx);
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        Log.e("UDPChanelInBoundHandler", "channelRead0");
        String body = datagramPacket.content().toString(CharsetUtil.UTF_8);
        onReceive(body);
    }

    public void send(Object o) {
        send(o, null);
    }

    public void send(Object o, OnSendCallback callback) {
        if (mHandlerContext == null) {
            Log.e("UDPChanelInBoundHandler", "mHandlerContext is null");
            return;
        }
        mHandlerContext.writeAndFlush(o).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                Log.i("UDPChanelInBoundHandler", "operationComplete " + future.isSuccess());
                if (callback != null) {
                    if (future.isSuccess()) {
                        callback.onSuccess();
                    } else {
                        callback.onFailed(future.cause().getCause().toString());
                    }
                }
            }
        });
    }

    public abstract void onReceive(String body);


}
