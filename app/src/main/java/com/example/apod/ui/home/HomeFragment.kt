package com.example.apod.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.apod.R
import com.example.apod.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    companion object{
        private val TAG = this::class.java.name
    }

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val date = arguments?.getString("date")?:""
        Log.d(TAG,"argument received is $date")
        homeViewModel.getMediaData(date,requireContext())

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.media.observe(viewLifecycleOwner) {
            binding.imgHome.load(it?.url){
                crossfade(true)
                placeholder(androidx.appcompat.R.drawable.abc_ab_share_pack_mtrl_alpha)
            }
            binding.imgFav.visibility = View.VISIBLE
            binding.textTitle.text = it?.title
            binding.textDate.text = it?.date
            binding.textDetail.text = it?.explanation
        }

        homeViewModel.status.observe(viewLifecycleOwner){
            when(it){
                ApiStatus.NETWORK_ERROR->
                    showToast(R.string.network_error)
                ApiStatus.ERROR ->
                    showToast(R.string.error)
            }
        }

        binding.imgFav.setOnClickListener {
            binding.imgFav.setImageResource(R.drawable.ic_favorite_red_24)
            homeViewModel.addToFavorites(requireContext())
            showToast(R.string.fav_msg)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showToast(message:Int){
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
    }
}