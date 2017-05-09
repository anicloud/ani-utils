package com.ani.utils.core;

import java.io.Serializable;
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

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }

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
