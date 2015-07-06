package org.home.sziolkow.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;

/**
 * Created by slawomir.ziolkowski on 06.07.2015.
 */
public class ChatClientHandler extends ChannelInboundMessageHandlerAdapter<String> {

    @Override
    public void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println(s);
    }
}
