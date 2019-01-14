package com.example.lilia.projectslist;

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val projects = listOf(
            Project(getString(R.string.name1),getString(R.string.descr1)),
            Project(getString(R.string.name2),getString(R.string.descr2)),
            Project(getString(R.string.name3),getString(R.string.name3))
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyAdapter(projects)
    }
}

data class Project(val name:String, val descriptor: String)

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

        fun bindValue(project: Project) {
            title.text = project.name
            description.text = project.descriptor
        }

    }

}