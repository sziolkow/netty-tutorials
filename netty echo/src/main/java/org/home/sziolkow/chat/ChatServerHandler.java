package org.home.sziolkow.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by slawomir.ziolkowski on 06.07.2015.
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext context) {
        Channel incoming = context.channel();
        for (Channel channel : channels) {
            channel.write("[SERVER] -" + incoming.remoteAddress() + " has joined!\n");
        }
        channels.add(incoming);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext context) {
        Channel incoming = context.channel();
        for (Channel channel : channels) {
            channel.write("[SERVER] -" + incoming.remoteAddress() + " has left!\n");
        }
        channels.remove(incoming);
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel incoming = ctx.channel();

        for (Channel channel : channels) {
            // if (channel != incoming) {
            channel.write("[" + incoming.remoteAddress() +"] " +msg + "\n");
            // }

        }
    }
}
