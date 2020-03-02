package by.a_ogurtsov.AutoTaxes

import android.content.Intent
import android.content.Intent.*
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import by.a_ogurtsov.AutoTaxes.viewModels.MyViewModel
import by.a_ogurtsov.AutoTaxes.workmanager.MyWorker
import com.google.android.material.navigation.NavigationView


class ActivityMain : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

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
    private lateinit var intentEmail: Intent

    private val metrics: DisplayMetrics = DisplayMetrics()
    private var widthScreen: Int =
        0                                      //ширина экрана, передаем во фрагмент как атрибут


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        getSizeButtonLayoutWidth()
        initTheme()
        setContentView(R.layout.activity_main)
        initViewModel()
        initToolbar()
        initDrawerLayout()
        initFragment()
        startEuroRateTask()
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
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(
                        R.id.container,
                        FragmentDorSbor().newInstance(color, widthScreen),
                        FRAGMENTDORSBOR
                    )
                        .commit()
                    intent.putExtra(CURRENTFRAGMENT, FRAGMENTDORSBOR)
                    intent.putExtra(CURRENTFRAGMENT_PRESS_ARROW_BACK, FRAGMENTDORSBOR)
                    invalidateOptionsMenu()   // update menu


                }
                "FRAGMENT_UTILSBOR" -> {                                       // pressed button утилизационный сбор from startfragment
                    setTitle(R.string.titleUtilSbor)
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(
                        R.id.container,
                        FragmentUtilSbor().newInstance(color, widthScreen),
                        FRAGMENTUTILSBOR
                    ).commit()
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
            this,
            drawer,
            toolbar,
            R.string.nav_app_bar_open_drawer_description,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()


        // press email in drawer head

        val view: View = View.inflate(this, R.layout.nav_drawer_header, navigationView)
        val email: TextView = view.findViewById(R.id.nav_header_email)
        val onClickListenerDrawerHead: View.OnClickListener = View.OnClickListener {
            intentEmail = Intent(ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(EXTRA_EMAIL, resources.getStringArray(R.array.email_address))
                putExtra(EXTRA_SUBJECT, "Dear Anatoli, ")
            }
            if (intentEmail.resolveActivity(packageManager) != null)
                startActivity(intentEmail)
        }
        email.setOnClickListener(onClickListenerDrawerHead)
    }

    fun initFragment() {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        if (intent.getStringExtra(CURRENTFRAGMENT) == null) {                              //добавляется StartFragment в первый раз
            setTitle(R.string.app_name)
            fragmentTransaction.add(R.id.container, FragmentStart(), FRAGMENTSTART).commit()
            intent.putExtra(CURRENTFRAGMENT, FRAGMENTSTART)
            intent.putExtra(CURRENTFRAGMENT_PRESS_ARROW_BACK, FRAGMENTSTART)

        } else if (intent.getStringExtra(CURRENTFRAGMENT) == FRAGMENTSETTINGS) {           //добавляется SettingFragment
            setTitle(R.string.SETTING)
            fragmentTransaction.replace(R.id.container, FragmentSetting(), FRAGMENTSETTINGS)
                .commit()
            closeDrawer_initToolbar()
            invalidateOptionsMenu()   // update menu

        } else if (intent.getStringExtra(CURRENTFRAGMENT) == FRAGMENTDORSBOR) {
            setTitle(R.string.titleDorSbor)                                                 //добавляется DorSborFragment
            fragmentTransaction.replace(
                R.id.container,
                FragmentDorSbor().newInstance(color, widthScreen),
                FRAGMENTDORSBOR
            ).commit()
            intent.putExtra(CURRENTFRAGMENT, FRAGMENTDORSBOR)
            intent.putExtra(CURRENTFRAGMENT_PRESS_ARROW_BACK, FRAGMENTDORSBOR)
            invalidateOptionsMenu()   // update menu

        } else if (intent.getStringExtra(CURRENTFRAGMENT) == FRAGMENTSTART) {                //добавляется СтартFragment при повороте экрана
            setTitle(R.string.app_name)
            fragmentTransaction.replace(R.id.container, FragmentStart(), FRAGMENTSTART).commit()
            intent.putExtra(CURRENTFRAGMENT, FRAGMENTSTART)
            intent.putExtra(CURRENTFRAGMENT_PRESS_ARROW_BACK, FRAGMENTSTART)
            invalidateOptionsMenu()     // update menu
        }
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
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        when (item?.itemId) {
            // нажатие кнопки "настройки"
            R.id.menu_setting -> {
                setTitle(R.string.SETTING)
                fragmentTransaction.replace(R.id.container, FragmentSetting(), FRAGMENTSETTINGS)
                    .commit()

                closeDrawer_initToolbar()    // inherit fun
                intent.putExtra(CURRENTFRAGMENT, FRAGMENTSETTINGS)
                invalidateOptionsMenu()   // update menu
            }
            // нажатие стрелки назад
            android.R.id.home -> {
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)            // on drawer layout
                initDrawerLayout()
                onBackPressedAndBackArrowPressed()
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
        supportActionBar?.title = resources.getString(res_title)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        when (p0.itemId) {
            R.id.navigation_view_item_dor_sbor -> {
                setTitle(R.string.titleDorSbor)
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(
                    R.id.container,
                    FragmentDorSbor().newInstance(color, widthScreen),
                    FRAGMENTDORSBOR
                )
                    .commit()
                intent.putExtra(CURRENTFRAGMENT, FRAGMENTDORSBOR)
                intent.putExtra(CURRENTFRAGMENT_PRESS_ARROW_BACK, FRAGMENTDORSBOR)
                invalidateOptionsMenu()   // update menu

            }
            R.id.navigation_view_item_util_sbor -> {
                setTitle(R.string.titleUtilSbor)
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(
                    R.id.container,
                    FragmentUtilSbor().newInstance(color, widthScreen),
                    FRAGMENTUTILSBOR
                )
                    .commit()
                intent.putExtra(CURRENTFRAGMENT, FRAGMENTUTILSBOR)
                intent.putExtra(CURRENTFRAGMENT_PRESS_ARROW_BACK, FRAGMENTUTILSBOR)
                invalidateOptionsMenu()   // update menu
            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        val fragmentTransaction: FragmentTransaction =
            fragmentManager.beginTransaction()  // close navigationDrawer
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else if (intent.getStringExtra(CURRENTFRAGMENT) == FRAGMENTDORSBOR ||   // если тек. фрагмент или дорсбор или утилсбор то возвращаемся на стартовый фрагмент
            intent.getStringExtra(CURRENTFRAGMENT) == FRAGMENTUTILSBOR
        ) {
            setTitle(R.string.app_name)
            fragmentTransaction.replace(R.id.container, FragmentStart(), FRAGMENTSTART).commit()
            intent.putExtra(CURRENTFRAGMENT, FRAGMENTSTART)
            intent.putExtra(CURRENTFRAGMENT_PRESS_ARROW_BACK, FRAGMENTSTART)
        } else if (intent.getStringExtra(CURRENTFRAGMENT) == FRAGMENTSETTINGS) {

            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)            // on drawer layout
            initDrawerLayout()
            onBackPressedAndBackArrowPressed()
        } else
            super.onBackPressed()
    }

    fun closeDrawer_initToolbar() {
        initToolbar()                                                        // pass toolbar to ActionBar
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)       // off drawer layout
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)     // set arrow back
    }

    fun getSizeButtonLayoutWidth() {   // находим ширина экрана, чтобы выставить размеры кнопок в MaterialToogle

        windowManager.defaultDisplay.getMetrics(this.metrics)
        this.widthScreen = metrics.widthPixels

        Log.d(LOG_TAG, widthScreen.toString())

    }

    fun onBackPressedAndBackArrowPressed() {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        when (intent.getStringExtra(CURRENTFRAGMENT_PRESS_ARROW_BACK)) {
            FRAGMENTDORSBOR -> {
                setTitle(R.string.titleDorSbor)
                fragmentTransaction.replace(
                    R.id.container,
                    FragmentDorSbor().newInstance(color, widthScreen),
                    FRAGMENTDORSBOR
                )
                    .commit()
                intent.putExtra(CURRENTFRAGMENT, FRAGMENTDORSBOR)
                invalidateOptionsMenu()   // update menu
            }//->
            FRAGMENTSTART -> {
                setTitle(R.string.app_name)
                fragmentTransaction.replace(R.id.container, FragmentStart(), FRAGMENTSTART)
                    .commit()
                intent.putExtra(CURRENTFRAGMENT, FRAGMENTSTART)
                invalidateOptionsMenu()   // update menu
            }
            FRAGMENTUTILSBOR -> {
                setTitle(R.string.titleUtilSbor)
                fragmentTransaction.replace(
                    R.id.container,
                    FragmentUtilSbor().newInstance(color, widthScreen),
                    FRAGMENTUTILSBOR
                ).commit()
                intent.putExtra(CURRENTFRAGMENT, FRAGMENTUTILSBOR)
                invalidateOptionsMenu()   // update menu
            }
        } //when
    }

    fun startEuroRateTask() {

        val contrains: Constraints =
            Constraints.Builder()     // вводим ограничения на загрузку задачи без интернета
                .setRequiredNetworkType(NetworkType.CONNECTED)     // WiFi or mobilData
                .build()


        val uploadWorkRequest = OneTimeWorkRequestBuilder<MyWorker>()
            .setConstraints(contrains)
            .addTag("TaskParseringSiteMinfinForEuroRate")
            .build()

        WorkManager.getInstance(applicationContext).enqueue(uploadWorkRequest)
    }

}


