package com.example.cookingrecipes.ui.YourProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cookingrecipes.Api.RetrofitClient
import com.example.cookingrecipes.R
import com.example.cookingrecipes.data.model.DataProfile
import com.example.cookingrecipes.data.storage.SharedPreferenceManager
import kotlinx.android.synthetic.main.fragment_yourprofile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YourProfileFragment : Fragment() {

    private var data : DataProfile?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_yourprofile, container, false)


        RetrofitClient.instance.fetchMyProfile(token = "${SharedPreferenceManager.getInstance(requireContext()).isLoggedIn}").enqueue(object  : Callback<DataProfile> {
            override fun onResponse(call: Call<DataProfile>, response: Response<DataProfile>) {
                response.body()?.let { setData(it) }
                println(SharedPreferenceManager.getInstance(requireContext()).isLoggedIn)
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
//        Picasso.get()
//            .load(dataProfile.data.photo_id.data.url)
//            .into(your_photo)
    }
}