package by.a_ogurtsov.AutoTaxes.strahovka.location

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class ContractActivityStrahovkaLocation : ActivityResultContract<Unit, Intent?>() {

    override fun createIntent(context: Context, input: Unit?): Intent {
        return Intent(context, ActivityStrahovkaLocation::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Intent? {
        if (resultCode != Activity.RESULT_OK) return null
        return intent
    }

}