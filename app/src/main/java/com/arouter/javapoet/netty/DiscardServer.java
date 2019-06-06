package com.arouter.javapoet.netty;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public class DiscardServer {
    public static void main(String args[]){
        EventLoopGroup boss=new NioEventLoopGroup();
    }
}
