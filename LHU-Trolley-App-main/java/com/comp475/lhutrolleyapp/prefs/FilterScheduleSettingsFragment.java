package com.comp475.lhutrolleyapp.prefs;

import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.comp475.lhutrolleyapp.R;
import com.comp475.lhutrolleyapp.util.ResetOptionsDialogFragment;

public class FilterScheduleSettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.filter_schedule_settings, rootKey);

        // This ListPreference is only temporary. Once filter parameters are agreed upon,
        // a ListPreference needs to be created for each parameter, and the following code
        // must be executed for each ListPreference to set it up:
        ListPreference listPref = getPreferenceScreen().findPreference("list_preference");
        listPref.setSummary(listPref.getEntry());
        listPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                ListPreference pref = (ListPreference) preference;
                int id = 0;
                for (int i = 0; i < pref.getEntryValues().length; i++) {
                    if (pref.getEntryValues()[i].equals(newValue.toString())) {
                        id = i;
                        break;
                    }
                }
                preference.setSummary(pref.getEntries()[id]);
                return true;
            }
        });

        Preference reset = getPreferenceScreen().findPreference(getString(R.string.reset_schedule_key));
        reset.setOnPreferenceClickListener(preference -> {
            ResetOptionsDialogFragment dialogFragment = new ResetOptionsDialogFragment(new ResetOptionsDialogFragment.NoticeDialogListener() {
                @Override
                public void onDialogPositiveClicked() {
                    resetPreferences();
                }

                @Override
                public void onDialogNegativeClicked() {
                    // Do nothing
                }
            });

            dialogFragment.show(getChildFragmentManager(), ResetOptionsDialogFragment.TAG);

            return true;
        });
    }

    private void resetPreferences() {
        // Just like above, this is just the temporary ListPreference, and this will need to be
        // executed for each parameter's ListPreference:
        ListPreference listPref = getPreferenceScreen().findPreference("list_preference");
        if (listPref != null) {
            listPref.setValueIndex(0);
            listPref.setSummary(listPref.getEntries()[0]);
        }
    }
} 
