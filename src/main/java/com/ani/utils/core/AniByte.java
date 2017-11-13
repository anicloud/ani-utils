package com.ani.utils.core;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import javax.security.auth.callback.CallbackHandler;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeh on 15-11-28.
 */
public class AniByte implements Serializable {

    byte[] bytes = null;

    public AniByte() {
    }

    public AniByte(byte[] bytes) {
        this.bytes = bytes;
    }

    public AniByte(String str) {
        if (str == null || str.length() < 1) this.bytes = new byte[0];
        this.bytes = str.getBytes();
    }

    public AniByte(int integer) {
        this.setByteFromInt(integer);
    }
    public AniByte (Long longvalue){
        this.long2Bytes(longvalue);
    }
    public AniByte(short shortvalue){
        this.short2Byte(shortvalue);
    }
    public AniByte(char charvalue){
        this.charToByte(charvalue);
    }
    public AniByte(boolean b){
        this.boolean2ByteArray(b);
    }
    public AniByte(float floatValue){
        this.float2Byte(floatValue);
    }
    public AniByte(Byte[] bytes){
        this.bytes=new byte[bytes.length];
        for (int i=0;i<bytes.length;i++){
            this.bytes[i]=bytes[i].byteValue();
        }
    }

    public AniByte(Byte by){
        this.bytes=new byte[]{by.byteValue()};

    }
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }

    //int
    public void setByteFromInt(int integer) {
        this.bytes = new byte[4];
        this.bytes[3] = (byte) (integer & 0xFF);
        this.bytes[2] = (byte) ((integer >> 8) & 0xFF);
        this.bytes[1] = (byte) ((integer >> 16) & 0xFF);
        this.bytes[0] = (byte) ((integer >> 24) & 0xFF);
    }

    public int getIntValue() {
        int integer = (this.bytes[3] & 0xFF) |
                (this.bytes[2] & 0xFF) << 8 |
                (this.bytes[1] & 0xFF) << 16 |
                (this.bytes[0] & 0xFF) << 24;
        return integer;
    }
    public Byte toByte(){
        return (Byte)this.bytes[0];
    }
    public Byte[] toBytes(){
        Byte[] bs=new Byte[bytes.length];
        for (int i=0;i<bytes.length;i++){
            bs[i]=(Byte)bytes[i];
        }
        return bs;
    }    //long
    public void  long2Bytes(long num) {
        this.bytes = new byte[8];
        for (int ix = 0; ix < 8; ++ix) {
            int offset = 64 - (ix + 1) * 8;
            this.bytes[ix] = (byte) ((num >> offset) & 0xff);
        }
    }

    public  long bytes2Long() {
        long num = 0;
        for (int ix = 0; ix < 8; ++ix) {
            num <<= 8;
            num |= (this.bytes[ix] & 0xff);
        }
        return num;
    }
    //short
    public  void short2Byte(short a){
        this.bytes = new byte[2];

        this.bytes[0] = (byte) (a >> 8);
        this.bytes[1] = (byte) (a);

    }
    public short byte2Short(){
        return (short) (((this.bytes[0] & 0xff) << 8) | (this.bytes[1] & 0xff));
    }
    //char
    public void  charToByte(char c) {
       this.bytes = new byte[2];
        this.bytes[0] = (byte) ((c & 0xFF00) >> 8);
        this.bytes[1] = (byte) (c & 0xFF);
    }
    public char byteToChar() {
        char c = (char) (((this.bytes[0] & 0xFF) << 8) | (this.bytes[1] & 0xFF));
        return c;
    }
    //boolean
    public  void  boolean2ByteArray(boolean val) {
        int tmp = (val == false) ? 0 : 1;
        this.bytes= ByteBuffer.allocate(4).putInt(tmp).array();
    }

    public boolean byteArray2Boolean() {
        if (this.bytes == null || this.bytes.length < 4) {
            return false;
        }
        int tmp = ByteBuffer.wrap(this.bytes, 0, 4).getInt();
        return (tmp == 0) ? false : true;
    }
    //float
    public void  float2Byte(Float f){
        int values=Float.floatToIntBits(f);
        this.bytes=new AniByte(values).getBytes();

    }
    public  float byte2Float(){
        int values= new AniByte(bytes).getIntValue();
        return Float.intBitsToFloat(values);

    }



    public int hashCode() {
        if (this.bytes == null || bytes.length < 1) return 0;
        int hashCode = 1;
        for (byte b : this.bytes)
            hashCode = 31 * hashCode + (int) b;
        return hashCode;
    }

    public boolean equals(Object obj) {
        AniByte byteObj = (AniByte) obj;
        return (this.hashCode() == byteObj.hashCode());
    }

    @Override
    public String toString() {
        if (this.bytes == null || this.bytes.length < 1) return "";
        StringBuilder byteStr = new StringBuilder(this.bytes.length);
        for (byte oneByte : this.bytes) {
            byteStr.append(oneByte);
        }
        return byteStr.toString();
    }

    public static <T> Map<AniByte, T> fromByteMap(Map<byte[], T> byteMap) {
        if (byteMap == null || byteMap.size() < 1) {
            return new HashMap<AniByte, T>(1);
        }
        Map<AniByte, T> resultByteBufferMap = new HashMap<AniByte, T>(1);
        for (byte[] oneByteKey : byteMap.keySet()) {
            resultByteBufferMap.put(
                    new AniByte(oneByteKey),
                    byteMap.get(oneByteKey));
        }

        return resultByteBufferMap;
    }
}
