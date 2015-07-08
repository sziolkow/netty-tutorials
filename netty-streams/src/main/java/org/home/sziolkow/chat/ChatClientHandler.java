package org.home.sziolkow.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by slawomir.ziolkowski on 06.07.2015.
 */
    public class ChatClientHandler extends SimpleChannelInboundHandler<Byte[]> {

        private static final Logger logger = Logger.getLogger(
                ChatClientHandler.class.getName());

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            logger.log(
                    Level.WARNING,
                    "Unexpected exception from downstream.", cause);
            ctx.close();
        }

        @Override
        protected void channelRead0(ChannelHandlerContext chc, Byte[] i) throws Exception {
            System.out.println(i[3]);
        }
    }
