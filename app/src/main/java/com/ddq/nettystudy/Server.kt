package com.ddq.nettystudy

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder

/**
 * Author : ddq
 * Time : 2019/5/31 11:44
 * Description :
 */
class Server {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            instance().initServer()
        }

        private var server: Server? = null
        private fun instance(): Server {
            if (server == null) {
                synchronized(Server::class.java) {
                    server = Server()
                }
            }
            return server!!
        }
    }

    private fun initServer() {
        val serverBootstrap = ServerBootstrap()

        val boss = NioEventLoopGroup()
        val worker = NioEventLoopGroup()

        var port = 9999
        //指定监听线程 和 工作线程
        serverBootstrap.group(boss, worker)
            //在启动过程中处理
            .handler(object : ChannelInitializer<NioServerSocketChannel>() {
                override fun initChannel(ch: NioServerSocketChannel?) {
                    println("server is starting")
                }
            })
            //临时存放已完成三次握手的请求的队列的最大长度
            .option(ChannelOption.SO_BACKLOG, 1024)
            //指定IO模型
            .channel(NioServerSocketChannel::class.java)
            //开启心跳连接
            .childOption(ChannelOption.SO_KEEPALIVE, true)
            //不延迟发送数据
            .childOption(ChannelOption.TCP_NODELAY, true)
            //处理业务逻辑
            .childHandler(object : ChannelInitializer<NioSocketChannel>() {
                override fun initChannel(ch: NioSocketChannel) {
                    ch.pipeline().addLast(ServerHandler1())
                }
            })
            .bind(port)
            .addListener {
                //如果绑定端口失败,则自增
                if (!it.isSuccess) {
                    serverBootstrap.bind(port + 1)
                }
            }
    }
}