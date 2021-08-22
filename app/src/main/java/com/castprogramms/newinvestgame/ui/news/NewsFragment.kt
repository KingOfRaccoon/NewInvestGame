package com.castprogramms.newinvestgame.ui.news

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.fragment.app.Fragment
import com.castprogramms.newinvestgame.MainActivity
import com.castprogramms.newinvestgame.R
import com.castprogramms.newinvestgame.databinding.StandartFragmentBinding
import com.castprogramms.newinvestgame.news.GeneratorNews
import com.castprogramms.newinvestgame.tools.Updater
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment: Fragment(R.layout.standart_fragment) {
    private val viewModel: NewsViewModel by viewModel()

    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    @ExperimentalUnitApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Лента новостей"
        val layout = NewsFragmentLayout()
        StandartFragmentBinding.bind(view).compose.setContent {
            layout.Main()
        }
        viewModel.getNews().observe(viewLifecycleOwner, {
            layout.news.value = it.toList()
        })
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).isNewsFragment = true
    }

    override fun onStop() {
        super.onStop()
        (requireActivity() as MainActivity).isNewsFragment = false
    }
}