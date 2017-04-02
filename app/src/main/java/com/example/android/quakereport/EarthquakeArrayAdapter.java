package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeArrayAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeArrayAdapter(@NonNull Context context, @NonNull ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        // Inflate our Site Item layout if there wasn't one already
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_item, parent, false);

            // Set up ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.magnitudeTextView = (TextView) convertView.findViewById(R.id.magnitude_textview);
            viewHolder.locationTextView = (TextView) convertView.findViewById(R.id.location_textview);
            viewHolder.dateTextView = (TextView) convertView.findViewById(R.id.date_textview);

            // Store holder with the view
            convertView.setTag(viewHolder);
        } else {
            // Just use the saved ViewHolder. No need to do all the findViewById stuff.
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Get the current Site
        Earthquake currentQuake = getItem(position);

        // Find and update layout views if we got one
        if (currentQuake != null) {

            viewHolder.magnitudeTextView.setText(String.format("%.1f", currentQuake.getMagnitude()));
            viewHolder.locationTextView.setText(currentQuake.getLocation());
            viewHolder.dateTextView.setText(new SimpleDateFormat("MMM d, yyyy").format(new Date(currentQuake.getDate())));
        }

        return convertView;
    }
}

class ViewHolder {
    TextView magnitudeTextView;
    TextView locationTextView;
    TextView dateTextView;
}