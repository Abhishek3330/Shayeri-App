package test.com.shayari.Adapter

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.BuildConfig
import test.com.shayari.AllShayeriActivity
import test.com.shayari.Model.ShayeriModel
import test.com.shayari.R
import test.com.shayari.databinding.ItemShayeriBinding


/**
 * Created by Mukesh on 14-12-2022.
 */
class AllShayeriAdapter(val allShayeriActivity: AllShayeriActivity, private val shayeriList: ArrayList<ShayeriModel>) : RecyclerView.Adapter<AllShayeriAdapter.ShayeriViewHolder>() {


    class ShayeriViewHolder(val binding: ItemShayeriBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShayeriViewHolder {
        return ShayeriViewHolder(
            ItemShayeriBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShayeriViewHolder, position: Int) {

        if (position%6==0){
            holder.binding.mainBackground.setBackgroundResource(R.drawable.grediant1)
        }
        else if (position%6==1){
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradent2)
        }
        else if (position%6==2){
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradent3)
        }
        else if (position%6==3){
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradent4)
        }
        else if (position%6==4){
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradent5)
        }
        else if (position%6==5){
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradent6)
        }
        holder.binding.itemShayeri.text = shayeriList[position].data.toString()

        holder.binding.share.setOnClickListener{
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                var shareMessage = "\n${shayeriList[position].data}\n\n"
                shareMessage =
                    """
                            ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.BUILD_TYPE}
                            
                            
                            """.trimIndent()
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                allShayeriActivity.startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (e: Exception) {

            }
        }
        holder.binding.copy.setOnClickListener{
            val clipboard: ClipboardManager? =
                allShayeriActivity.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?
            val clip = ClipData.newPlainText("label", shayeriList[position].data.toString())
            clipboard?.setPrimaryClip(clip)

            Toast.makeText(allShayeriActivity,"Copied",Toast.LENGTH_SHORT).show()
        }
        holder.binding.whatsappBtn.setOnClickListener{
            val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.type = "text/plain"
            whatsappIntent.setPackage("com.whatsapp")
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, shayeriList[position].data.toString())
            try {
                allShayeriActivity.startActivity(whatsappIntent)
            } catch (ex: ActivityNotFoundException) {
            }

        }
    }

    override fun getItemCount()= shayeriList.size
}