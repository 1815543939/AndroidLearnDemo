package com.example.mvpdemo.model.entity;

/**
 * Created by Administrator on 2016/4/24.
 */
public class WeatherInfo {
    public WeatherinfoBean weatherinfo;

    public static class WeatherinfoBean {
        public String city;
        public String cityid;
        public String temp;
        public String WD;
        public String WS;
        public String SD;
        public String WSE;
        public String time;
        public String isRadar;
        public String Radar;
        public String njd;
        public String qy;
        @Override
        public String toString() {
            return "WeatherinfoBean{" +
                    "city='" + city + '\'' +
                    ", cityid='" + cityid + '\'' +
                    ", temp='" + temp + '\'' +
                    ", WD='" + WD + '\'' +
                    ", WS='" + WS + '\'' +
                    ", SD='" + SD + '\'' +
                    ", WSE='" + WSE + '\'' +
                    ", time='" + time + '\'' +
                    ", isRadar='" + isRadar + '\'' +
                    ", Radar='" + Radar + '\'' +
                    ", njd='" + njd + '\'' +
                    ", qy='" + qy + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "weatherinfo=" + weatherinfo +
                '}';
    }
}
