package com.ddq.nettystudy.serializer

import com.alibaba.fastjson.JSON

/**
 * Author : ddq
 * Time : 2019/6/12 10:57
 * Description :json序列化实现
 */
class JsonSerializer : Serializer {
    override fun serializeAlgorithm(): Byte {
        return SerializerAlgorithm.JSON
    }

    override fun serialize(data: Any): ByteArray {
        return JSON.toJSONBytes(data)
    }

    override fun <T> deserialize(cls: Class<T>, byteArray: ByteArray): T {
        return JSON.parseObject(byteArray,cls)
    }
}