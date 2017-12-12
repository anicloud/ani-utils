package com.ani.utils.core.sms;

import com.ani.utils.core.AniByte;
import com.ani.utils.core.datatype.AniDataPrimitiveTypes;

/**
 * Created by zhanglina on 17-11-13.
 */
public class AniByteUtils {
    public static AniByte primiType2Anibyte(AniDataPrimitiveTypes types, Object values){
        switch (types){

            case INTEGER:
                return new AniByte((Integer) values);
            case PERCENTAGE:
                return new AniByte((Short)values);
            case FLOAT:
                return new AniByte((Float)values);
            case STRING:
                return new AniByte((String)values);
            case BOOLEAN:
                return new AniByte((Boolean)values);
            case BINARY:
                return new AniByte((Byte[]) values);
            case CHAR:
                return new AniByte((Character)values);
            case SHORT:
                return new AniByte((Short)values);
            case LONG:
                return new AniByte((Long)values);
            case BYTE:
                return new AniByte((Byte)values);
            case OBJECT:
                // TODO: 17-11-13  for object 
                return null;
        }
        return null;
    }
    public static Object AniTypetoPriTypeValue(AniByte aniByte,AniDataPrimitiveTypes types){
        switch (types){

            case INTEGER:
                return aniByte.getIntValue();
            case PERCENTAGE:
                return aniByte.byte2Short();
            case FLOAT:
                return aniByte.byte2Float();
            case STRING:
                return new String(aniByte.getBytes());
            case BOOLEAN:
                return aniByte.byteArray2Boolean();
            case BINARY:
                return aniByte.toBytes();
            case CHAR:
                return aniByte.byteToChar();
            case SHORT:
                return aniByte.byte2Short();
            case LONG:
                return aniByte.bytes2Long();
            case BYTE:
                return aniByte.toByte();
            case OBJECT:
                // TODO: 17-11-13  for object
                return null;
        }
        return null;

    }
}
