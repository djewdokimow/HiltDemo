package com.jewdokimow.hiltdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jewdokimow.hiltdemo.databinding.FragmentBirthdayLotteriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BirthdayLotteryFragment : Fragment() {

    private lateinit var binding: FragmentBirthdayLotteriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBirthdayLotteriesBinding.inflate(layoutInflater)
        return binding.root
    }
}