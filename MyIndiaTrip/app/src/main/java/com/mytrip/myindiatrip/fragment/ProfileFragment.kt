package com.mytrip.myindiatrip.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mytrip.myindiatrip.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    lateinit var profileBinding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileBinding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        initView()
        // Inflate the layout for this fragment
        return profileBinding.root
    }

    private fun initView() {
        profileBinding.cdUserLogin.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(com.mytrip.myindiatrip.R.id.container, UserLoginFragment()).commit()
        }
    }
}

