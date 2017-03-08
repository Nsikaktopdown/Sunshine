package ng.codeimpact.sunshineapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class  MainActivityFragment extends Fragment {


    private ArrayAdapter<String> mForecastAdapter;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView =  inflater.inflate(R.layout.fragment_main, container, false);


        //List of Dummy weather data

        String[] forecastArray = {
                "Today - Sunny - 88/63",
                "Tomorrow - Foogy - 70/40",
                "Weds - Cloudy - 72/65",
                "Thurs - Asteroids - 75/65",
                "Fri - Heavy Rain - 65/65",
                "Sat - HELP TRAPPED IN WEATHERSTATION - 60/51",
                "Sun - Sunny - 80/68"

        };


        //Array list
        List<String>  weekForecast = new ArrayList<String>(
                Arrays.asList(forecastArray));


        mForecastAdapter = new ArrayAdapter<String>(
                //Curent context(fragment [parent activity
                getActivity(),
                //ID of the List Item layout
                R.layout.list_item_forecast,
                //ID of the textview to populate
                R.id.listview_forecast,
                //Forecast data
                weekForecast);

        //Giv
            return  rootView;
    }
}
