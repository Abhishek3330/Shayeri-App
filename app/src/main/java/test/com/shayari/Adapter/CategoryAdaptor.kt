package test.com.shayari.Adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import test.com.shayari.AllShayeriActivity
import test.com.shayari.MainActivity
import test.com.shayari.Model.CategoryModel
import test.com.shayari.databinding.ItemCategoryBinding

/**
 * Created by Mukesh on 13-12-2022.
 */
class CategoryAdaptor(val mainActivity: MainActivity, val list: ArrayList<CategoryModel>) : RecyclerView.Adapter<CategoryAdaptor.CatViewHolder>() {

   val colorList = arrayListOf<String>("#55efc4","#81ecec","#ff7675","#0984e3","#e84393","#d63031")

    class CatViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {

        if (position%6==0){
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorList[0]))
        }
        else if (position%6==1){
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorList[1]))
        }
        else if (position%6==2){
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorList[2]))
        }
        else if (position%6==3){
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorList[3]))
        }
        else if (position%6==4){
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorList[4]))
        }
        else if (position%6==5){
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorList[5]))
        }


        holder.binding.itemText.text = list[position].name.toString()

        holder.binding.root.setOnClickListener{

            val intent = Intent(mainActivity,AllShayeriActivity::class.java)
            intent.putExtra("id",list[position].id)
            intent.putExtra("name",list[position].name)

            mainActivity.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
       return list.size
    }


}