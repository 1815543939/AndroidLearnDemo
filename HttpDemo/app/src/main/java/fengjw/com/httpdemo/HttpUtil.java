package fengjw.com.httpdemo;

import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * Created by fengjw on 2017/8/1.
 */

public class HttpUtil {
//    public static String sendHttpRequest(String address){
//        HttpURLConnection connection = null;
//        try{
//            URL url = new URL(address);
//            connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            connection.setConnectTimeout(8000);
//            connection.setReadTimeout(8000);
//            connection.setDoInput(true);
//            connection.setDoOutput(true);
//
//            InputStream in = connection.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//            StringBuilder response = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null){
//                response.append(line);
//            }
//            return  response.toString();
//        }catch (Exception e){
//            e.printStackTrace();
//            return e.getMessage();
//        }finally {
//            if (connection != null){
//                connection.disconnect();
//            }
//        }
//    }

    //
    public static void sendOKHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(createSSLSocketFactory())
                .hostnameVerifier(new HttpUtil.TrustAllHostnameVerifier()).build();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
//    class TrustAllCerts implements X509TrustManager {
//
//        @Override
//        public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
//
//        }
//
//        @Override
//        public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
//
//        }
//
//        public X509Certificate[] getAcceptedIssuers() {return new X509Certificate[0];}
//    }

    //
    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    //
    public static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null,  new TrustManager[] { new TrustAllCerts() }, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }
}
