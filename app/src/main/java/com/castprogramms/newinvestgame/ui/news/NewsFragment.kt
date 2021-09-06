package com.castprogramms.newinvestgame.ui.news

import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.fragment.app.Fragment
import com.castprogramms.newinvestgame.MainActivity
import com.castprogramms.newinvestgame.R
import com.castprogramms.newinvestgame.databinding.DialogNewBinding
import com.castprogramms.newinvestgame.databinding.FragementNewsBinding
import com.castprogramms.newinvestgame.databinding.StandartFragmentBinding
import com.castprogramms.newinvestgame.news.GeneratorNews
import com.castprogramms.newinvestgame.tools.Updater
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment: Fragment(R.layout.fragement_news) {
    private val viewModel: NewsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Лента новостей"
        val binding = FragementNewsBinding.bind(view)
        val adapter = NewsAdapter()
        binding.recyclerNews.adapter = adapter

        viewModel.getNews().observe(viewLifecycleOwner, {
            it.minus(adapter.news).reversed().forEach {
                adapter.addNew(it)
                binding.recyclerNews.scrollToPosition(0)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).isNewsFragment.postValue(true)
    }

    override fun onStop() {
        super.onStop()
        (requireActivity() as MainActivity).isNewsFragment.postValue(false)
    }

}