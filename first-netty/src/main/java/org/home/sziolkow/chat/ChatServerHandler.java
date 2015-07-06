package org.home.sziolkow.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

/**
 * Created by slawomir.ziolkowski on 06.07.2015.
 */
public class ChatServerHandler extends ChannelInboundMessageHandlerAdapter<String> {

    private static ChannelGroup channels = new DefaultChannelGroup();

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

    @Override public void messageReceived(ChannelHandlerContext channelHandlerContext, String message) throws Exception {
        Channel incoming = channelHandlerContext.channel();

        for (Channel channel : channels) {
           // if (channel != incoming) {
                channel.write("[" + incoming.remoteAddress() +"] " +message + "\n");
           // }

        }
    }
}
