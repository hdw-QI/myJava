package com.study.zookeeper;

import lombok.SneakyThrows;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ZookeeperApplicationTests {
    static ZkClient zkClient = null;
    @BeforeAll
    public static void init(){
        // 创建连接对象,默认的连接端口为2181
        zkClient = new ZkClient("47.108.236.212");
    }

    @Test
    public void createEphemeral() throws InterruptedException {
        //连接节点 客户端断开连接后，连接节点就会被删除 !!!
        //创建临时节点(java结束操作后默认为30秒钟有效期)
        //zkClient.createEphemeral("/name","zhang");

        //创建持久化节点
        zkClient.createPersistent("/name", "wang");
    }

    @Test
    public void testCreatePersistent(){
        //创建持久化节点
        //如果节点存在他不能重复创建
        //先要有name节点
        zkClient.createPersistent("/name/w", "wang111");
    }

    @Test
    public void getNodeList(){
        List<String> children = zkClient.getChildren("/name");
        //获取所有的子节点
        System.out.println(children);
        //遍历子节点, 获取子节点保存的内容
        for (String child : children) {
            Object o = zkClient.readData("/name/" + child);
            System.out.println(o);
        }
    }

    @Test
    public void testWrite(){
        // 修改节点的值
        zkClient.writeData("/name/w","张三");
    }

    @Test
    public void testDelete(){
        // 注意, 不能删除非空节点(包含子节点)
        boolean delete = zkClient.delete("/name");
        System.out.println(delete);
    }

    @Test
    public void testDeleteRecursive(){
        // 递归删除, 会删除该节点下所有子节点
        boolean delete = zkClient.deleteRecursive("/name");
        System.out.println(delete);
    }

    @SneakyThrows
    @Test
    public void testSubscribe(){
        //监听name节点的更新
        zkClient.subscribeChildChanges("/name", new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String>
                    children) throws Exception {
                //当被监听的节点发生改变的时候，触发该方法

                System.out.println("---------------------------");
                //遍历子节点, 获取子节点保存的内容
                for (String child : children) {
                    Object o = zkClient.readData("/name/" + child);
                    System.out.println(o);
                }
            }
        });
        // 创建节点
        zkClient.createPersistent("/name/d", "dang");
        // 设置休眠时间,触发监听
        Thread.sleep(3000);
        // 删除节点
        zkClient.delete("/name/d");
        // 设置休眠时间,触发监听
        Thread.sleep(3000);
    }



    //测试轮询
    @Test
    public void testConsumer(){
        // 修改节点的值
        zkClient.writeData("/name/w","张三");
    }

}
