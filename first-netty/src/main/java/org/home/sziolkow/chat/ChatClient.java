package org.home.sziolkow.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by slawek on 06/07/15.
 */
public class ChatClient {

    public static void main(String[] argc) throws Exception {
        new ChatClient("localhost", 8000).run();
    }

    private final String host;

    private final int port;

    /**
     *
     * @param host
     * @param port
     */
    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap().
                    group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ChatClientInitializer());

            Channel channel = bootstrap.connect(host, port).sync().channel();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                channel.write(bufferedReader.readLine()+"\r\n");
            }
        }
        finally {
            eventLoopGroup.shutdownGracefully();
        }

    }
}
