package com.example.mauro.weatherapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mauro.weatherapp.data.MyList;

import java.util.List;


/**
 * Created by mauro on 7/29/17.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherHolder> {

    private static final String URL_ICON = "http://openweathermap.org/img/w/";
    private List<MyList> resultApis;

    public WeatherAdapter(List<MyList> resultApis) {
        this.resultApis = resultApis;
    }

    @Override
    public WeatherHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row, parent, false);
        return new WeatherHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherHolder holder, int position) {
        MyList myList = resultApis.get(position);
        holder.bind(myList);
    }

    @Override
    public int getItemCount() {
        return resultApis != null ? resultApis.size() : 0;
    }


    class WeatherHolder extends RecyclerView.ViewHolder {

        private TextView textViewDay;
        private TextView textViewDate;
        private TextView textViewDesc;
        private TextView textViewTemp;
        private ImageView imageView;

        public WeatherHolder(View itemView) {
            super(itemView);

            textViewDay = (TextView) itemView.findViewById(R.id.r_weather_day);
            textViewDate = (TextView) itemView.findViewById(R.id.r_weather_date);
            textViewDesc = (TextView) itemView.findViewById(R.id.r_weather_description);
            textViewTemp = (TextView) itemView.findViewById(R.id.r_weather_temp);
            imageView = (ImageView) itemView.findViewById(R.id.r_weather_img);
        }

        public void bind(MyList myList) {
            textViewDay.setText(myList.getDayFormart());
            textViewDate.setText(myList.getMonthDayFormat());
            textViewDesc.setText(myList.getWeatherDescription());
            textViewTemp.setText(myList.getTemperatureFormart());
            String url = URL_ICON + myList.getWeatherIcon();
            Glide.with(imageView.getContext())
                    .load(url)
                    .centerCrop()
                    .into(imageView);
        }
    }
}
