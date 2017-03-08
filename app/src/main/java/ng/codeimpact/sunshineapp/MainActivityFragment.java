package ng.codeimpact.sunshineapp;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
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
                R.id.list_item_forecast_textview,
                //Forecast data
                weekForecast);

        //Get reference to the listview
        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(mForecastAdapter);




              return  rootView;



    }

    public class FetchWeatherTask extends AsyncTask<Void, Void, Void>{

        private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();
        @Override
        protected Void doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String forecastJsonStr = null;
            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=Uyo,DE&appid=12b713670eb1a25757bef50806438abe");

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }

                forecastJsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("MainActivityFragment", "Error closing stream", e);
                    }
                }
            }

            return null;
        }
    }
}
