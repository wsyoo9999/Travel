package com.multi.travel.vo;

import lombok.Data;

@Data
public class TourismVO {
    private int no;
    private String district;
    private String title;
    private String description;
    private String address;
    private String phone;
    private double lat;
    private double lng;
    private String img;
}
