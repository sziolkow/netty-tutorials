package org.home.sziolkow.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by slawomir.ziolkowski on 06.07.2015.
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<Byte[]> {
    private static final Logger logger = Logger.getLogger(
            ChatServerHandler.class.getName());

    static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        channels.add(ctx.channel());
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.log(
                Level.WARNING,
                "Unexpected exception from downstream.", cause);
        ctx.close();
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Byte[] i) throws Exception {
        logger.log(
                Level.INFO,
                "Channel read.");
        for (Channel c: channels) {
            if (c != ctx.channel()) {
                c.writeAndFlush(i);
            }
        }

    }
}