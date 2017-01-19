package com.ani.utils.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeh on 15-11-28.
 */
public class AniByte implements Serializable{

    byte[] bytes;

    public AniByte(){}

    public AniByte(byte[] bytes) {
        this.bytes = bytes;
    }

    public AniByte(String str) {
        if(str == null || str.length() < 1) this.bytes = new byte[0];
        this.bytes = str.getBytes();
    }

    public void setBytes(byte[] bytes){
        this.bytes = bytes;
    }

    public byte[] getBytes(){
        return bytes;
    }

    public int hashCode(){
        if(this.bytes == null || bytes.length < 1) return 0;
        int hashCode = 1;
        for(byte b: this.bytes)
            hashCode = 31 * hashCode + (int)b;
        return hashCode;
    }

    public boolean equals(Object obj) {
        AniByte byteObj = (AniByte)obj;
        return (this.hashCode() == byteObj.hashCode());
    }

    @Override
    public String toString(){
        if(this.bytes == null || this.bytes.length < 1) return "";
        StringBuilder byteStr = new StringBuilder(this.bytes.length);
        for(byte oneByte: this.bytes){
            byteStr.append(oneByte);
        }
        return byteStr.toString();
    }

    public static <T> Map<AniByte, T> fromByteMap(Map<byte[], T> byteMap){
        if(byteMap == null || byteMap.size() < 1){
            return new HashMap<AniByte, T>(1);
        }
        Map<AniByte, T> resultByteBufferMap = new HashMap<AniByte, T>(1);
        for(byte[] oneByteKey: byteMap.keySet()){
            resultByteBufferMap.put(
                    new AniByte(oneByteKey),
                    byteMap.get(oneByteKey));
        }

        return resultByteBufferMap;
    }
}
