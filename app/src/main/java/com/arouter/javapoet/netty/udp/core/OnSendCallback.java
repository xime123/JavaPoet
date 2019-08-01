package com.arouter.javapoet.netty.udp.core;

public interface OnSendCallback {
    /**
     * upd发送成功，非业务成功
     */
    void onSuccess();

    /**
     * upd发送失败，非业务失败
     */
    void onFailed(String errorMsg);
}
