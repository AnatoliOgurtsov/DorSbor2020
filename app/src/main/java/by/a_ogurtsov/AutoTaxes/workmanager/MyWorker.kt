package by.a_ogurtsov.AutoTaxes.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    val LogTAG: String = "workmng"
    lateinit var document: Document
    lateinit var euroValue: String

    override fun doWork(): Result {

        Log.d(LogTAG, "start")

        try {
            document = Jsoup.connect("https://myfin.by/bank/kursy_valjut_nbrb/eur").get()
        } catch (e: Exception) {
            Log.d(LogTAG, "something wrong with MinFinSite")
        }
        val element: Elements = document.getElementsByClass("h1")
        euroValue = element.text()

        Log.d(LogTAG, euroValue)

        return Result.success()
    }

}
