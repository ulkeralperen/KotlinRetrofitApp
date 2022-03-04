package com.alperen.rickandmortykotlinfragment.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.alperen.rickandmortykotlinfragment.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.row_layout.view.*


class SecondFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val id:Long=SecondFragmentArgs.fromBundle(it).id
            val name:String=SecondFragmentArgs.fromBundle(it).name
            val status:String=SecondFragmentArgs.fromBundle(it).status
            val species:String=SecondFragmentArgs.fromBundle(it).species
            val type:String=SecondFragmentArgs.fromBundle(it).type
            val gender:String=SecondFragmentArgs.fromBundle(it).gender
            val image:String=SecondFragmentArgs.fromBundle(it).image
            val url:String=SecondFragmentArgs.fromBundle(it).url
            val created:String=SecondFragmentArgs.fromBundle(it).created


            Picasso.get().load(image)
                .resize(120,120)
                .centerCrop()
                .into(imageView2)
            textViewid.text="Id: ${id}"
            textViewname.text="Name: ${name}"
            textViewstatus.text="Status: ${status}"
            textViewgender.text="Gender: ${gender}"
            textViespecies.text="Species: ${species}"
            textViewtype.text="Type: ${type}"
            textViewgender.text="Gender: ${gender}"
            textViewcreated.text="Created Time: ${created}"



        }


        button2.setOnClickListener {
            val action=SecondFragmentDirections.actionSecondFragmentToFirstFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

}