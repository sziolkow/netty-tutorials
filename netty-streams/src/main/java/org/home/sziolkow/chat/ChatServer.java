package org.home.sziolkow.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by slawomir.ziolkowski on 06.07.2015.
 */
public class ChatServer {

    public static void main(String[] argc) throws Exception {
        new ChatServer(8000).run();
    }

    private final int port;

    public ChatServer(int port) {
        this.port = port;
    }

    public void run() throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap =  new ServerBootstrap().
                    group(bossGroup, workerGroup).
                    channel(NioServerSocketChannel.class).
                    childHandler(new ChatServerInitializer());
            serverBootstrap.bind(port).sync().channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }


}
