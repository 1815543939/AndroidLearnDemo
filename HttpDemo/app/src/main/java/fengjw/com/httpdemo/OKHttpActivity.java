package fengjw.com.httpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OKHttpActivity extends AppCompatActivity implements View.OnClickListener{


    private TextView text_response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        Button btn_request = (Button) findViewById(R.id.btn_okrequest);
        text_response = (TextView) findViewById(R.id.text_okresponse);
        btn_request.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_okrequest){
            sendRequestWithOKHttp();
        }
    }

    private void sendRequestWithOKHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://45.32.253.249/get_data.json").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    //showResponse(responseData);
                    parseJSONWithJSONObject(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithJSONObject(String jsonData){
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i ++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                Log.d("fengjw",id);
                String name = jsonObject.getString("name");
                Log.d("fengjw",name);
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

}
