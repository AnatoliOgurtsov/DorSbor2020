package by.a_ogurtsov.dorsborkotlin


import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager


class FragmentSetting : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var model: MyViewModel
    val LOG_TAG = "myLogs"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        model = ViewModelProviders.of(this.getActivity()!!).get(MyViewModel::class.java)

    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.fragment_setting)
    }


    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {

        if (key.equals("pref_color_theme")) {
            var pref_color_theme: Preference? = findPreference(key!!)
            var color: String = pref_color_theme!!.summary as String

            Log.d(LOG_TAG, "$color в сеттингах")

            this.model.currentColor.value = color
        }
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences
            .registerOnSharedPreferenceChangeListener(this)

        Log.d(LOG_TAG, "FragmentMain Resumed")

    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "FragmentMain Paused")
        preferenceScreen.sharedPreferences
            .unregisterOnSharedPreferenceChangeListener(this)
    }

}