package fengjw.com.httpdemo;

/**
 * Created by fengjw on 2017/8/1.
 */

public class App {
    private String app_name;
    private String file_name;
    private String verName;
    private int ver_code;
    private String url;
    private String MD5;

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getVer_name() {
        return verName;
    }

    public void setVer_name(String verName) {
        this.verName = verName;
    }

    public int getVer_code() {
        return ver_code;
    }

    public void setVer_code(int ver_code) {
        this.ver_code = ver_code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMD5() {
        return MD5;
    }

    public void setMD5(String MD5) {
        this.MD5 = MD5;
    }
}
