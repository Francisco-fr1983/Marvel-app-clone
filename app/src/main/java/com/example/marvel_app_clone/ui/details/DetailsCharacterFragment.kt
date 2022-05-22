package com.example.marvel_app_clone.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.marvel_app_clone.ui.base.BaseFragment
import com.example.marvel_app_clone.databinding.FragmentDetailsCharacterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsCharacterFragment : BaseFragment<FragmentDetailsCharacterBinding, DetailsCharacterViewModel>() {

    override val viewModel: DetailsCharacterViewModel by viewModels()


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsCharacterBinding = FragmentDetailsCharacterBinding.inflate(inflater, container, false)
}