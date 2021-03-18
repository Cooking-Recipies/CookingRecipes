package com.example.cookingrecipes.ui.YourProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cookingrecipes.R

class YourProfileFragment : Fragment() {

    private lateinit var yourProfileViewModel: YourProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        yourProfileViewModel =
                ViewModelProviders.of(this).get(YourProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_yourprofile, container, false)
        val textView: TextView = root.findViewById(R.id.text_yourprofile)
        yourProfileViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}