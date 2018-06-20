package com.fengjw.weatherdemo.model.entity;

/**
 * Created by fengjw on 2018/6/20.
 */

public class WeatherInfo {

    public WeatherInfoBean weatherInfo;

    public static class WeatherInfoBean{
        public String city;
        public String cityId;
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
        public String toString(){
            return "WeatherinfoBean{"+
                    "city='" + city + '\'' +
                    ", cityId='" + cityId + '\'' +
                    ", temp='" + temp + '\'' +
                    ", WD='" + WD + '\'' +
                    ", WS='" + WS + '\'' +
                    ", SD='" + SD + '\'' +
                    ", WSE='" + WSE + '\'' +
                    ", time='" + time + '\'' +
                    ", isRadar'" + isRadar + '\'' +
                    ", Radar'" + Radar + '\'' +
                    ", njd='" + njd + '\'' +
                    ", qy='" + qy + '\'' +
                    '}';
        }
    }

    @Override
    public String toString(){
        return "WeatherInfo{" +
                "weatherinfo=" + weatherInfo +
                '}';
    }
}
