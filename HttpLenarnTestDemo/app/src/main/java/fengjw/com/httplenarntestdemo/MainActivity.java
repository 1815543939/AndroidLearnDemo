package fengjw.com.httplenarntestdemo;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textResponse;
    private  final static String TGA = "fengjw";
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnRequest = (Button) findViewById(R.id.btn_http);
        textResponse = (TextView) findViewById(R.id.text_response);
        btnRequest.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_http){
            //sendRequestWithHttpURLConnection();
            sendRequestWithOKHttp();
        }
    }


    private void sendRequestWithOKHttp(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    String url = "https://10.0.2.2/get_data.json";
//                    OkHttpClient client = new OkHttpClient.Builder()
//                            .sslSocketFactory(createSSLSocketFactory())
//                            .hostnameVerifier(new TrustAllHostnameVerifier()).build();
//                    Request request = new Request.Builder().url(url).build();
//                    Response response = client.newCall(request).execute();
//                    String responseData = response.body().string();
//                    //showResponse(responseData);
//                    //parseJSONWithJSONObject(responseData);
//                    parseNewJSONWithJSONObject(responseData);
//                }catch (Exception e){
//                    e.getMessage();
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        String url = "https://10.0.2.2/get_data.json";
        HttpUtil.sendOKHttpResquest(url, new okhttp3.Callback(){

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                parseNewJSONWithJSONObject(responseData);
            }
        });
    }



    private void parseNewJSONWithJSONObject(String responseData){
        Gson gson = new Gson();
        List<APP> appList = gson.fromJson(responseData, new TypeToken<List<APP>>(){}.getType());
        for (APP app : appList){ //foreach()
            Log.d(TGA, "appName : " + app.getApp_name());
            Log.d(TGA, "fileName : " + app.getFile_name());
            Log.d(TGA, "verName : " + app.getVer_name());
            Log.d(TGA, "verCode : " + app.getVer_code());
            Log.d(TGA, "url : " + app.getUrl());
            Log.d(TGA, "MD5 : " + app.getMD5());
        }
    }
    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textResponse.setText(response);
                Log.d("fengjw", response);
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
            sc.init(null, new TrustManager[] { new TrustAllCerts() }, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }


    private void sendRequestWithHttpURLConnection(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                HttpURLConnection httpURLConnection = null;
//                BufferedReader reader = null;
//                try {
//                    URL url = new URL("https://www.baidu.com");
//                    httpURLConnection = (HttpURLConnection) url.openConnection();
//                    //set some values
//                    httpURLConnection.setConnectTimeout(10000);
//                    httpURLConnection.setReadTimeout(10000);
//                    httpURLConnection.setRequestMethod("GET");
//
//                    InputStream inputStream = httpURLConnection.getInputStream();
//                    reader = new BufferedReader(new InputStreamReader(inputStream));
//                    StringBuilder response = new StringBuilder();
//                    String line;
//                    while ((line = reader.readLine()) != null){
//                        response.append(line);
//                    }
//
//                    showResponse(response.toString());
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                    e.getMessage();
//                }finally {
//                    if (reader != null){
//                        try {
//                            reader.close();
//                        }catch (IOException e){
//                            e.getMessage();
//                            e.printStackTrace();
//                        }
//                    }
//                    if (httpURLConnection != null){
//                        httpURLConnection.disconnect();
//                    }
//                }
//            }
//        }).start();

        String url = "https://www.baidu.com";
        HttpUtil.sendHttpRequest(url, new HttpCallbackListener() {
            @Override
            public void onFinish(final String response) {
                showResponse(response);
            }

            @Override
            public void onError(Exception e) {

            }
        });

    }

    private void parseJSONWithJSONObject(String responseData){
        try {
            JSONArray jsonArray = new JSONArray(responseData);
            for (int i = 0; i < jsonArray.length(); i ++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String appName = jsonObject.getString("app_name");
                String fileName = jsonObject.getString("file_name");
                String verName = jsonObject.getString("ver_name");
                String verCode = jsonObject.getString("ver_code");
                String url = jsonObject.getString("url");
                String MD5 = jsonObject.getString("MD5");

                Log.d(TGA, appName);
                Log.d(TGA, fileName);
                Log.d(TGA, verName);
                Log.d(TGA, "verCode: " + verCode);
                Log.d(TGA, url);
                Log.d(TGA, MD5);
                Log.d(TGA, "---------------------");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
