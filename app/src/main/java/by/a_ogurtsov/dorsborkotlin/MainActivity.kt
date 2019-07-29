package by.a_ogurtsov.dorsborkotlin

import android.os.Bundle
import android.util.Log
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
    val LOG_TAG = "myLogs"
    private lateinit var color: String // color of theme


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        when (sharedPreferences != null) {
            true -> color = sharedPreferences.getString("pref_color_theme", "")
            false -> color = "standart"
        }

        initTheme()
        initToolbar()
        initFragment(savedInstanceState)
        initViewModel()
    }

    fun initViewModel() {

        model = ViewModelProviders.of(this).get(MyViewModel::class.java)
        val nameObserver = Observer<String> { newColor ->
            this.color = newColor
        }

        model.currentColor.observe(this, nameObserver)
    }

    fun initTheme() {

        setTheme(R.style.AppTheme_GREEN)
    }

    fun initToolbar() {

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

    }


    fun initFragment(savedInstanceState: Bundle?) {

        val fragmentManager: FragmentManager = supportFragmentManager;
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        var fragmentMain: FragmentMain = FragmentMain().newInstance(color)

        if (savedInstanceState == null) {
            fragmentTransaction.add(R.id.container, fragmentMain, "FRAGMENT_MAIN")
            fragmentTransaction.commit()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        when (item?.itemId) {

            // нажатие кнопки "настройки"
            R.id.menu_setting -> {
                val fragmentSetting: FragmentSetting = FragmentSetting()
                fragmentTransaction.replace(R.id.container, fragmentSetting, "FRAGMENT_SETTING")
                fragmentTransaction.commit()
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setTitle(resources.getString(R.string.SETTING))
            }


            // нажатие стрелки назад
            android.R.id.home -> {
                val fragmentManager: FragmentManager = supportFragmentManager
                val fragment: Fragment = fragmentManager.findFragmentByTag("FRAGMENT_SETTING")!!

                // если будут еще фрагменты здесь можно их указать

                when (fragment.tag) {
                    "FRAGMENT_SETTING" -> {
                        val fragmentMain: FragmentMain = FragmentMain()
                        fragmentTransaction.replace(R.id.container, fragmentMain.newInstance(color), "FRAGMENT_MAIN")
                        fragmentTransaction.commit()
                        supportActionBar?.setDisplayHomeAsUpEnabled(false)
                        supportActionBar?.setTitle(resources.getString(R.string.app_name))
                    }//->
                } //when
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
