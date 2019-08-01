package com.arouter.javapoet.netty.udp.core;


public class UDPConfig implements IUDPConfig {
    public static Builder newBuilder() {
        return new Builder();
    }

    private String ip;//广播Ip
    private int port;//目的广播端口号
    private int readerIdleTimeSeconds;
    private int writerIdleTimeSeconds;
    private int allIdleTimeSeconds;
    private int so_rcvbuf;
    private int so_sndbuf;
    private int bindPort;

    private UDPConfig(Builder builder) {
        this.bindPort = builder.bindPort;
        this.ip = builder.ip;
        this.port = builder.port;
        this.readerIdleTimeSeconds = builder.readerIdleTimeSeconds;
        this.allIdleTimeSeconds = builder.allIdleTimeSeconds;
        this.allIdleTimeSeconds = builder.allIdleTimeSeconds;
        this.so_rcvbuf = builder.so_rcvbuf;
        this.so_sndbuf = builder.so_sndbuf;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getReaderIdleTimeSeconds() {
        return readerIdleTimeSeconds;
    }

    public void setReaderIdleTimeSeconds(int readerIdleTimeSeconds) {
        this.readerIdleTimeSeconds = readerIdleTimeSeconds;
    }

    public int getWriterIdleTimeSeconds() {
        return writerIdleTimeSeconds;
    }

    public void setWriterIdleTimeSeconds(int writerIdleTimeSeconds) {
        this.writerIdleTimeSeconds = writerIdleTimeSeconds;
    }

    public int getAllIdleTimeSeconds() {
        return allIdleTimeSeconds;
    }

    public void setAllIdleTimeSeconds(int allIdleTimeSeconds) {
        this.allIdleTimeSeconds = allIdleTimeSeconds;
    }

    public int getSo_rcvbuf() {
        return so_rcvbuf;
    }

    public void setSo_rcvbuf(int so_rcvbuf) {
        this.so_rcvbuf = so_rcvbuf;
    }

    public int getSo_sndbuf() {
        return so_sndbuf;
    }

    public void setSo_sndbuf(int so_sndbuf) {
        this.so_sndbuf = so_sndbuf;
    }

    public int getBindPort() {
        return bindPort;
    }

    public void setBindPort(int bindPort) {
        this.bindPort = bindPort;
    }

    public final static class Builder {
        private String ip;//广播Ip
        private int port;//目的广播端口号
        private int readerIdleTimeSeconds = DEFAULT_READERIDLETIMESECONDS;
        private int writerIdleTimeSeconds = DEFAULT_WRITERIDLETIMESECONDS;
        private int allIdleTimeSeconds = DEFAULT_ALLIDLETIMESECONDS;
        private int so_rcvbuf;
        private int so_sndbuf;
        private int bindPort = DEFAULT_INETPORT;

        private Builder() {
        }

        public Builder ip(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder readerIdleTimeSeconds(int readerIdleTimeSeconds) {
            this.readerIdleTimeSeconds = readerIdleTimeSeconds;
            return this;
        }

        public Builder writerIdleTimeSeconds(int writerIdleTimeSeconds) {
            this.writerIdleTimeSeconds = writerIdleTimeSeconds;
            return this;
        }

        public Builder allIdleTimeSeconds(int allIdleTimeSeconds) {
            this.allIdleTimeSeconds = allIdleTimeSeconds;
            return this;
        }

        public Builder so_rcvbuf(int so_rcvbuf) {
            this.so_rcvbuf = so_rcvbuf;
            return this;
        }

        public Builder so_sndbuf(int so_sndbuf) {
            this.so_sndbuf = so_sndbuf;
            return this;
        }

        public UDPConfig build() {
            return new UDPConfig(this);
        }
    }
}
