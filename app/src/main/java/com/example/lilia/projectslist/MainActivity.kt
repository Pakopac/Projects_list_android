package com.example.lilia.projectslist;

import android.content.res.ColorStateList
import android.os.Bundle
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val projects = listOf(
            Project(getString(R.string.name1),getString(R.string.descr1),
                getString(R.string.tagName1), resources.getColor(R.color.redTag),
                "http://i.imgur.com/DvpvklR.png"
                ),

            Project(getString(R.string.name2), getString(R.string.descr2),
                getString(R.string.tagName2), resources.getColor(R.color.orangeTag),
                "http://i.imgur.com/DvpvklR.png"),

            Project(getString(R.string.name3),getString(R.string.name3),
                getString(R.string.tagName3), resources.getColor(R.color.greenTag),
                "http://i.imgur.com/DvpvklR.png")
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyAdapter(projects)
    }
}

data class Project(val name:String, val description: String, val tagName: String, val colorTag: Int, val image: String)

class MyAdapter(val projects: List<Project>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.bindValue(projects[p1])
    }

    override fun getItemCount(): Int {
        return projects.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.list, p0, false)
        return MyViewHolder(view)
    }

    class MyViewHolder(v: View) : ViewHolder(v) {

        val title: TextView = itemView.findViewById(R.id.title)
        val description: TextView = itemView.findViewById(R.id.description)
        val tagName: TextView = itemView.findViewById(R.id.textTag)
        val colorTag: LinearLayout = itemView.findViewById(R.id.blockTag)
        val image: ImageView = itemView.findViewById(R.id.image)

        fun bindValue(project: Project) {
            title.text = project.name
            description.text = project.description
            tagName.text = project.tagName
            DrawableCompat.setTintList(colorTag.background, ColorStateList.valueOf(project.colorTag))
            Picasso.get().load(project.image).into(image)
        }

    }

}