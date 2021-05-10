package com.example.cookingrecipes.ui.YourProfile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.cookingrecipes.Api.RetrofitClient
import com.example.cookingrecipes.MainActivity
import com.example.cookingrecipes.R
import com.example.cookingrecipes.data.model.DataProfile
import com.example.cookingrecipes.data.model.ProfileModel
import com.example.cookingrecipes.data.repositories.SelectedProfileRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_yourprofile.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

class YourProfileFragment : Fragment() {

    private var data : DataProfile?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_yourprofile, container, false)


        RetrofitClient.instance.fetchSelectedProfile().enqueue(object  : Callback<DataProfile> {
            override fun onResponse(call: Call<DataProfile>, response: Response<DataProfile>) {
                response.body()?.let { setData(it) }
            }

            override fun onFailure(call: Call<DataProfile>, t: Throwable) {
                Toast.makeText(activity,"something went wrong",Toast.LENGTH_SHORT).show()
            }
        })
        return root
    }

    fun setData(dataProfile: DataProfile){
        your_name.text = dataProfile.data.name
        your_description.text = dataProfile.data.description
        Picasso.get()
            .load(dataProfile.data?.photo_id?.data?.url)
            .into(your_photo)
    }
}