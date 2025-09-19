package com.multi.travel.support;

public class NearBy {
    private double latitude;
    private double longitude;

    private double radian_lat;
    private double radian_lng;

    private static final double EARTH_RADIUS_M = 6_371_000.0;

    private int distance;

    public NearBy(double latitude, double longitude, int distance) {  //중심이 될 좌표들과 반경 몇키로까지인지 설정
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.radian_lat = Math.toRadians(this.latitude);
        this.radian_lng = Math.toRadians(this.longitude);
    }

    public boolean isNearBy(double lat, double lng){
        double radian_lt = Math.toRadians(lat);
        double radian_ln = Math.toRadians(lng);

        double dLat = radian_lt - this.radian_lat;
        double dLng = radian_ln- this.radian_lng;

        double sinLat = Math.sin(dLat / 2.0);
        double sinLon = Math.sin(dLng / 2.0);

        double a = sinLat * sinLat
                + Math.cos(radian_lt) * Math.cos(this.radian_lat) * sinLon * sinLon;
        double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0 - a));
        return distance <= EARTH_RADIUS_M * c;
    }


}
