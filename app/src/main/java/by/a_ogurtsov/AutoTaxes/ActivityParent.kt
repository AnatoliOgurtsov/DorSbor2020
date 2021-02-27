package by.a_ogurtsov.AutoTaxes

import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager

open class ActivityParent : AppCompatActivity(){
  private lateinit var themeColor: String // color of theme

  fun initTheme() {
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
    when (sharedPreferences != null) {
      true -> themeColor = sharedPreferences.getString("pref_color_theme", "").toString()
      false -> themeColor = "оливковая"
    }
    setAppTheme(themeColor)
  }

  fun setAppTheme(color: String) {
    when (color) {
      "водяная" -> setTheme(R.style.AppThemeWaterDialog)
      "оливковая" -> setTheme(R.style.AppThemeOliveDialog)
      "снежная" -> setTheme(R.style.AppThemeSnowDialog)
      "морковная" -> setTheme(R.style.AppThemeCarrotDialog)
      "медовая" -> setTheme(R.style.AppThemeHoneyDialog)
    }
  }
}