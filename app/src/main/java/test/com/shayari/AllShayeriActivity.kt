package test.com.shayari

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import test.com.shayari.Adapter.AllShayeriAdapter
import test.com.shayari.Model.ShayeriModel
import test.com.shayari.databinding.ActivityAllShayeriBinding

class AllShayeriActivity : AppCompatActivity() {

    lateinit var binding: ActivityAllShayeriBinding
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAllShayeriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()

        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.catName.text = name.toString()




        if (id != null) {
            db.collection("Shayeri").document(id).collection("all")
                .addSnapshotListener { value, error ->
                    val shayeriList = arrayListOf<ShayeriModel>()


                    val data = value?.toObjects(ShayeriModel::class.java)

                    if (data != null) {
                        shayeriList.addAll(data)
                    }

                    binding.rcvAllShayeri.layoutManager = LinearLayoutManager(this)
                    binding.rcvAllShayeri.adapter = AllShayeriAdapter(this, shayeriList)

                }
        }


    }
}