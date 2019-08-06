package by.a_ogurtsov.dorsborkotlin

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager


class MainActivity : AppCompatActivity() {

    private lateinit var model: MyViewModel
    private lateinit var color: String // color of theme
    val LOG_TAG = "myLogs"
    val FRAGMENTMAIN = "FRAGMENT_MAIN"
    val FRAGMENTSETTINGS = "FRAGMENT_SETTINGS"
    val CURRENTFRAGMENT = "CURRENT_FRAGMENT"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTheme()
        setContentView(R.layout.activity_main)
        initViewModel()
        initToolbar()
        initFragment()

    }

    fun initViewModel() {

        model = ViewModelProviders.of(this).get(MyViewModel::class.java)
        val nameObserver = Observer<String> { newColor ->
            this.color = newColor

            intent.putExtra(CURRENTFRAGMENT, FRAGMENTSETTINGS)
            finish()
            startActivity(intent)
        }

        model.currentColor.observe(this, nameObserver)
    }

    fun initTheme() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        when (sharedPreferences != null) {
            true -> color = sharedPreferences.getString("pref_color_theme", "")
            false -> color = "водяная"
        }
        setAppTheme(color)
    }

    fun initToolbar() {

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

    }


    fun initFragment() {

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragmentMain: FragmentMain = FragmentMain().newInstance(color)
        val fragmentSetting: FragmentSetting = FragmentSetting()

        if (intent.getStringExtra(CURRENTFRAGMENT) == null) {                              //добавляется MainFragment в первый раз
            fragmentTransaction.add(R.id.container, fragmentMain, FRAGMENTMAIN)
            fragmentTransaction.commit()
            intent.putExtra(CURRENTFRAGMENT, FRAGMENTMAIN)


        } else if (intent.getStringExtra(CURRENTFRAGMENT) == FRAGMENTSETTINGS) {           //добавляется SettingFragment
            fragmentTransaction.replace(R.id.container, fragmentSetting, FRAGMENTSETTINGS)
            fragmentTransaction.commit()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setTitle(resources.getString(R.string.SETTING))
            invalidateOptionsMenu()   // update menu


        } else if (intent.getStringExtra(CURRENTFRAGMENT) == FRAGMENTMAIN) {                //добавляется MainFragment
            fragmentTransaction.replace(R.id.container, fragmentMain, FRAGMENTMAIN)
            fragmentTransaction.commit()
            intent.putExtra(CURRENTFRAGMENT, FRAGMENTMAIN)
            invalidateOptionsMenu()   // update menu

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        when (intent.getStringExtra(CURRENTFRAGMENT)) {
            null, FRAGMENTMAIN -> menu?.findItem(R.id.menu_setting)!!.isVisible = true
            FRAGMENTSETTINGS -> menu?.findItem(R.id.menu_setting)!!.isVisible = false
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        when (item?.itemId) {

            // нажатие кнопки "настройки"
            R.id.menu_setting -> {
                val fragmentSetting: FragmentSetting = FragmentSetting()
                fragmentTransaction.replace(R.id.container, fragmentSetting, FRAGMENTSETTINGS)
                fragmentTransaction.commit()
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setTitle(resources.getString(R.string.SETTING))
                intent.putExtra(CURRENTFRAGMENT, FRAGMENTSETTINGS)
                invalidateOptionsMenu()   // update menu

            }

            // нажатие стрелки назад
            android.R.id.home -> {
                val fragmentManager: FragmentManager = supportFragmentManager
                val fragment: Fragment = fragmentManager.findFragmentByTag(FRAGMENTSETTINGS)!!

                // если будут еще фрагменты здесь можно их указать

                when (fragment.tag) {
                    FRAGMENTSETTINGS -> {
                        val fragmentMain: FragmentMain = FragmentMain()
                        fragmentTransaction.replace(R.id.container, fragmentMain.newInstance(color), FRAGMENTMAIN)
                        fragmentTransaction.commit()
                        supportActionBar?.setDisplayHomeAsUpEnabled(false)
                        supportActionBar?.setTitle(resources.getString(R.string.app_name))
                        intent.putExtra(CURRENTFRAGMENT, FRAGMENTMAIN)
                        invalidateOptionsMenu()   // update menu
                    }//->
                } //when
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setAppTheme(color: String) {

        when (color) {
            "водяная" -> setTheme(R.style.AppThemeWater)
            "оливковая" -> setTheme(R.style.AppThemeOlive)
            "снежная" -> setTheme(R.style.AppThemeSnow)
            "морковная" -> setTheme(R.style.AppThemeCarrot)
            "медовая" -> setTheme(R.style.AppThemeHoney)
        }

    }
}
