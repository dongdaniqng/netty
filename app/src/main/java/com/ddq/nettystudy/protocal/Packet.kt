package com.ddq.nettystudy.protocal

/**
 * Author : ddq
 * Time : 2019/6/12 10:23
 * Description :基础协议抽象类
 */
abstract class Packet {
    /**
     * 协议包版本
     */
    var version: Byte = 1
    abstract fun getCommand():Byte
}