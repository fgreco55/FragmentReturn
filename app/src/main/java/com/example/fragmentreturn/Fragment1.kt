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

class Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("Frank", "Insdie onCreateView... about to inflate")
        // Inflate the layout for this fragment
        var myroot = inflater.inflate(R.layout.fragment_1, container, false)

        var userinput = getArguments()
        var s: String = userinput!!.getString("activity_input", "DUMMY")
        var f1text: EditText = myroot!!.findViewById(R.id.frag1_edittext)

        Log.i("Frank", "Inside onCreateView()... about to setText() with [" + s + "]")
        f1text.setText(s)

        f1text.setOnKeyListener(object: View.OnKeyListener {
            override fun onKey(myview: View?, keyCode: Int, event: KeyEvent): Boolean {
               if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                   var s = f1text.text.toString()
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