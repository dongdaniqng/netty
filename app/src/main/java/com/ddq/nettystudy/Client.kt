package com.ddq.nettystudy

import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder

/**
 * Author : ddq
 * Time : 2019/5/31 11:45
 * Description :
 */
class Client {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            instance().initClient()
        }

        private var client: Client? = null
        private fun instance(): Client {
            if (client == null) {
                synchronized(Client::class.java) {
                    client = Client()
                }
            }
            return client!!
        }
    }

    fun initClient() {
        val bootstrap = Bootstrap()

        val group = NioEventLoopGroup()

        val ip = "192.168.0.151"
        val port = 9999

        val channel = bootstrap.group(group)
            .channel(NioSocketChannel::class.java)
            .handler(object : ChannelInitializer<NioSocketChannel>() {
                override fun initChannel(ch: NioSocketChannel) {
                    println("init client")
                    ch.pipeline().addLast(StringEncoder())
                    ch.pipeline().addLast(StringDecoder())
                    ch.pipeline().addLast(ClientHandler1())
                }
            })
            //连接超时时间
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
            .option(ChannelOption.SO_KEEPALIVE, true)
            .option(ChannelOption.TCP_NODELAY, true)

            .connect(ip, port)
            .addListener {
                if (!it.isSuccess) {
                    reConnect(bootstrap, ip, port)
                }
            }
            .channel()


//        while (true) {
//            channel.writeAndFlush("hello\n")
//            Thread.sleep(2000)
//        }
    }

    private fun reConnect(bootstrap: Bootstrap, ip: String, port: Int) {
        bootstrap.connect(ip, port)
            .addListener {
                if (!it.isSuccess) {
                    Thread.sleep(2000)
                    print("reconnect\n")
                    reConnect(bootstrap, ip, port)
                }
            }
    }
}