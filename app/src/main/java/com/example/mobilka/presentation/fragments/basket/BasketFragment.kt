package com.example.mobilka.presentation.fragments.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilka.R
import com.example.mobilka.databinding.FragmentBasketBinding
import com.example.mobilka.databinding.FragmentLoginBinding
import com.example.mobilka.model.BasketModel
import com.example.mobilka.presentation.fragments.login.LoginViewModel


class BasketFragment : Fragment(R.layout.fragment_basket){

    private lateinit var basketViewModel: BasketViewModel
    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: RcBasketAdapter



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        basketViewModel = ViewModelProvider(this)[BasketViewModel::class.java]
        binding.rcBasket.layoutManager = LinearLayoutManager(requireContext())
        adapter = RcBasketAdapter()
        adapter.decrementClickListener = {
            basketViewModel.decrement(it.item)
        }
        adapter.incrementClickListener = {
            basketViewModel.increment(it.item)
        }
        binding.rcBasket.adapter = adapter
        val animator = binding.rcBasket.itemAnimator
        if(animator is DefaultItemAnimator){
            animator.supportsChangeAnimations = false
        }

        basketViewModel.basketList.observe(viewLifecycleOwner){
            adapter.list = it
        }
        basketViewModel.sum.observe(viewLifecycleOwner){
            binding.twTeaList3.text = "Итого: $it руб."
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }
}