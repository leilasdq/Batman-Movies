package com.example.batman_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.api.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.example.batman_project.R
import com.example.batman_project.model.ImageSliderModel
import com.smarteist.autoimageslider.SliderViewAdapter


class SliderAdapterExample(private val items: List<ImageSliderModel>) :
    SliderViewAdapter<SliderAdapterExample.MySliderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?): MySliderViewHolder {
        val inflate: View = LayoutInflater.from(parent!!.context)
            .inflate(R.layout.slider_layout, parent, false)
        return MySliderViewHolder(inflate)
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: MySliderViewHolder?, position: Int) {
        viewHolder?.bind(items[position])
    }

    class MySliderViewHolder(itemViews: View) :
        SliderViewAdapter.ViewHolder(itemViews) {
        var img: ImageView = itemViews.findViewById(R.id.slider_img)
        var txt: TextView = itemViews.findViewById(R.id.slider_name)

        fun bind(model: ImageSliderModel) {
            txt.text = model.img_txt
            img.load(model.image_link) {
                crossfade(false)
                transformations(RoundedCornersTransformation(4f))
            }
        }
    }
}