package com.example1.project

import androidx.lifecycle.ViewModel


class StudentListViewModel: ViewModel() {
    private val studentRepository = StudentRepository.get()
    val  studentList = studentRepository.getStudents()
    fun addStudent(student: Student) {
        studentRepository.addStudent(student)
    }
    fun updateStudent(student: Student) {
        studentRepository.updateStudent(student)
    }
    fun deleteStudent(student: Student) {
        studentRepository.deleteStudent(student)
    }

}
