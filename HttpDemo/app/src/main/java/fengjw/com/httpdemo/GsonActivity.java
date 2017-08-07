package fengjw.com.httpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GsonActivity extends AppCompatActivity implements View.OnClickListener{
    private final static String TGA = "fengjw";
    private TextView text_response;
    private String url = "https://10.0.2.2/get_data.json";
    //private String url = "http://10.0.2.15:40609/get_data.json";
    //private String url1 = "http://192.168.10.254";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);
        Button btn_request = (Button) findViewById(R.id.btn_gsonrequest);
        text_response = (TextView) findViewById(R.id.text_gsonresponse);
        btn_request.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_gsonrequest){
            sendRequestWithOKHttp();
        }
    }

    private void sendRequestWithOKHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient.Builder()
                            .sslSocketFactory(createSSLSocketFactory())
                            .hostnameVerifier(new GsonActivity.TrustAllHostnameVerifier())
                            .build();
                    Request request = new Request.Builder().url(url).build();
                    Log.d("fengjw", "request: " + request.toString());
                    Response response = client.newCall(request).execute();
                    Log.d("fengjw",response.toString());
                    String responseData = null;
                    if (response.isSuccessful()){
                        responseData = response.body().string();
                    }else {
                        throw new IOException("Unexpected code " + response);
                    }
                    Log.d("fengjw", responseData);


                    //showResponse(responseData);
                    //parseJSONWithJSONObject(responseData);
                    parseJSONWithGSON(responseData);


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithGSON(String jsonData){
        Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData,new TypeToken<List<App>>(){}.getType());
        for (App app : appList){
            Log.d(TGA, "appName : " + app.getApp_name());
            Log.d(TGA, "fileName : " + app.getFile_name());
            Log.d(TGA, "verName : " + app.getVer_name());
            Log.d(TGA, "verCode : " + app.getVer_code());
            Log.d(TGA, "url : " + app.getMD5());
            Log.d(TGA, "MD5 : " + app.getUrl());
        }
    }

    private void parseJSONWithJSONObject(String jsonData){
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i ++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                Log.d("fengjw","Id: " + id);
                String version = jsonObject.getString("version");
                Log.d("fengjw","version : " + version);
                String name = jsonObject.getString("name");
                Log.d("fengjw","name: " + name);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void showResponse(final String responseData){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text_response.setText(responseData);
            }
        });

    }


    class TrustAllCerts implements X509TrustManager {

        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {

        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {

        }

        public X509Certificate[] getAcceptedIssuers() {return new X509Certificate[0];}
    }
    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }


    private SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null,  new TrustManager[] { new GsonActivity.TrustAllCerts() }, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }
}
