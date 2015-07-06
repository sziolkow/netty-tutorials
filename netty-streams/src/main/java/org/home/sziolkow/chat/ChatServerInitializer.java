package org.home.sziolkow.chat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;


/**
 * Created by slawomir.ziolkowski on 06.07.2015.
 */
public class ChatServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("framer", new LengthFieldBasedFrameDecoder(100, 0,2,0,2));
        pipeline.addLast("decoder", new ByteArrayDecoder());
        pipeline.addLast("encoder", new ByteArrayEncoder());
        pipeline.addLast("handler", new ChatServerHandler());
    }
}
