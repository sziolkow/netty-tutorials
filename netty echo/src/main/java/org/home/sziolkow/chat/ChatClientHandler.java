package org.home.sziolkow.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by slawomir.ziolkowski on 06.07.2015.
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
        DataChangeHandler.fireChangeEvent(new DataReadEvent(msg));
    }
}
