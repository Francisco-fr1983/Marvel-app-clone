package com.example.marvel_app_clone.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.marvel_app_clone.databinding.FragmentListCharacterBinding
import com.example.marvel_app_clone.ui.base.BaseFragment


class ListCharacterFragment : BaseFragment<FragmentListCharacterBinding, ListCharacterViewModel>() {
    override val viewModel: ListCharacterViewModel by viewModels()


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListCharacterBinding = FragmentListCharacterBinding.inflate(inflater, container, false)
}