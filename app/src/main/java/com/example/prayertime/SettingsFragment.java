package com.example.prayertime;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class SettingsFragment extends PreferenceFragment {

    // list prefs
    public static final String PREF_JURISTIC = "juristic";
    public static final String PREF_CALC = "calculation";
    public static final String PREF_LATITUDE = "latitude";
    public static final String PREF_TIME = "time";

    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefrences);
        //Listener for all the groups
        preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals(PREF_JURISTIC)) {
                    ListPreference juristicPref = (ListPreference) findPreference(key); //if you need the prefrence key similar to find by ID
                    Toast.makeText(getActivity(), juristicPref.getValue(), Toast.LENGTH_SHORT).show();
                }
                if (key.equals(PREF_CALC)) {
                    ListPreference calculatePref = (ListPreference) findPreference(key);
                    Toast.makeText(getActivity(), calculatePref.getValue(), Toast.LENGTH_SHORT).show();
                }
                if (key.equals(PREF_LATITUDE)) {
                    ListPreference latitudePref = (ListPreference) findPreference(key);
                    Toast.makeText(getActivity(), latitudePref.getValue(), Toast.LENGTH_SHORT).show();

                }
                if (key.equals(PREF_TIME)) {
                    ListPreference timePref = (ListPreference) findPreference(key);
                    Toast.makeText(getActivity(), timePref.getValue(), Toast.LENGTH_SHORT).show();

                }
            }
        };

    }

    @Override
    public void onResume() {
        super.onResume();

        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(preferenceChangeListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
    }
}