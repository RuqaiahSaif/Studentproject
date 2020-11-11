package com.example1.project

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.constraintlayout.motion.utils.CurveFit.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example1.project.StudentRepository.Companion.get
import kotlinx.android.synthetic.main.fragment_1.*
import kotlinx.android.synthetic.main.student_list.*
import java.lang.reflect.Array.get
import java.nio.file.Paths.get


class Fragment1 : Fragment(),NewDialog.Callbacks {
    private lateinit var studentRecyclerView: RecyclerView
    private var adapter: StudentAdapter? = null
    lateinit var no_students:TextView
    lateinit var add_new_student:ImageButton
    private val studentListViewModel: StudentListViewModel by lazy {
        ViewModelProviders.of(this).get(StudentListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)


    }
    companion object {
        fun newInstance(): Fragment1 {
            return Fragment1()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.student_list, container, false)
        studentRecyclerView =view.findViewById(R.id.student_recycler_view) as RecyclerView
        studentRecyclerView.layoutManager = LinearLayoutManager(context)
        no_students=view.findViewById(R.id.no_student)
        add_new_student=view.findViewById(R.id.add_new_student)
        studentRecyclerView.adapter = adapter

        return view
    }
    private fun updateUI(students: List<Student>) {

        adapter = StudentAdapter(students)
        studentRecyclerView.adapter = adapter

    }
    private inner class StudentHolder(view: View) : RecyclerView.ViewHolder(view),View.OnClickListener {
        val idTextView: TextView = itemView.findViewById(R.id.textView)
        val nameTextView: TextView = itemView.findViewById(R.id.textView1)
        val resultTextView: TextView = itemView.findViewById(R.id.textView2)
        val delete: Button = itemView.findViewById(R.id.button)

        private lateinit var student: Student
        init {
            delete.setOnClickListener(this)
        }
        fun bind(student: Student) {
            this.student = student
            idTextView.text = this.student.number.toString()
            nameTextView.text = this.student.name
            if (this.student.pass)
                resultTextView.text = "passed "
            else
                resultTextView.text = "faild "

        }

        override fun onClick(v: View?) {
            val builder = AlertDialog.Builder(context)
            //set title for alert dialog
            builder.setTitle(R.string.dialogTitle)
            //set message for alert dialog
            builder.setMessage(R.string.dialogMessage)
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            //performing positive action
            builder.setPositiveButton("Yes"){dialogInterface, which ->
                studentListViewModel.deleteStudent(this.student)
            }
            //performing cancel action
            builder.setNeutralButton("Cancel"){dialogInterface , which ->

            }
            //performing negative action
            builder.setNegativeButton("No"){dialogInterface, which ->

            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        studentListViewModel.studentList.observe(
            viewLifecycleOwner,
            Observer { students ->
                students ?.let {
                    updateUI(students)
                }
            })


    }

    override fun onStart() {
        super.onStart()
        add_new_student.setOnClickListener {
            NewDialog().apply {
                setTargetFragment(this@Fragment1, 0)
                show(this@Fragment1.requireFragmentManager(), "Input")

            }
        }

    }

    private inner class StudentAdapter(var students: List<Student>) :
        RecyclerView.Adapter<StudentHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : StudentHolder {
            val view = layoutInflater.inflate(R.layout.fragment_1, parent, false)

            return StudentHolder(view)
        }


        override fun getItemCount():Int{
            if (students.isEmpty()) {
                no_students.visibility = View.VISIBLE
               add_new_student.visibility = View.VISIBLE

            } else {
               no_students.visibility = View.GONE
               add_new_student.visibility = View.GONE

            }

            return students.size
        }



        override fun onBindViewHolder(holder: StudentHolder, position: Int) {
            val student = students[position]
            holder.bind(student)
        }
    }
            override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.student_list, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_student -> {
                NewDialog().apply {
                    setTargetFragment(this@Fragment1, 0)
                    show(this@Fragment1.requireFragmentManager(), "Input")
                }
                true
            }
            else -> return super.onOptionsItemSelected(item)

        }
    }

    override fun onSudentAdd(Student: Student) {
        studentListViewModel.addStudent(Student)

    }
}
