package com.study.zookeeper.register;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author 胡代伟
 * @description 远程调用服务提供方
 * @date 2024-03-14 19:09
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        int port=9527;
        // 1.创建绑定到特定端口的服务器套接字。
        ServerSocket ss = new ServerSocket(port);
        // 将服务器信息注册到zk
        register(port);

        // 2.监听客户端的请求,获取客户端的Socket对象
        // 侦听并接受到此套接字的连接。
        while(true){
            // 阻塞
            Socket socket = ss.accept();
            new SocketThread(socket).start();
        }
    }
    /**
     * 向zk注册临时节点
     * @param port
     */
    private static void register(int port) {
        ZkClient client=new ZkClient("47.108.236.212");

        //解决invalid stream header: 3137322E
//        client.setZkSerializer(new MyZkSerializer());
        client.setZkSerializer(new SerializableSerializer());


        String rootPath="/dubbo";
        if(!client.exists(rootPath)) {
            //如果不存在持久化节点就创建
            client.createPersistent(rootPath);
        }
        try {
            //获取服务器的ip
            InetAddress inetAddress= InetAddress.getLocalHost();
            String ip=inetAddress.getHostAddress();
            //准备节点的值
            String service=ip+":"+port;
            //临时节点名称
            String lpath="/dubbo/"+service;
            if(client.exists(lpath)) {
                //如果之前创建的临时节点存在，删除
                client.delete(lpath);
            }
            //创建临时节点
            client.createEphemeral(lpath, service);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
