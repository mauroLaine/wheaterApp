package com.example.mauro.weatherapp.data;

import java.util.Date;

/**
 * Created by mauro on 7/30/17.
 */

public class Test {
    public static void main(String[] args) {

        long day = 1501437600L;
        Date today = new Date(day * 1000);
        System.out.println(today.toString());

    }

}
