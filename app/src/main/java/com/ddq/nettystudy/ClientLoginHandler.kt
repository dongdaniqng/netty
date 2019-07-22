package com.ddq.nettystudy

import com.alibaba.fastjson.JSON
import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufAllocator
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandler
import io.netty.channel.ChannelInboundHandlerAdapter
import java.nio.ByteBuffer

/**
 * Author : ddq
 * Time : 2019/5/31 14:06
 * Description :
 */
class ClientLoginHandler:ChannelInboundHandlerAdapter() {
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