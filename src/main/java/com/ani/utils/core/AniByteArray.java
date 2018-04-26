package com.ani.utils.core;

import com.ani.utils.exception.AniDataException;
import com.ani.utils.exception.AniRuleException;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * Created by yeh on 15-11-28.
 */
public class AniByteArray implements Serializable {

    private static char hexDigits[] = {
            '0',
            '1',
            '2',
            '3',
            '4',
            '5',
            '6',
            '7',
            '8',
            '9',
            'a',
            'b',
            'c',
            'd',
            'e',
            'f'
    };
    byte[] bytes = null;
    Integer curIdx = 0;

    public AniByteArray() {
    }

    public AniByteArray(byte[] bytes) {
        this.bytes = bytes;
    }

    public AniByteArray(String str) {
        if (str == null || str.length() < 1) this.bytes = new byte[0];
        this.bytes = str.getBytes();
    }

    public AniByteArray(int length) throws AniDataException {
        if (length < 0)
            throw new AniDataException("BYTE_ARRAY_LENGTH_MUST_BE_POSITIVE");
        this.bytes = new byte[length];
        this.curIdx = 0;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    private void initAndCheckArrayCapacity(int cap) throws AniRuleException {
        if (this.bytes == null) {
            this.bytes = new byte[cap];
            this.curIdx = 0;
        }
        if ((this.bytes.length < cap)
                || (this.bytes.length - this.curIdx < cap)) {
            throw new AniRuleException("BYTE_ARRAY_CAPACITY_NOT_ENOUGH");
        }
    }

    public synchronized void setByteArrayValues(byte... bytes) throws AniRuleException {
        if(bytes == null) throw new AniRuleException("BYTE_ARRAY_IS_NULL");
        initAndCheckArrayCapacity(bytes.length);
        for(int idx = 0; idx < bytes.length; idx++){
            this.bytes[this.curIdx + idx] = bytes[idx];
        }
        this.curIdx += bytes.length;
    }

    //int
    public void setByteFromInt(int integer) throws AniRuleException {
        setByteArrayValues(
                (byte) ((integer >> 24) & 0xFF),
                (byte) ((integer >> 16) & 0xFF),
                (byte) ((integer >> 8) & 0xFF),
                (byte) (integer & 0xFF));
    }

    public int getIntValue() {
        int integer = (this.bytes[3] & 0xFF) |
                (this.bytes[2] & 0xFF) << 8 |
                (this.bytes[1] & 0xFF) << 16 |
                (this.bytes[0] & 0xFF) << 24;
        return integer;
    }

    public Byte toByte() {
        return (Byte) this.bytes[0];
    }

    public Byte[] toBytes() {
        Byte[] bs = new Byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            bs[i] = (Byte) bytes[i];
        }
        return bs;
    }    //long

    public void long2Bytes(long num) throws AniRuleException {
        byte[] longBytes = new byte[8];
        for (int ix = 0; ix < 8; ++ix) {
            int offset = 64 - (ix + 1) * 8;
            longBytes[ix] = (byte) ((num >> offset) & 0xff);
        }
        this.setByteArrayValues(longBytes);
    }

    public long bytes2Long() {
        long num = 0;
        for (int ix = 0; ix < 8; ++ix) {
            num <<= 8;
            num |= (this.bytes[ix] & 0xff);
        }
        return num;
    }

    //short
    public void short2Byte(short a) {
        this.bytes = new byte[2];

        this.bytes[0] = (byte) (a >> 8);
        this.bytes[1] = (byte) (a);

    }

    public short byte2Short() {
        return (short) (((this.bytes[0] & 0xff) << 8) | (this.bytes[1] & 0xff));
    }

    //char
    public void charToByte(char c) {
        this.bytes = new byte[2];
        this.bytes[0] = (byte) ((c & 0xFF00) >> 8);
        this.bytes[1] = (byte) (c & 0xFF);
    }

    public char byteToChar() {
        char c = (char) (((this.bytes[0] & 0xFF) << 8) | (this.bytes[1] & 0xFF));
        return c;
    }

    //boolean
    public void boolean2ByteArray(boolean val) {
        int tmp = (val == false) ? 0 : 1;
        this.bytes = ByteBuffer.allocate(4).putInt(tmp).array();
    }

    public boolean byteArray2Boolean() {
        if (this.bytes == null || this.bytes.length < 4) {
            return false;
        }
        int tmp = ByteBuffer.wrap(this.bytes, 0, 4).getInt();
        return (tmp == 0) ? false : true;
    }

    //float
    public void float2Byte(Float f) throws AniDataException {
        int values = Float.floatToIntBits(f);
        this.bytes = new AniByteArray(values).getBytes();

    }

    public float byte2Float() {
        int values = new AniByteArray(bytes).getIntValue();
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
        AniByteArray byteObj = (AniByteArray) obj;
        return (this.hashCode() == byteObj.hashCode());
    }

    public String getHexStringFromByte() {
        if (this.bytes == null) return null;
        int length = this.bytes.length;
        char[] result = new char[length * 2];
        int k = 0;
        for (int i = 0; i < length; i++) {
            byte byte0 = this.bytes[i];
            result[k++] = hexDigits[byte0 >>> 4 & 0xf];
            result[k++] = hexDigits[byte0 & 0xf];
        }
        return String.valueOf(result);
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
}
