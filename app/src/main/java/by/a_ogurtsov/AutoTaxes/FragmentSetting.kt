package by.a_ogurtsov.AutoTaxes


import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import by.a_ogurtsov.AutoTaxes.viewModels.MyViewModel


class FragmentSetting : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var model: MyViewModel
    val LOG_TAG = "myLogs"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = ViewModelProviders.of(this.getActivity()!!).get(MyViewModel::class.java)

    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.fragment_setting)
    }


    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {

        if (key.equals("pref_color_theme")) {
            var pref_color_theme: Preference? = findPreference(key!!)
            var color: String = pref_color_theme!!.summary as String

            this.model.currentColor.value = color

        }
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences
            .registerOnSharedPreferenceChangeListener(this)


    }

    override fun onPause() {
        super.onPause()

        preferenceScreen.sharedPreferences
            .unregisterOnSharedPreferenceChangeListener(this)
    }
}