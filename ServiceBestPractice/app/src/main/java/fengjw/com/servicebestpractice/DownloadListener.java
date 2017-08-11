package fengjw.com.servicebestpractice;

/**
 * Created by fengjw on 2017/8/11.
 */

public interface DownloadListener {
    void onProgress(int progress);
    void onSuccess();
    void onFailed();
    void onPaused();
    void onCanceled();
}
