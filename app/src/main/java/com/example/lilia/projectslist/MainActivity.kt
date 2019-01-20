package com.example.lilia.projectslist;

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
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
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import android.widget.ProgressBar
import com.example.lilia.projectslist.R.id.*

class MainActivity : AppCompatActivity() {

    fun link(view:View){
        val linkIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
        startActivity(linkIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val projects = listOf(
            Project(getString(R.string.name1),getString(R.string.descr1),
                getString(R.string.tagName1), resources.getColor(R.color.redTag),
                "https://img.bfmtv.com/c/630/420/207ef/3b62a3af980faa60a10f041e3c4.png"
                ),

            Project(getString(R.string.name2), getString(R.string.descr2),
                getString(R.string.tagName2), resources.getColor(R.color.blueTag),
                "http://images6.fanpop.com/image/photos/39400000/Sasuke-Uchiha-uchiha-sasuke-39421286-1024-585.png"),

            Project(getString(R.string.name3),getString(R.string.descr3),
                getString(R.string.tagName3), resources.getColor(R.color.yellowTag),
                "https://trustmyscience.com/wp-content/uploads/2018/03/ornithorynque-nage.jpeg")
        )

        val skills = listOf(
            skill("Android",55),
            skill("Kotlin",25),
            skill("Javascript",80),
            skill("CSS",100),
            skill("Golang",5)
        )

        val degrees = listOf(
            degree("DUT Informatique","Université de Valence", "Paris", "2018"),
            degree("Bac S Spécialité Mathématiques","Lycée Mermoz", "Paris", "2017")
        )

        val experiences = listOf(
            experience("Développeur","SUP'Internet", "Paris", "2018 - 2019"),
            experience("Développeur","Le Monde", "Lyon", "2012 - 2018")
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = careerAdapter(skills,degrees,experiences)
    }
}

data class Project(val name: String, val description: String, val tagName: String, val colorTag: Int, val image: String)

data class skill(val name: String, val percent: Int)
data class degree(val name: String, val institution: String, val place: String, val date: String)
data class experience(val function: String, val institution: String, val place: String, val date: String)

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
        val blockProject: ConstraintLayout = itemView.findViewById(R.id.blockProject)

        fun bindValue(project: Project) {
            title.text = project.name
            description.text = project.description
            tagName.text = project.tagName
            DrawableCompat.setTintList(colorTag.background, ColorStateList.valueOf(project.colorTag))
            Picasso.get().load(project.image).resize(1024,520).into(image)
        }

    }

}

class careerAdapter(val skills:List<skill>,val degrees:List<degree>,val experiences:List<experience>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TYPE_SKILL = 0
    val TYPE_DEGREE = 1
    val TYPE_EXPERIENCE = 2

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val viewType : Int = getItemViewType(p1)
        Log.v("azerty", getItemViewType(p1).toString())
        if (viewType == TYPE_SKILL) {
            (p0 as SkillsViewHolder).bindValue(skills[p1])
        }
        else if(viewType == TYPE_DEGREE){
            (p0 as DegreesViewHolder).bindValue(degrees[p1-5])
            }
        else{
            (p0 as ExperiencesViewHolder).bindValue(experiences[p1-7])
        }
        }

    override fun getItemCount(): Int {
        return skills.size + degrees.size + experiences.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewSkill: View = LayoutInflater.from(p0.context).inflate(R.layout.skills, p0, false)
        val viewDegree: View = LayoutInflater.from(p0.context).inflate(R.layout.degrees, p0, false)
        val viewExperiences: View = LayoutInflater.from(p0.context).inflate(R.layout.experiences, p0, false)
        if (viewType == TYPE_SKILL) {
            return SkillsViewHolder(viewSkill)
        }
        else if(viewType == TYPE_DEGREE) {
            return DegreesViewHolder(viewDegree)
        }
        else{
            return ExperiencesViewHolder(viewExperiences)
        }
    }

    override fun getItemViewType(position: Int): Int {
        Log.v("count",position.toString())
        if(position < 5){
            return TYPE_SKILL
        }
        else if(position < 7){
            return TYPE_DEGREE
        }
        else{
            return TYPE_EXPERIENCE
        }
    }

    class SkillsViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val name: TextView = itemView.findViewById(R.id.nameSkill)
        val percent: ProgressBar = itemView.findViewById(R.id.progressSkill)

        fun bindValue(skill: skill) {
            name.text = skill.name
            percent.progress = skill.percent
        }

    }
    class DegreesViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val degreeName: TextView = itemView.findViewById(R.id.degreeName)
        val institution : TextView = itemView.findViewById(R.id.degreeInstitution)
        val place : TextView = itemView.findViewById(R.id.degreePlace)
        val date : TextView = itemView.findViewById(R.id.degreeDate)

        fun bindValue(degree: degree) {
            degreeName.text = degree.name
            institution.text = degree.institution
            place.text = degree.place
            date.text = degree.date
        }

    }

    class ExperiencesViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val function: TextView = itemView.findViewById(R.id.function)
        val institution : TextView = itemView.findViewById(R.id.institution)
        val place : TextView = itemView.findViewById(R.id.place)
        val date : TextView = itemView.findViewById(R.id.date)

        fun bindValue(experience: experience) {
            function.text = experience.function
            institution.text = experience.institution
            place.text = experience.place
            date.text = experience.date
        }

    }

}


