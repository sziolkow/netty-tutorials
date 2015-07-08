package org.home.sziolkow.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by slawek on 06/07/15.
 */
public class ChatClient implements DataChangeListener {

    public static void main(String[] argc) throws Exception {
        new ChatClient("localhost", 8000).run();
    }

    private final String host;

    private final int port;

    private String response;

    /**
     *
     * @param host
     * @param port
     */
    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
        DataChangeHandler.registerDataChangeListener(this);
    }

    public void run() throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap().
                    group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ChatClientInitializer());

            Channel channel = bootstrap.connect(host, port).sync().channel();

            channel.write("Duuuupa"+"\r\n");

            while (response == null) {
                Thread.sleep(100);
            }
        }
        finally {
            eventLoopGroup.shutdownGracefully();
        }

    }

    @Override
    public void dataChangeEnent(DataReadEvent event) {
        response = event.getMessage();
    }
}
