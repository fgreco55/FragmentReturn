package com.example.fragmentreturn

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import android.view.KeyEvent
import android.widget.EditText

class Fragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("Frank", "Insdie onCreateView... about to inflate")
        // Inflate the layout for this fragment
        var myroot = inflater.inflate(R.layout.fragment_2, container, false)

        var userinput = getArguments()
        var s: String = userinput!!.getString("activity_input", "DUMMY")
        var f2text: EditText = myroot!!.findViewById(R.id.frag2_edittext)

        Log.i("Frank", "Inside onCreateView()... about to setText() with [" + s + "]")
        f2text.setText(s)

        f2text.setOnKeyListener(object: View.OnKeyListener {
            override fun onKey(myview: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                    var s = f2text.text.toString()
                    Log.i("Frank", "<CR> entered - $s")

                    val myIntent = Intent(activity?.applicationContext, MainActivity::class.java)
                    myIntent.putExtra("fragment_data", s)
                    startActivity(myIntent)

                    return true
                } else
                    return false
            }
        })

        return myroot
    }
}