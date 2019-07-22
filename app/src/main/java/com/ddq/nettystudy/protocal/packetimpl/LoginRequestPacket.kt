package com.ddq.nettystudy.protocal.packetimpl

import com.ddq.nettystudy.protocal.Command
import com.ddq.nettystudy.protocal.Packet

/**
 * Author : ddq
 * Time : 2019/6/12 10:30
 * Description :请求登录
 */
data class LoginRequestPacket(var userId: String, var userName: String, var password: String) :
    Packet() {

    override fun getCommand(): Byte {
        return Command.LOGIN_REQUEST
    }
}