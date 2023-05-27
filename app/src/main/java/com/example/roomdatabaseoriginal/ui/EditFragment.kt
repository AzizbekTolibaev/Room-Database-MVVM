package com.example.roomdatabaseoriginal.ui

import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdatabaseoriginal.MainActivity
import com.example.roomdatabaseoriginal.R
import com.example.roomdatabaseoriginal.data.entity.User
import com.example.roomdatabaseoriginal.databinding.FragmentEditBinding
import com.example.roomdatabaseoriginal.presentation.MainViewModel
import kotlinx.coroutines.launch

class EditFragment: Fragment(R.layout.fragment_edit) {
    private lateinit var binding: FragmentEditBinding
    private val args: EditFragmentArgs by navArgs()
    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditBinding.bind(view)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        (requireActivity() as MainActivity).window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.teal_700)

        initVariables()
        initListeners()
    }

    fun initVariables() {
        maxLengthToTextView(binding.etName, 20)
        maxLengthToTextView(binding.etSurname, 20)
        maxLengthToTextView(binding.etPhoneNumber, 9)
    }

    fun initListeners() {
        val isEdit = args.isEdit
        val userName = args.name
        val userId = args.id
        val userSurname = args.surname
        val userPhoneNumber = args.phoneNumber

        if (isEdit) {
            binding.etName.setText(userName)
            binding.etSurname.setText(userSurname)
            binding.etPhoneNumber.setText(userPhoneNumber.toString())
            binding.btnSave.text = "Edit"
        } else {
            binding.btnSave.text = "Add"
        }

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val surname = binding.etSurname.text.toString()
            val phoneNumber = binding.etPhoneNumber.text.toString()

            if (isEdit) {
                if (name.isNotEmpty() && surname.isNotEmpty() && phoneNumber
                        .isNotEmpty()
                ) {
                    lifecycleScope.launch {
                        viewModel.updateUser(
                            User(
                                userId,
                                name,
                                surname,
                                phoneNumber.toInt(),
                                R.drawable.ic_round_person_24
                            )
                        )
                    }
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(activity, "Mag'luwmatlardi toliq kiritin'", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                if (name.isNotEmpty() && surname.isNotEmpty() && phoneNumber.isNotEmpty()) {
                    lifecycleScope.launch {
                        viewModel.addUser(
                            User(0, name, surname, phoneNumber.toInt(), R.drawable.ic_round_person_24)
                        )
                    }
                    Toast.makeText(activity, "Successful", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(activity, "Mag'luwmatlardi toliq kiritin'", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun maxLengthToTextView(textview: TextView, maxNumber: Int) {
        textview.filters = arrayOf<InputFilter>(object : InputFilter {
            override fun filter(
                source: CharSequence?,
                start: Int,
                end: Int,
                dest: Spanned?,
                dstart: Int,
                dend: Int
            ): CharSequence? {
                if (dest.toString().length < maxNumber) {
                    return source
                }
                return ""
            }
        })
    }
}