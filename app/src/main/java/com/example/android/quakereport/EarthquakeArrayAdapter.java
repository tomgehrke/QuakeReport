package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeArrayAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";

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
            viewHolder.primaryLocationTextView = (TextView) convertView.findViewById(R.id.primary_location_textview);
            viewHolder.locationOffsetTextView = (TextView) convertView.findViewById(R.id.location_offset_textview);
            viewHolder.dateTextView = (TextView) convertView.findViewById(R.id.date_textview);
            viewHolder.timeTextView = (TextView) convertView.findViewById(R.id.time_textview);

            // Store holder with the view
            convertView.setTag(viewHolder);
        } else {
            // Just use the saved ViewHolder. No need to do all the findViewById stuff.
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Get the current Site
        final Earthquake currentQuake = getItem(position);

        // Find and update layout views if we got one
        if (currentQuake != null) {

            // Set date-related text views
            Date quakeDate = new Date(currentQuake.getDate());
            viewHolder.dateTextView.setText(formatDate(quakeDate));
            viewHolder.timeTextView.setText(formatTime(quakeDate));

            // Set location-related text views
            String location = currentQuake.getLocation();
            String primaryLocation;
            String locationOffset;

            if (location.contains(LOCATION_SEPARATOR)) {
                String[] locationParts = location.split(LOCATION_SEPARATOR);

                locationOffset = locationParts[0] + LOCATION_SEPARATOR;
                primaryLocation = locationParts[1];
            } else {
                locationOffset = getContext().getString(R.string.near_the);
                primaryLocation = location;
            }

            viewHolder.locationOffsetTextView.setText(locationOffset);
            viewHolder.primaryLocationTextView.setText(primaryLocation);

            // Set magnitude text view
            DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
            viewHolder.magnitudeTextView.setText(magnitudeFormat.format(currentQuake.getMagnitude()));

            // Set magnitude circle color
            GradientDrawable magnitudeCircle = (GradientDrawable) viewHolder.magnitudeTextView.getBackground();
            magnitudeCircle.setColor(getMagnitudeColor(currentQuake.getMagnitude()));

        }

        return convertView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private int getMagnitudeColor(Double magnitude) {
        int magnitudeColor = 0;

        switch (magnitude.intValue()) {
            case 0:
            case 1:
                magnitudeColor = R.color.magnitude1;
                break;
            case 2:
                magnitudeColor = R.color.magnitude2;
                break;
            case 3:
                magnitudeColor = R.color.magnitude3;
                break;
            case 4:
                magnitudeColor = R.color.magnitude4;
                break;
            case 5:
                magnitudeColor = R.color.magnitude5;
                break;
            case 6:
                magnitudeColor = R.color.magnitude6;
                break;
            case 7:
                magnitudeColor = R.color.magnitude7;
                break;
            case 8:
                magnitudeColor = R.color.magnitude8;
                break;
            case 9:
                magnitudeColor = R.color.magnitude9;
                break;
            default:
                magnitudeColor = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColor);
    }
}

class ViewHolder {
    TextView magnitudeTextView;
    TextView primaryLocationTextView;
    TextView locationOffsetTextView;
    TextView dateTextView;
    TextView timeTextView;
}

