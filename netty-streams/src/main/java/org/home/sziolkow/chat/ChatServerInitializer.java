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
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("frameEncoder",
                new LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4));
        pipeline.addLast("bytesDecoder",
                new ByteArrayDecoder());
        //pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));

        pipeline.addLast("bytesEncoder", new ByteArrayEncoder());
        pipeline.addLast("handler", new ChatServerHandler());
    }
}
