package by.a_ogurtsov.AutoTaxes.strahovka.autoKind

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import by.a_ogurtsov.AutoTaxes.strahovka.FragmentStrahovka

class ContractActivityStrahovkaAutoKind : ActivityResultContract<Unit, String>(){

    override fun createIntent(context: Context, input: Unit?): Intent {
       return Intent(context, ActivityStrahovkaAutoKind::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        if (resultCode != Activity.RESULT_OK) return null
        return intent?.extras?.getString(FragmentStrahovka.RESULT_AUTO_KIND, "")
    }
}