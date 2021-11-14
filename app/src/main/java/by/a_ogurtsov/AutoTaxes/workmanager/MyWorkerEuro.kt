package by.a_ogurtsov.AutoTaxes.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import by.a_ogurtsov.AutoTaxes.euroRate
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


class MyWorkerEuro(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    private lateinit var euroValue: String

    override fun doWork(): Result {

        val document: Document
        var outputData: Data
        Log.d(LogTAG, "startMyWorkerEuro")

        try {
            document = Jsoup.connect(URLMINFIN).get()
            val element: Elements = document.getElementsByClass("h1")
            euroValue = element[0].text()
            outputData = workDataOf("EuroRate" to euroValue)

            euroRate = euroValue

        } catch (e: Exception) {
            Log.d(LogTAG, "something wrong with MinFinSite")
            outputData = workDataOf("EuroRate" to "something wrong with MinFinSite")
        }
        Log.d(LogTAG, euroValue)
        Log.d(LogTAG, "stopMyWorkerEuro")

        return Result.success(outputData)
    }

    companion object {
        const val URLMINFIN = "https://myfin.by/bank/kursy_valjut_nbrb/eur"
        const val LogTAG = "workmng"
    }
}
