package com.ddq.nettystudy

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandler
import io.netty.channel.ChannelInboundHandlerAdapter

/**
 * Author : ddq
 * Time : 2019/5/31 14:06
 * Description :
 */
class ClientHandler1:ChannelInboundHandlerAdapter() {
    override fun channelActive(ctx: ChannelHandlerContext) {
        //客户端连接服务端成功之后回调
        println("client connect server success")
        ctx.channel().writeAndFlush("hello server,i m client")
    }

    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        super.channelRead(ctx, msg)
        println(msg as String)
    }

    override fun channelInactive(ctx: ChannelHandlerContext) {
        super.channelInactive(ctx)
        println("server is dead")
    }
}