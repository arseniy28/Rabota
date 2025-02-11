import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.rabotablyat.R
import com.example.rabotablyat.models.Flower

class FlowerListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var flowerAdapter: FlowerAdapter
    private val flowerList = mutableListOf<Flower>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flower_list)

        recyclerView = findViewById(R.id.flowerRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        flowerAdapter = FlowerAdapter(flowerList)
        recyclerView.adapter = flowerAdapter

        loadFlowers()
    }

    private fun loadFlowers() {
        val database = FirebaseDatabase.getInstance()
        val flowersRef = database.getReference("flowers")

        flowersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                flowerList.clear()
                for (flowerSnapshot in snapshot.children) {
                    val flower = flowerSnapshot.getValue(Flower::class.java)
                    flower?.let { flowerList.add(it) }
                }
                flowerAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}