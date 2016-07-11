package com.ani.test.utils;

import com.ani.utils.core.AniByte;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yeh on 6/28/16.
 */
public class TestCore {

    public void testAniByteHash(){
        Set<AniByte> aniBytes = new HashSet<AniByte>(10);
        //insert
        AniByte byteA = new AniByte(new byte[]{(byte)192, (byte)168, (byte)1, (byte)101});
        aniBytes.add(byteA);
        AniByte byteB = new AniByte(new byte[]{(byte)192, (byte)168, (byte)1, (byte)110});
        aniBytes.add(byteB);
        AniByte byteC = new AniByte(new byte[]{(byte)192, (byte)168, (byte)1, (byte)111});
        aniBytes.add(byteC);

        //fetch
        System.out.println(aniBytes.size());
        System.out.println(aniBytes.contains(byteA));
        System.out.println(aniBytes.contains(byteB));
        System.out.println(aniBytes.contains(byteC));
    }
}
