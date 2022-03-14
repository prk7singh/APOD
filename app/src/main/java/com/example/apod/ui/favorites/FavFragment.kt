package com.example.apod.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apod.databinding.FragmentFavBinding

class FavFragment : Fragment() {

    private var _binding: FragmentFavBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(FavViewModel::class.java)

        _binding = FragmentFavBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var todoRecyclerView = binding.favRecycler
        todoRecyclerView.adapter = FavListAdapter(listOf())
        todoRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        notificationsViewModel.getFavList(requireContext())

        notificationsViewModel.favList.observe(viewLifecycleOwner){
            todoRecyclerView.adapter = FavListAdapter(it)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}