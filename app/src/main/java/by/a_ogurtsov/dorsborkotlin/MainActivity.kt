package by.a_ogurtsov.dorsborkotlin

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var model: MyViewModel
    private lateinit var color: String // color of theme


    val LOG_TAG = "myLogs"
    val FRAGMENTSTART = "FRAGMENT_START"
    val FRAGMENTDORSBOR = "FRAGMENT_DORSBOR"
    val FRAGMENTUTILSBOR = "FRAGMENT_UTILSBOR"
    val FRAGMENTSETTINGS = "FRAGMENT_SETTINGS"
    val CURRENTFRAGMENT = "CURRENT_FRAGMENT"
    val CURRENTFRAGMENT_PRESS_ARROW_BACK = "CURRENTFRAGMENT_PRESS_ARROW_BACK"



    private val fragmentManager: FragmentManager = supportFragmentManager



    private lateinit var toolbar: Toolbar
    private lateinit var drawer: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTheme()
        setContentView(R.layout.activity_main)
        initViewModel()
        initToolbar()
        initDrawerLayout()
        initFragment()
    }

    fun initViewModel() {

        model = ViewModelProviders.of(this).get(MyViewModel::class.java)
        val observerColorTheme = Observer<String> { newColor ->
            this.color = newColor

            intent.putExtra(CURRENTFRAGMENT, FRAGMENTSETTINGS)
            finish()
            startActivity(intent)
        }

        model.currentColor.observe(this, observerColorTheme)

        val observerPressButtonFromFragmentStart = Observer<String> { choice_from_fragmentStart ->

            when (choice_from_fragmentStart) {
                "FRAGMENT_DORSBOR" -> {                                        // pressed button дорожный сбор from startfragment
                    setTitle(R.string.titleDorSbor)
                   // val fragmentManager: FragmentManager = supportFragmentManager
                    val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                    val fragmentDorSbor: FragmentDorSbor = FragmentDorSbor().newInstance(color)
                    fragmentTransaction.replace(R.id.container, fragmentDorSbor, FRAGMENTDORSBOR).commit()
                    intent.putExtra(CURRENTFRAGMENT, FRAGMENTDORSBOR)
                    intent.putExtra(CURRENTFRAGMENT_PRESS_ARROW_BACK, FRAGMENTDORSBOR)
                    invalidateOptionsMenu()   // update menu

                }
                "FRAGMENT_UTILSBOR" -> {                                       // pressed button утилизационный сбор from startfragment
                    setTitle(R.string.titleUtilSbor)
                    //val fragmentManager: FragmentManager = supportFragmentManager
                    val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                    val fragmentUtilSbor = FragmentUtilSbor()
                    fragmentTransaction.replace(R.id.container, fragmentUtilSbor, FRAGMENTUTILSBOR).commit()
                    intent.putExtra(CURRENTFRAGMENT, FRAGMENTUTILSBOR)
                    intent.putExtra(CURRENTFRAGMENT_PRESS_ARROW_BACK, FRAGMENTUTILSBOR)
                    invalidateOptionsMenu()   // update menu

                }
            }
        }
        model.choice_from_fragmentStart.observe(this, observerPressButtonFromFragmentStart)


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
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    fun initDrawerLayout() {
        drawer = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener(this)
        toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.nav_app_bar_open_drawer_description, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    fun initFragment() {

        //val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragmentStart = FragmentStart()
        val fragmentDorSbor: FragmentDorSbor = FragmentDorSbor().newInstance(color)
        val fragmentSetting = FragmentSetting()

        if (intent.getStringExtra(CURRENTFRAGMENT) == null) {                              //добавляется StartFragment в первый раз
            setTitle(R.string.app_name)
            fragmentTransaction.add(R.id.container, fragmentStart, FRAGMENTSTART).commit()
            intent.putExtra(CURRENTFRAGMENT, FRAGMENTSTART)
            intent.putExtra(CURRENTFRAGMENT_PRESS_ARROW_BACK, FRAGMENTSTART)


        } else if (intent.getStringExtra(CURRENTFRAGMENT) == FRAGMENTSETTINGS) {           //добавляется SettingFragment
            setTitle(R.string.SETTING)
            fragmentTransaction.replace(R.id.container, fragmentSetting, FRAGMENTSETTINGS).commit()
            closeDrawer_initToolbar()
            invalidateOptionsMenu()   // update menu


        } else if (intent.getStringExtra(CURRENTFRAGMENT) == FRAGMENTDORSBOR) {
            setTitle(R.string.titleDorSbor)                                                 //добавляется DorSborFragment
            fragmentTransaction.replace(R.id.container, fragmentDorSbor, FRAGMENTDORSBOR).commit()
            intent.putExtra(CURRENTFRAGMENT, FRAGMENTDORSBOR)
            intent.putExtra(CURRENTFRAGMENT_PRESS_ARROW_BACK, FRAGMENTDORSBOR)
            invalidateOptionsMenu()   // update menu

        } else if (intent.getStringExtra(CURRENTFRAGMENT) == FRAGMENTSTART) {                //добавляется СтартFragment при повороте экрана
            setTitle(R.string.app_name)
            fragmentTransaction.replace(R.id.container, fragmentStart, FRAGMENTSTART).commit()
            intent.putExtra(CURRENTFRAGMENT, FRAGMENTSTART)
            intent.putExtra(CURRENTFRAGMENT_PRESS_ARROW_BACK, FRAGMENTSTART)
            invalidateOptionsMenu()
        } // update menu
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        when (intent.getStringExtra(CURRENTFRAGMENT)) {
            null, FRAGMENTDORSBOR, FRAGMENTSTART, FRAGMENTUTILSBOR ->
                menu?.findItem(R.id.menu_setting)!!.isVisible = true
            FRAGMENTSETTINGS -> menu?.findItem(R.id.menu_setting)!!.isVisible = false
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        //val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        when (item?.itemId) {
                                                                                    // нажатие кнопки "настройки"
            R.id.menu_setting -> {
                setTitle(R.string.SETTING)
                val fragmentSetting = FragmentSetting()
                fragmentTransaction.replace(R.id.container, fragmentSetting, FRAGMENTSETTINGS).commit()

                closeDrawer_initToolbar()    // inherit fun

                intent.putExtra(CURRENTFRAGMENT, FRAGMENTSETTINGS)
                invalidateOptionsMenu()   // update menu
            }
            // нажатие стрелки назад
            android.R.id.home -> {
                Log.d(LOG_TAG, "Press back button")

                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)            // on drawer layout
                initDrawerLayout()

                when (intent.getStringExtra(CURRENTFRAGMENT_PRESS_ARROW_BACK)) {
                    FRAGMENTDORSBOR -> {
                        setTitle(R.string.titleDorSbor)
                        val fragmentDorSbor = FragmentDorSbor()
                        fragmentTransaction.replace(R.id.container, fragmentDorSbor.newInstance(color), FRAGMENTDORSBOR)
                            .commit()
                        intent.putExtra(CURRENTFRAGMENT, FRAGMENTDORSBOR)
                        invalidateOptionsMenu()   // update menu
                    }//->
                    FRAGMENTSTART -> {
                        setTitle(R.string.app_name)
                        val fragmentStart = FragmentStart()
                        fragmentTransaction.replace(R.id.container, fragmentStart, FRAGMENTSTART).commit()
                        intent.putExtra(CURRENTFRAGMENT, FRAGMENTSTART)
                        invalidateOptionsMenu()   // update menu
                    }
                    FRAGMENTUTILSBOR -> {
                        setTitle(R.string.titleUtilSbor)
                        val fragmentUtilSbor = FragmentUtilSbor()
                        fragmentTransaction.replace(R.id.container, fragmentUtilSbor, FRAGMENTUTILSBOR).commit()
                        intent.putExtra(CURRENTFRAGMENT, FRAGMENTUTILSBOR)
                        invalidateOptionsMenu()   // update menu
                    }
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

    override fun setTitle(res_title: Int) {
        supportActionBar?.setTitle(resources.getString(res_title))
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        when (p0.itemId) {
            R.id.navigation_view_item_dor_sbor -> {
                setTitle(R.string.titleDorSbor)
                //val fragmentManager: FragmentManager = supportFragmentManager
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                val fragmentDorSbor: FragmentDorSbor = FragmentDorSbor().newInstance(color)
                fragmentTransaction.replace(R.id.container, fragmentDorSbor, FRAGMENTDORSBOR).commit()
                intent.putExtra(CURRENTFRAGMENT, FRAGMENTDORSBOR)
                intent.putExtra(CURRENTFRAGMENT_PRESS_ARROW_BACK, FRAGMENTDORSBOR)
                invalidateOptionsMenu()   // update menu

            }
            R.id.navigation_view_item_util_sbor -> {
                setTitle(R.string.titleUtilSbor)
                //val fragmentManager: FragmentManager = supportFragmentManager
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                val fragmentUtilSbor = FragmentUtilSbor()
                fragmentTransaction.replace(R.id.container, fragmentUtilSbor, FRAGMENTUTILSBOR).commit()
                intent.putExtra(CURRENTFRAGMENT, FRAGMENTUTILSBOR)
                intent.putExtra(CURRENTFRAGMENT_PRESS_ARROW_BACK, FRAGMENTUTILSBOR)
                invalidateOptionsMenu()   // update menu
            }
        }
        drawer.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else
            super.onBackPressed()
    }

    fun closeDrawer_initToolbar() {
        initToolbar()                                                        // pass toolbar to ActionBar
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)       // off drawer layout
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)     // set arrow back
    }

}
