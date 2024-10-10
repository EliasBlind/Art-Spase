package com.example.art

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.squareup.picasso.Picasso
import java.io.Serializable
import java.net.URL

val GlobalList: ArrayList<PaintData> = ArrayList()

data class PaintData (
    val description:String,
    val image: String,
) : Serializable

class PaintInformation() : Serializable {
    val paintDataList: ArrayList<PaintData> = GlobalList

    public fun addPaintItem(description:String, url: String) {
        val data: PaintData = PaintData(description, url)
        paintDataList.add(data)
    }

    private fun getBitmap(url : String) : Bitmap? {
        var resultBitmap : Bitmap? = null
        Picasso.get().load(url).into(object : com.squareup.picasso.Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                resultBitmap =  bitmap
            }
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}
        })
        return resultBitmap
    }

}