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

        // Créer des faux projet
        val projects: ArrayList<String> = ArrayList()

        projects.add("Projet 1")
        projects.add("Projet 2")
        projects.add("Projet 3")

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyAdapter(projects)
    }
}

data class Project(val name: String)

class MyAdapter(val projects: ArrayList<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.title.text = projects[p1]
    }

    override fun getItemCount(): Int {
        return projects.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.list, p0, false)
        return MyViewHolder(view)
    }

    class MyViewHolder(v: View) : ViewHolder(v) {

        // 1) Récupérer les vues
        //val thisProject: RecyclerView = v.findViewById(R.id.recyclerView)
        val title: TextView = itemView.findViewById(R.id.list_item)
        // TextView1
        // TextView2

        // 2ème étape
        fun bindValue(projet: Project) {
            //textView1 = project.title
        }

    }

    // Des projets

}