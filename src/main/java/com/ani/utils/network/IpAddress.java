package com.ani.utils.network;

import java.net.*;
import java.util.Enumeration;

/**
 * Created by zsl on 16-11-10.
 */
public class IpAddress {
    public static String getIpAddress() {
        String ip = "";
        String NETWORK_WIRE = "wlp3s0";
        String NETWORK_CARD = "enp0s25";
        String NETWORK_CARD_BAND = "bond0";
        try {
            Enumeration<NetworkInterface> e1 = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
            while (e1.hasMoreElements()) {
                NetworkInterface ni = e1.nextElement();
                //单网卡或者绑定双网卡
                //TODO 不同系统的适配问题
                if ((NETWORK_CARD.equals(ni.getName())) || (NETWORK_CARD_BAND.equals(ni.getName())) || NETWORK_WIRE.equals(ni.getName())) {
                    Enumeration<InetAddress> e2 = ni.getInetAddresses();
                    while (e2.hasMoreElements()) {
                        InetAddress ia = e2.nextElement();
                        if (ia instanceof Inet6Address) {
                            continue;
                        }
                        ip = ia.getHostAddress();
                        return ip;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("IpGetter.getLocalIP出现异常！异常信息：" + e.getMessage());
        }
        return ip;
    }

    public static byte[] getAddress() throws UnknownHostException {
        return InetAddress.getByName(getIpAddress()).getAddress();
    }
}