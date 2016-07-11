package com.ani.utils.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeh on 15-11-28.
 */
public class AniByte implements Serializable{

    byte[] bytes;

    public AniByte(byte[] bytes) {
        this.bytes = bytes;
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

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof AniByte)) return false;
        return obj.hashCode() == this.hashCode();
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
