package com.example1.project

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import java.util.*

class NewDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val v = activity?.layoutInflater?.inflate(R.layout.fragment_new_dialog, null)
        val noEditText = v?.findViewById(R.id.number_EditText) as EditText
        val nameEditText = v?.findViewById(R.id.name_EditText) as EditText
        val passCheckBox = v?.findViewById(R.id.pass_CheckBox) as CheckBox
        return AlertDialog.Builder(requireContext(), R.style.ThemeOverlay_AppCompat_Dialog_Alert)
            .setView(v)
            .setPositiveButton("Add") { dialog, _ ->
                val s =Student(
                    UUID.randomUUID()  ,nameEditText.text.toString() ,noEditText.text.toString().toInt() ,
                    passCheckBox.isChecked)
                targetFragment?.let { fragment ->
                    (fragment as Callbacks).onSudentAdd(s)
                }
            }.setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }.create()

    }
    interface Callbacks{
        fun onSudentAdd(Student:Student)
    }
}