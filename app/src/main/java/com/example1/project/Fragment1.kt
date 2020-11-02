package com.example1.project

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders


class Fragment1 : Fragment(),NewDialog.Callbacks {
    private lateinit var textView: TextView
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
        val stu=studentListViewModel.studentList

        val view= inflater.inflate(R.layout.fragment_1, container, false)
        textView=view.findViewById(R.id.textView)
        printStudent(stu)
        return view
    }
    fun printStudent(sList: List<Student>) {
        sList.forEach() {
            textView.append("${it.number}|${it.name}|${it.pass}\n")
        }
    }

    override fun onSudentAdd(student: Student) {
        studentListViewModel.addStudent(student)
        studentListViewModel.updateStudent(student)
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
}
