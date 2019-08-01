package com.arouter.javapoet.netty.udp.core;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class UDPChannelInitializer extends ChannelInitializer<NioDatagramChannel> {
    private UDPChanelInBoundHandler inBoundHandler;
    private IdleStateHandler idleStateHandler;

    public UDPChannelInitializer(UDPChanelInBoundHandler inBoundHandler) {
        this.inBoundHandler = inBoundHandler;
    }

    public void setIdleStateHandler(IdleStateHandler idleStateHandler) {
        this.idleStateHandler = idleStateHandler;
    }

    @Override
    protected void initChannel(NioDatagramChannel datagramChannel) throws Exception {
        ChannelPipeline pipeline = datagramChannel.pipeline();
        if (idleStateHandler != null) {
            pipeline.addLast(this.idleStateHandler);
        } else {
            pipeline.addLast(new IdleStateHandler(IUDPConfig.DEFAULT_READERIDLETIMESECONDS, IUDPConfig.DEFAULT_WRITERIDLETIMESECONDS, IUDPConfig.DEFAULT_ALLIDLETIMESECONDS));
        }
        pipeline.addLast(inBoundHandler);
    }
}
