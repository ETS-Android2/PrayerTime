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

    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefrences);

        preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals(PREF_JURISTIC)) {
                    ListPreference juristicPref = (ListPreference) findPreference(key); //if you need the prefrence key similar to find by ID
                    Toast.makeText(getActivity(),juristicPref.getValue(), Toast.LENGTH_SHORT).show();
                }
                if (key.equals(PREF_CALC)) {
                    Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                }
                if (key.equals(PREF_LATITUDE)) {
                    Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();

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