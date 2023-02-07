package test.com.shayari

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.BuildConfig
import com.google.firebase.firestore.FirebaseFirestore
import test.com.shayari.Adapter.CategoryAdaptor
import test.com.shayari.Model.CategoryModel
import test.com.shayari.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var db: FirebaseFirestore

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= FirebaseFirestore.getInstance()

        db.collection("Shayeri").addSnapshotListener{value,error->

            val list= arrayListOf<CategoryModel>()

            val data = value?.toObjects(CategoryModel::class.java)

            if (data != null) {
                list.addAll(data)
            }

            binding.recCatagery.layoutManager=LinearLayoutManager(this)
            binding.recCatagery.adapter=CategoryAdaptor(this,list)

        }

        binding.btnMenu.setOnClickListener{
            if (binding.drawerLayout.isDrawerOpen(Gravity.LEFT)){
                binding.drawerLayout.closeDrawer(Gravity.LEFT)
            }
            else{
                binding.drawerLayout.openDrawer(Gravity.LEFT)

            }
        }


        binding.navigation.setNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.share->{
                    try {
                        val shareIntent = Intent(Intent.ACTION_SEND)
                        shareIntent.type = "text/plain"
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                        var shareMessage = "\nInstall this application for new hindi shayeri\n\n"
                        shareMessage =
                            """
                            ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.BUILD_TYPE}
                            
                            
                            """.trimIndent()
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                        startActivity(Intent.createChooser(shareIntent, "choose one"))
                    } catch (e: Exception) {

                    }
                    true
                }
                R.id.rate->{
                    val uri = Uri.parse("market://details?id=$packageName")
                    val myAppLinkToMarket = Intent(Intent.ACTION_VIEW, uri)
                    try {
                        startActivity(myAppLinkToMarket)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(this, " Unable to find to play Store", Toast.LENGTH_LONG).show()
                    }
                    true
                }
                R.id.more->{
                    val uri = Uri.parse("market://details?id=$packageName")
                    val myAppLinkToMarket = Intent(Intent.ACTION_VIEW, uri)
                    try {
                        startActivity(myAppLinkToMarket)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(this, " Unable to find to play Store", Toast.LENGTH_LONG).show()
                    }
                    true
                }
                R.id.about-> {

                    val intent = Intent(this, About::class.java)
                    // start your next activity
                    startActivity(intent)
                    true
                }

                else -> {false}
            }

        }
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(Gravity.LEFT))
            binding.drawerLayout.closeDrawer(Gravity.LEFT)
        else{
            super.onBackPressed()

        }
    }
}