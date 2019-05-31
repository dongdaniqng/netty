package com.ddq.nettystudy

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter

/**
 * Author : ddq
 * Time : 2019/5/31 14:24
 * Description :
 */
class ServerHandler1:ChannelInboundHandlerAdapter() {
    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        super.channelRead(ctx, msg)
        println(msg as String)

        ctx.channel().writeAndFlush("hello ,client, i m server")
    }
}