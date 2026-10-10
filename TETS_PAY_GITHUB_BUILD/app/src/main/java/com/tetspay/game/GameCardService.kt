package com.tetspay.game

import android.nfc.cardemulation.HostApduService
import android.os.Bundle

class GameCardService : HostApduService() {
    override fun processCommandApdu(commandApdu: ByteArray?, extras: Bundle?): ByteArray {
        // Game-only token. This is not a bank card and cannot make real payments.
        val command = commandApdu?.joinToString("") { "%02X".format(it) } ?: ""
        return if (command.startsWith("00A40400")) hex("6F10840AF0010203040506A5029000") else hex("54544554535041599000")
    }
    override fun onDeactivated(reason: Int) {}
    private fun hex(s: String) = s.chunked(2).map { it.toInt(16).toByte() }.toByteArray()
}
