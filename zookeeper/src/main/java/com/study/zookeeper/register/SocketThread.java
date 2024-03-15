package com.study.zookeeper.register;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author 胡代伟
 * @description 服务的提供方
 * @date 2024-03-14 19:06
 */
public class SocketThread extends Thread {
    private Socket socket = null;
    public SocketThread(Socket socket){
        this.socket = socket;
    }
    @SneakyThrows
    @Override
    public void run(){
        // 获取客户端数据, 解析数据
        InputStream inputStream = socket.getInputStream();
        // 获取InetAddress
        InetAddress inetAddress = socket.getInetAddress();
        // 缓冲区
        byte[] bytes = new byte[1024];
        // 读取信息
        int lenth = inputStream.read(bytes);
        //测试: 输出消息
        System.out.println("ip:"+ inetAddress.getHostAddress()+"::"+new String(bytes,0,lenth));
        //给客户端反馈
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("done".getBytes());
        socket.close();
    }
}
