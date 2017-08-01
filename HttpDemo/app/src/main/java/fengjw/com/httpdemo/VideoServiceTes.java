package fengjw.com.httpdemo;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.test.AndroidTestCase;
import android.util.Log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by fengjw on 2017/8/1.
 */

public class VideoServiceTes extends AndroidTestCase {
    private static final String TAG = "fengjw";

    public void testLocalIpAndMac(){
        Log.d(TAG, "IP: "+getLocalIpAddress());
    }

    /**
     * 获取Android本机IP地址
     *
     * @return
     */
    private String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();){
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.d("WifiIpAddress", ex.toString());
        }
        return null;
    }


}
