package by.a_ogurtsov.dorsborkotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment


class FragmentMain : Fragment() {

    private val color: String = "привет"


    fun newInstance(param1: String): FragmentMain {
        val fragment = FragmentMain()
        val args = Bundle()
        args.putString(color, param1)
        fragment.arguments = args
        return fragment
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_main, container, false)


        val button: Button = view.findViewById(R.id.button)
        val textView: TextView = view.findViewById(R.id.textView)

        val onClickListener: View.OnClickListener = View.OnClickListener {
            textView.text = arguments!!.getString(color)
        }
        button.setOnClickListener(onClickListener)

        return view
    }
}
