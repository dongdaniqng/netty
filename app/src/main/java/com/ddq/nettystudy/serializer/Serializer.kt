package com.ddq.nettystudy.serializer

/**
 * Author : ddq
 * Time : 2019/6/12 10:45
 * Description :
 */
interface Serializer {

    companion object{
        /**
         * json序列化
         */
        val JSON_SERIALIZER:Byte = 1
        /**
         * 默认序列化算法
         */
        val DEFAULT:JsonSerializer = JsonSerializer()
    }

    /**
     * 序列化算法表示
     * @return Byte
     */
    fun serializeAlgorithm():Byte

    /**
     * 对象序列化为二级制
     * @param data Any
     * @return ByteArray
     */
    fun serialize(data:Any):ByteArray

    /**
     * 二进制反序列化为对象
     * @param cls Class<T>
     * @param byteArray ByteArray
     * @return T
     */
    fun <T> deserialize(cls: Class<T>, byteArray: ByteArray):T
}