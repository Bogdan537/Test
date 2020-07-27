package com.example.test

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_content.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
var adapter: MyAdapter?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav_view.setNavigationItemSelectedListener (this)

        var list = ArrayList<ListItem>()

        list.addAll(fillAraas(resources.getStringArray(R.array.Kurs),
        resources.getStringArray(R.array.Kurs_content),getImageId(R.array.Kurs_image_array)))
        rcView.hasFixedSize()
        rcView.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(list,this)
        rcView.adapter = adapter


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
       when(item.itemId){

           R.id.id_kurs ->
           {
               adapter?.updateAdapter(fillAraas(resources.getStringArray(R.array.Kurs),
                   resources.getStringArray(R.array.Kurs_content),getImageId(R.array.Kurs_image_array)))
           }
           R.id.id_princip ->
           {
               adapter?.updateAdapter(fillAraas(resources.getStringArray(R.array.princips),
                   resources.getStringArray(R.array.princips_content),getImageId(R.array.princips_image_array)))
           }
           R.id.id_modul1 ->
           {
              Toast.makeText(this,"Тестування 1",Toast.LENGTH_SHORT).show()
               val intent = Intent(applicationContext, MStartActivity::class.java)
               startActivity(intent)
               }
           R.id.id_modul2 ->
           {
               Toast.makeText(this,"Тестування 2",Toast.LENGTH_SHORT).show()
               val intent = Intent(applicationContext, MStartActivity2::class.java)
               startActivity(intent)
           }
           R.id.id_modul3 ->
           {
               Toast.makeText(this,"Тестування 3",Toast.LENGTH_SHORT).show()
               val intent = Intent(applicationContext, MStartActivity3::class.java)
               startActivity(intent)
           }
           R.id.id_modul4 ->
           {
               Toast.makeText(this,"Тестування 4",Toast.LENGTH_SHORT).show()
               val intent = Intent(applicationContext, MStartActivity4::class.java)
               startActivity(intent)
           }
           R.id.id_lastmodul ->
           {
               Toast.makeText(this,"Загальне тестування",Toast.LENGTH_SHORT).show()
               val intent = Intent(applicationContext, MStartActivity5::class.java)
               startActivity(intent)
           }
           R.id.id_soft ->
           {
               adapter?.updateAdapter(fillAraas(resources.getStringArray(R.array.soft),
                   resources.getStringArray(R.array.soft_content),getImageId(R.array.soft_image_array)))
           }
           R.id.id_Requirements ->
           {
               adapter?.updateAdapter(fillAraas(resources.getStringArray(R.array.requirements),
                   resources.getStringArray(R.array.requirements_content),getImageId(R.array.requirements_image_array))) }
           R.id.id_UML ->
           {
               adapter?.updateAdapter(fillAraas(resources.getStringArray(R.array.uml),
                   resources.getStringArray(R.array.uml_content),getImageId(R.array.uml_image_array)))
                }
           R.id.id_Cases ->
           {
               adapter?.updateAdapter(fillAraas(resources.getStringArray(R.array.Cases),
                   resources.getStringArray(R.array.Cases_content),getImageId(R.array.Cases_image_array)))
              }

       }
        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }
    fun fillAraas(titleArray: Array<String>,contentArray:Array<String>,imageArray:IntArray):List<ListItem>
    {
      var listItemArray = ArrayList<ListItem>()
        for (n in 0..titleArray.size - 1)
        {
            var listItem = ListItem(imageArray[n],titleArray[n],contentArray[n])
            listItemArray.add(listItem)
        }
        return listItemArray
    }
    fun getImageId(ImageArrayId:Int): IntArray {
        var tArray:TypedArray = resources.obtainTypedArray(ImageArrayId)
        val count=tArray.length()
        val ids = IntArray(count)
        for(i in ids.indices)
        {
            ids[i]=tArray.getResourceId(i,0)
        }
        tArray.recycle()
return ids
    }
}
