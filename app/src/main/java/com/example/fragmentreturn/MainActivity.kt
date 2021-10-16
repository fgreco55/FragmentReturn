package com.example.fragmentreturn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.fragmentreturn.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var frankbinding: ActivityMainBinding
    private lateinit var fglayout: FrameLayout
    private lateinit var frag1Button: Button
    private lateinit var frag2Button: Button
    private lateinit var activityInput: EditText

    /*
     ================================================================
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // read the views for this activity into memory
        frankbinding = ActivityMainBinding.inflate(this.layoutInflater)

        // show the activity with all its views
        setContentView(frankbinding.root)

        // some convenience variables so I don't have to type the long name...
        frag1Button = frankbinding.frag1Button
        frag2Button = frankbinding.frag2Button
        fglayout = frankbinding.targetLayout
        activityInput = frankbinding.dataEdittext

        // Just want to separate the callbacks in a separate area
        addCallbacks()
    }

    // ================================================================
    override fun onResume() {
        super.onResume()

        //var myintent = intent
        val s = intent.getStringExtra("fragment_data")

        if (s != null) {
            Log.i("Frank", "ACTIVITY string: $s")
            activityInput.setText(s)
        } else {
            Log.i("Frank", "ERROR... its null")
        }
    }

    // ================================================================
    private fun addCallbacks() {
        // Set the clickListener on the buttons =======================
        frag1Button.setOnClickListener {
            Log.i("Frank", "=====Frag1 onClick called [" + activityInput.getText().toString() + "]")
            val mydata = Bundle()    // create a Bundle to send to Fragment 1
            mydata.putString("activity_input", activityInput.getText().toString())
            Log.i("Frank", "about to replaceFragment()")
            replaceFragment(Fragment1(), mydata)
        }

        frag2Button.setOnClickListener {
            Log.i("Frank", "-----Frag2 onClick called")
            val mydata = Bundle()     // create a Bundle to send to Fragment 2
            mydata.putString("activity_input", activityInput.getText().toString())
            replaceFragment(Fragment2(), mydata)
        }
    }

    // ================================================================
    private fun replaceFragment(frag: Fragment, mybundle: Bundle) {
        frag.setArguments(mybundle)     // attach the bundle to this frag
        Log.i("Frank", "Bundle string [" + mybundle.getString("activity_input") + "]")

        val fragmanager: FragmentManager = getSupportFragmentManager()

        // replacing a fragment in an Activity requires a transaction... frank
        val fragtran: FragmentTransaction = fragmanager.beginTransaction()
        fragtran.replace(R.id.targetLayout, frag)
        fragtran.commit()

    }
}