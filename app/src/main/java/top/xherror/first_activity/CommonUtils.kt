package top.xherror.first_activity

import org.apache.commons.codec.binary.Hex
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
val SALT="www"
val KEY="xherror"
object CommomUtils {
    fun createSignature(rawSecret:String,key:String):String{
        val saltedSecret=rawSecret+ SALT
        val sha256Hmac=Mac.getInstance("HmacSHA256")
        val secretKey=SecretKeySpec(key.toByteArray(),"HmacSHA256")
        sha256Hmac.init(secretKey)
        return Hex.encodeHexString(sha256Hmac.doFinal(saltedSecret.toByteArray()))
    }
}