package by.a_ogurtsov.AutoTaxes.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


class MyWorkerDollar(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    private lateinit var dollarValue: String

    override fun doWork(): Result {

        val document: Document
        var outputData: Data
        Log.d(LogTAG, "startMyWorkerDollar")

        try {
            document = Jsoup.connect(URLMINFIN).get()
          //val element: Elements = document.getElementsByClass("h1")
            val element: Elements = document.getElementsByClass("currency-detailed-change-card__value")
            dollarValue = element[0].text()
            outputData = workDataOf("DollarRate" to dollarValue)

        } catch (e: Exception) {
            Log.d(LogTAG, "something wrong with MinFinSite")
            outputData = workDataOf("DollarRate" to "something wrong with MinFinSite")
        }
        Log.d(LogTAG, dollarValue)
        Log.d(LogTAG, "stopMyWorkerDollar")

        return Result.success(outputData)
    }

    companion object {
      //const val URLMINFIN = "https://myfin.by/bank/kursy_valjut_nbrb/usd"
        const val URLMINFIN = "https://myfin.by/currency/torgi-na-bvfb/kurs-dollara"
        const val LogTAG = "workmng"
    }
}
