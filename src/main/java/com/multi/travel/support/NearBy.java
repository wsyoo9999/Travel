package com.multi.travel.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NearBy {
    private double lat;
    private double lng;
    private double radius; // km 단위
}
