package com.example.mauro.weatherapp.data;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by mauro on 7/31/17.
 */

public class MyListTest {

    private MyList myList;

    @Before
    public void setUp() {
        myList = new MyList();
    }

    @Test
    public void init_shouldBeAbleToInstantiateMyList() {
        assertNotNull(myList);
    }

    @Test
    public void temperatureFormat_shouldBeValid() {
        Temp temp = new Temp();

        temp.setMax(306.99);
        temp.setMin(293.48);
        myList.setTemp(temp);
        assertEquals("34°/20°", myList.getTemperatureFormart());

        temp.setMax(283.15);
        temp.setMin(278.15);
        myList.setTemp(temp);
        assertEquals("10°/5°", myList.getTemperatureFormart());

        temp.setMax(273.15);
        temp.setMin(263.15);
        myList.setTemp(temp);
        assertEquals("0°/-10°", myList.getTemperatureFormart());

        temp.setMax(258.15);
        temp.setMin(249.15);
        myList.setTemp(temp);
        assertEquals("-15°/-24°", myList.getTemperatureFormart());
    }

    @Test
    public void dayFormat_shouldBeValid(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        myList.setDt(1501520400L);
        assertEquals("Mon", myList.getDayFormart());

        myList.setDt(1502211600L);
        assertEquals("Tue", myList.getDayFormart());

        myList.setDt(1L);
        assertEquals("Wed", myList.getDayFormart());

        myList.setDt(calendar.getTimeInMillis()/1000);
        assertEquals("Mon", myList.getDayFormart());
    }

    @Test
    public void monthDayFormt_shouldBeValid(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        myList.setDt(1501520400L);
        assertEquals("07/31", myList.getMonthDayFormat());

        myList.setDt(1502211600L);
        assertEquals("08/08", myList.getMonthDayFormat());

        myList.setDt(1L);
        assertEquals("12/31", myList.getMonthDayFormat());

        myList.setDt(calendar.getTimeInMillis()/1000);
        assertEquals("07/31", myList.getMonthDayFormat());
    }

    @Test
    public void getWeatherDescription_shouldBeValid(){
        Weather myWeather = new Weather();
        myWeather.setDescription("light rain");
        List<Weather> weathers = new ArrayList<Weather>();
        weathers.add(myWeather);
        myList.setWeather(weathers);
        assertEquals("light rain", myList.getWeatherDescription());
    }

}
