package by.a_ogurtsov.AutoTaxes.strahovka.autoKind.details

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import by.a_ogurtsov.AutoTaxes.strahovka.FragmentStrahovka

class ContractActivityStrahovkaLegkPricepDetails : ActivityResultContract<Unit, String>(){

    override fun createIntent(context: Context, input: Unit?): Intent {
       return Intent(context, ActivityStrahovkaLegkPricepDetails::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        if (resultCode != Activity.RESULT_OK) return null
        return intent?.extras?.getString(FragmentStrahovka.RESULT_AUTO_KIND_LEGK_PRICEP_DETAILS, "")
    }
}