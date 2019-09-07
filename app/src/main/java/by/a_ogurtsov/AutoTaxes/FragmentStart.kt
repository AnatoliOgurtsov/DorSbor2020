package by.a_ogurtsov.AutoTaxes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

class FragmentStart : Fragment(), View.OnClickListener {

    val LOG_TAG = "myLogs"
    private lateinit var model: MyViewModel
    val FRAGMENTDORSBOR = "FRAGMENT_DORSBOR"
    val FRAGMENTUTILSBOR = "FRAGMENT_UTILSBOR"
    private lateinit var buttonDorSbor: Button
    private lateinit var buttonUtilSbor: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProviders.of(this.getActivity()!!).get(MyViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_start, container, false)
        buttonDorSbor = view.findViewById(R.id.button_open_frag_dor_sbor)
        buttonUtilSbor = view.findViewById(R.id.button_open_frag_util_sbor)
        buttonDorSbor.setOnClickListener(this)
        buttonUtilSbor.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.button_open_frag_dor_sbor -> {
                Log.d(LOG_TAG, "pressed DorsborKey")
                model.choice_from_fragmentStart.value = FRAGMENTDORSBOR
            }
            R.id.button_open_frag_util_sbor -> {
                Log.d(LOG_TAG, "pressed UtilsborKey")
                model.choice_from_fragmentStart.value = FRAGMENTUTILSBOR
            }
        }
    }
}