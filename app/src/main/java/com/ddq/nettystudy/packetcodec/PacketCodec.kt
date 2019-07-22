package com.ddq.nettystudy.packetcodec

import com.ddq.nettystudy.protocal.Packet
import com.ddq.nettystudy.serializer.Serializer
import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufAllocator

/**
 * Author : ddq
 * Time : 2019/6/12 11:10
 * Description :
 */
object PacketCodec {

    private const val MAGIC_NUMBER: Int = 0xabcdef


    fun encode(packet: Packet): ByteBuf {

        val byteArray = Serializer.DEFAULT.serialize(packet)
        val byteBuf = ByteBufAllocator.DEFAULT.ioBuffer()

        //协议魔数
        byteBuf.writeInt(MAGIC_NUMBER)
        //协议版本号
        byteBuf.writeByte(packet.version.toInt())
        //序列化版本号
        byteBuf.writeByte(Serializer.DEFAULT.serializeAlgorithm().toInt())
        //指令
        byteBuf.writeByte(packet.getCommand().toInt())
        //数据长度
        byteBuf.writeInt(byteArray.size)
        //数据二进制
        byteBuf.writeBytes(byteArray)
        return byteBuf
    }

    fun <T> decode(clz: Class<T>, byteBuf: ByteBuf): T {
        //跳过魔数
        byteBuf.skipBytes(4)
        //跳过版本号
        byteBuf.skipBytes(1)
        //序列化算法
        val serializeAlgorithm = byteBuf.readByte()
        //指令
        val command = byteBuf.readByte()
        //数据长度
        val length = byteBuf.readInt()
        //数据
        val bytes = ByteArray(length)
        byteBuf.readBytes(bytes)

        return Serializer.DEFAULT.deserialize(clz, bytes)
    }
}