package com.study.zookeeper.register;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 胡代伟
 * @description 远程调用消费方
 * @date 2024-03-14 19:10
 */
@Slf4j
public class Consumer {
    /**
     * 存储服务器的地址
     */
    private static List<String> listServer = new ArrayList<>();
    // 服务调用初始化
    private static void initServer() {
        //连接注册中心
        ZkClient client = new ZkClient("47.108.236.212");
        // 指定根节点为/dubbo
        String rootPath = "/dubbo";
        // 如果不存在持久化节点就抛异常
        if (!client.exists(rootPath)) {
            System.out.println("没有服务器提供服务!");
            throw new RuntimeException("没有服务器提供服务!");
        }
        // 清空服务器的列表
        listServer.clear();
        List<String> children = client.getChildren(rootPath);
        // 获取服务器的列表
        for (String name : children) {
            listServer.add(client.readData("/dubbo/" + name));
        }
        System.out.println(listServer);
        // 订阅监听
        // 当监听到注册中心发生改变, 则情况服务器列表并重新获取
        client.subscribeChildChanges(rootPath, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                // 清空服务器的列表
                listServer.clear();
                for (String c : currentChilds) {
                    listServer.add(client.readData("/dubbo/" + c));
                }
                System.out.println("-------------service change------" + listServer);
            }
        });
    }
    private static int count=0;
    // 获取服务器的列表
    public static String getServer() {
        // 负载均衡

        //随机 2 0,1
        //int index=new Random().nextInt(listServer.size());
        //轮询 访问次数%服务器的个数
        int index=count%listServer.size();
        count++;
        log.info("轮询:"+index);
        log.info("count:"+count);

        return listServer.get(index);
    }
    public static void main(String[] args) throws Exception {
        try {
            // 服务调用方启动向zk获取服务器的地址
            initServer();
            // 1.创建Socket对象
            while (true) {
                // 172.16.105.247:9527
                String server = getServer();
                log.info("server-->{}",server);
                String[] address = server.split(":");
                Socket socket = new Socket(address[0], Integer.valueOf(address[1]));
                // 2.发送数据
                // 获取字节输出流
                OutputStream out = socket.getOutputStream();
                Scanner sc = new Scanner(System.in);
                String str = sc.next();
                out.write(str.getBytes());
                // 3.获取服务器端响应的数据
                InputStream in = socket.getInputStream();
                byte[] buffer = new byte[1024];
                int len = in.read(buffer);
                System.out.println(new String(buffer, 0, len));
                socket.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("暂时没有服务器，请稍等!");
        }
    }

}
