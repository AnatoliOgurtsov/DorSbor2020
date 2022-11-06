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
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import androidx.work.*
import by.a_ogurtsov.AutoTaxes.dorSbor.FragmentDorSbor21
import by.a_ogurtsov.AutoTaxes.strahovka.FragmentStrahovka
import by.a_ogurtsov.AutoTaxes.strahovka.Location
import by.a_ogurtsov.AutoTaxes.utilSbor.FragmentUtilSbor
import by.a_ogurtsov.AutoTaxes.viewModels.MyViewModel
import by.a_ogurtsov.AutoTaxes.workmanager.MyWorkerDollar
import by.a_ogurtsov.AutoTaxes.workmanager.MyWorkerEuro
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ActivityMain : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var model: MyViewModel
    private lateinit var color: String // color of theme

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

        disableNightTheme()
        getSizeButtonLayoutWidth()
        initTheme()
        setContentView(R.layout.activity_main)
        initViewModel()
        initToolbar()
        initDrawerLayout()
        initFragment()
        startCurrencyRateTask()   // парсит сайт минфина по поводу курса евро
        getLocationListFromJsonStrahovka()
    }

    private fun disableNightTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun initViewModel() {

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
                "FRAGMENT_DORSBOR" -> showFragmentDorSbor()
                "FRAGMENT_UTILSBOR" -> showFragmentUtilSbor()
                "FRAGMENT_STRAHOVKA" -> showFragmentStrahovka()
            }
        }
        model.choiceFromFragmentStart.observe(this, observerPressButtonFromFragmentStart)
    }

    private fun initTheme() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        color = when (sharedPreferences != null) {
            true -> sharedPreferences.getString("pref_color_theme", "").toString()
            false -> "водяная"
        }
        setAppTheme(color)
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun initDrawerLayout() {
        drawer = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener(this)
        val view: View = View.inflate(this, R.layout.nav_drawer_header, navigationView)
        val email = view.findViewById<TextView>(R.id.nav_header_email)
        val textViewAppVersion = view.findViewById<TextView>(R.id.app_name)
        val shareApp = view.findViewById<TextView>(R.id.share_app)

        addAppVersionInHeader(textViewAppVersion)

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

        email.setOnClickListener {
            intentEmail = Intent(ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(EXTRA_EMAIL, resources.getStringArray(R.array.email_address))
                putExtra(EXTRA_SUBJECT, "Dear Anatoli, ")
            }
            if (intentEmail.resolveActivity(packageManager) != null)
                startActivity(intentEmail)
        }

        Log.d(LOG_TAG, "initDrawerLayout")
    }

    private fun initFragment() {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        if (intent.getStringExtra(CURRENTFRAGMENT) == null) {                              //добавляется StartFragment в первый раз
            setTitle(R.string.app_name)
            fragmentTransaction.add(R.id.container, FragmentStart(), FRAGMENTSTART).commit()
            intent.putExtra(CURRENTFRAGMENT, FRAGMENTSTART)
            intent.putExtra(CURRENTFRAGMENT_PRESS_ARROW_BACK, FRAGMENTSTART)

        } else if (intent.getStringExtra(CURRENTFRAGMENT) == FRAGMENTSETTINGS) {           //добавляется SettingFragment
            showFragmentSettings()
        } else if (intent.getStringExtra(CURRENTFRAGMENT) == FRAGMENTDORSBOR) {
            showFragmentDorSbor()                                                    //добавляется DorSborFragment
        } else if (intent.getStringExtra(CURRENTFRAGMENT) == FRAGMENTUTILSBOR) {
            showFragmentUtilSbor()                                                     //добавляется UtilSborFragment
        } else if (intent.getStringExtra(CURRENTFRAGMENT) == FRAGMENTSTRAHOVKA) {
            showFragmentStrahovka()                                                     //добавляется StrahovkaFragment
        } else if (intent.getStringExtra(CURRENTFRAGMENT) == FRAGMENTSTART) {                //добавляется СтартFragment при повороте экрана
            showFragmentStart()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        when (intent.getStringExtra(CURRENTFRAGMENT)) {
            null, FRAGMENTDORSBOR, FRAGMENTSTART, FRAGMENTUTILSBOR, FRAGMENTSTRAHOVKA ->
                menu?.findItem(R.id.menu_setting)!!.isVisible = true
            FRAGMENTSETTINGS -> menu?.findItem(R.id.menu_setting)!!.isVisible = false
        }
        return super.onPrepareOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item?.itemId) {
            // нажатие кнопки "настройки"
            R.id.menu_setting -> showFragmentSettings()


            // нажатие стрелки назад
            android.R.id.home -> {
                onBackPressedAndBackArrowPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setAppTheme(color: String) {
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
            R.id.navigation_view_item_dor_sbor -> showFragmentDorSbor()
            R.id.navigation_view_item_util_sbor -> showFragmentUtilSbor()
            R.id.navigation_view_item_strahovka -> showFragmentStrahovka()
            R.id.rate_app -> startIntentRateApp()
            R.id.share_app -> startIntentShareApp()
        }
        return true
    }

    override fun onBackPressed() {

        if (intent.getStringExtra(CURRENTFRAGMENT) != FRAGMENTSTART) {

            when (intent.getStringExtra(CURRENTFRAGMENT_PRESS_ARROW_BACK)) {
                FRAGMENTSTART -> showFragmentStart()
                FRAGMENTDORSBOR -> showFragmentDorSbor()
                FRAGMENTUTILSBOR -> showFragmentUtilSbor()
                FRAGMENTSTRAHOVKA -> showFragmentStrahovka()
            } //when
        } else super.onBackPressed()
    }

    private fun onBackPressedAndBackArrowPressed() {
        when (intent.getStringExtra(CURRENTFRAGMENT_PRESS_ARROW_BACK)) {
            FRAGMENTSTART -> showFragmentStart()
            FRAGMENTDORSBOR -> showFragmentDorSbor()
            FRAGMENTUTILSBOR -> showFragmentUtilSbor()
            FRAGMENTSTRAHOVKA -> showFragmentStrahovka()
        } //when
    }

    private fun closeDrawer_initToolbar() {
        initToolbar()                                                        // pass toolbar to ActionBar
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)       // off drawer layout
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)     // set arrow back
    }

    private fun getSizeButtonLayoutWidth() {   // находим ширина экрана, чтобы выставить размеры кнопок в MaterialToogle

        windowManager.defaultDisplay.getMetrics(this.metrics)
        this.widthScreen = metrics.widthPixels
        Log.d(LOG_TAG, widthScreen.toString())
    }


    private fun showFragmentStart() {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        setTitle(R.string.app_name)
        fragmentTransaction.replace(R.id.container, FragmentStart(), FRAGMENTSTART)
            .commit()
        intent.putExtra(CURRENTFRAGMENT, FRAGMENTSTART)
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)            // on drawer layout
        // initDrawerLayout()
        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.nav_app_bar_open_drawer_description,
            R.string.navigation_drawer_close
        )


        drawer.addDrawerListener(toggle)
        toggle.syncState()

        invalidateOptionsMenu()   // update menu
    }

    private fun showFragmentDorSbor() {

        setTitle(R.string.labelIconName)
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.container,
            FragmentDorSbor21().newInstance(color, widthScreen),
            FRAGMENTDORSBOR
        )
            .commit()
        intent.putExtra(CURRENTFRAGMENT, FRAGMENTDORSBOR)
        intent.putExtra(CURRENTFRAGMENT_PRESS_ARROW_BACK, FRAGMENTSTART)
        closeDrawer_initToolbar()    // inherit fun
        invalidateOptionsMenu()   // update menu
    }

    private fun showFragmentUtilSbor() {

        setTitle(R.string.titleUtilSbor)
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.container,
            FragmentUtilSbor().newInstance(color, widthScreen),
            FRAGMENTUTILSBOR
        ).commit()
        intent.putExtra(CURRENTFRAGMENT, FRAGMENTUTILSBOR)
        intent.putExtra(CURRENTFRAGMENT_PRESS_ARROW_BACK, FRAGMENTSTART)
        closeDrawer_initToolbar()    // inherit fun
        invalidateOptionsMenu()   // update menu
    }

    private fun showFragmentStrahovka() {

        setTitle(R.string.titleStrahovka)
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.container,
            FragmentStrahovka.newInstance(color, widthScreen),
            FRAGMENTSTRAHOVKA
        ).commit()
        intent.putExtra(CURRENTFRAGMENT, FRAGMENTSTRAHOVKA)
        intent.putExtra(CURRENTFRAGMENT_PRESS_ARROW_BACK, FRAGMENTSTART)
        closeDrawer_initToolbar()    // inherit fun
        invalidateOptionsMenu()   // update menu
    }


    private fun showFragmentSettings() {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        setTitle(R.string.SETTING)
        fragmentTransaction.replace(R.id.container, FragmentSetting(), FRAGMENTSETTINGS)
            .commit()

        closeDrawer_initToolbar()    // inherit fun

        if (intent.getStringExtra(CURRENTFRAGMENT) != FRAGMENTSETTINGS) {
            intent.putExtra(
                CURRENTFRAGMENT_PRESS_ARROW_BACK,
                intent.getStringExtra(CURRENTFRAGMENT)
            )
        }
        intent.putExtra(CURRENTFRAGMENT, FRAGMENTSETTINGS)
        invalidateOptionsMenu()   // update menu

    }

    private fun startCurrencyRateTask() {
        val LogTAG = "workmng"
        val wm = WorkManager.getInstance(applicationContext)
        val contrains: Constraints =
            Constraints.Builder()     // вводим ограничения на загрузку задачи без интернета
                .setRequiredNetworkType(NetworkType.CONNECTED)     // WiFi or mobilData
                .build()


        val uploadWorkRequestEuro = OneTimeWorkRequestBuilder<MyWorkerEuro>()
            .setConstraints(contrains)
            .addTag("TaskParseringSiteMinfinForEuroRate")
            .build()

        val uploadWorkRequestDollar = OneTimeWorkRequestBuilder<MyWorkerDollar>()
            .setConstraints(contrains)
            .addTag("TaskParseringSiteMinfinForDollarRate")
            .build()

// observer for a WorkManager State  and recieve data(euro Rate)
        wm.getWorkInfoByIdLiveData(uploadWorkRequestEuro.id)
            .observe(this, Observer { workInfo ->

                if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                    /*   Log.d(LogTAG, "state is ${workInfo.state}")*/
                    /*Log.d(LogTAG, "outputData is ${workInfo.outputData.getString("EuroRate")}")*/
                    model.euroRate.value = workInfo.outputData.getString("EuroRate")
                }
            })
