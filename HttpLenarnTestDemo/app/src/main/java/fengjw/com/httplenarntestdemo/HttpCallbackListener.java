package fengjw.com.httplenarntestdemo;

/**
 * Created by fengjw on 2017/8/7.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
