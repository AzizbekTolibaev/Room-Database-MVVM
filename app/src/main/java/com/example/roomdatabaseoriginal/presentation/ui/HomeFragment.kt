package com.example.roomdatabaseoriginal.presentation.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseoriginal.MainActivity
import com.example.roomdatabaseoriginal.R
import com.example.roomdatabaseoriginal.adapters.UserAdapter
import com.example.roomdatabaseoriginal.data.entity.User
import com.example.roomdatabaseoriginal.databinding.FragmentHomeBinding
import com.example.roomdatabaseoriginal.presentation.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private val adapter = UserAdapter()
    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        (requireActivity() as MainActivity).window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.background_color)

        initVariable()
        initObservers()
        initListener()
    }

    private fun initVariable() {
        binding.recyclerView.adapter = adapter
        swipeDelete()
    }

    private fun initListener() {
        binding.etSearch.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                lifecycleScope.launch {
                    viewModel.searchUserByName(it.toString())
                }
            } else {
                lifecycleScope.launch {
                    viewModel.getAllUsers()
                }
            }
        }

        adapter.setOnUserClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToEditFragment2(true,it.id,it.name,it.surname,it.phoneNumber))
        }

        binding.imgAdd.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToEditFragment2(
                    false, 0, "", ",", 0
                )
            )
        }
    }

    private fun initObservers() {
        viewModel.getAllUserLiveData.observe(viewLifecycleOwner) {
            adapter.models = it
        }
    }

    private fun swipeDelete() {
        val itemTouchHelperCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ) = false

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val user: User = adapter.models[position]

                    lifecycleScope.launch {
                        viewModel.deleteUser(user)
                    }
                    adapter.models.removeAt(position)
                    adapter.notifyItemRemoved(position)

                    Snackbar.make(
                        viewHolder.itemView, "O'shti", Snackbar.LENGTH_LONG
                    ).apply {
                        setAction("UNDO") {
                            lifecycleScope.launch {
                                viewModel.addUser(user)
                            }

                            adapter.models.add(position, user)
                            adapter.notifyItemInserted(position)
                            binding.recyclerView.scrollToPosition(position)
                        }
                        setActionTextColor(Color.YELLOW)
                    }.show()
                }
            }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.recyclerView)
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            viewModel.getAllUsers()
        }
    }
}