//=======================

        // observer for a WorkManager State  and recieve data(dollar Rate)
        wm.getWorkInfoByIdLiveData(uploadWorkRequestDollar.id)
            .observe(this, Observer { workInfo ->

                if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                    /*   Log.d(LogTAG, "state is ${workInfo.state}")*/
                    /*Log.d(LogTAG, "outputData is ${workInfo.outputData.getString("DollarRate")}")*/
                    model.dollarRate.value = workInfo.outputData.getString("DollarRate")
                }
            })
//=======================
        wm.enqueue(listOf(uploadWorkRequestEuro, uploadWorkRequestDollar))
    }

    private fun startIntentRateApp() {

        val appPackageName = packageName
        val intent = Intent(ACTION_VIEW, Uri.parse("market://details?id=$appPackageName"))
        startActivity(intent)
    }

    private fun startIntentShareApp() {

        val appPackageName = packageName

        val intent = Intent().apply {
            action = ACTION_SEND
            putExtra(EXTRA_TEXT, "https://play.google.com/store/apps/details?id=$appPackageName" )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)
    }

    // add appversion in header
    private fun addAppVersionInHeader(textView: TextView) {
        textView.text = "${getString(R.string.app_name)} ${
            packageManager.getPackageInfo(
                packageName,
                0
            ).versionName
        }"
    }

    private fun getLocationListFromJsonStrahovka() {   //getting list of cities from json for strahovki
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val jsonFileString = getJsonDataFromAsset(applicationContext, "cities.json")
            val json = Gson()
            val location = json.fromJson(jsonFileString, Location::class.java)
            listLocationsStrahovkaUtils = location.cities
        }
    }

}


