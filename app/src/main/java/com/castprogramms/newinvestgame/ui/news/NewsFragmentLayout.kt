package com.castprogramms.newinvestgame.ui.news

import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.castprogramms.newinvestgame.R
import com.castprogramms.newinvestgame.databinding.NewDialogBinding
import com.castprogramms.newinvestgame.news.New

class NewsFragmentLayout {
    var news = mutableStateOf(listOf<New>())
    private val needShowText = mutableStateOf("")

    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    @ExperimentalUnitApi
    @Preview
    @Composable
    fun Main() {
        ListOfNews()
    }

    @ExperimentalMaterialApi
    @ExperimentalUnitApi
    @Composable
    fun ListOfNews() {
        LazyColumn {
            items(news.value) {
                NewsCard(it)
            }
        }
    }

    @ExperimentalMaterialApi
    @ExperimentalUnitApi
    @Composable
    fun NewsCard(new: New) {
        val context = LocalContext.current
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 5.dp),
            elevation = 5.dp,
            onClick = {
                createFullTextAlertDialog(new.msg, context)
            },
            shape = RoundedCornerShape(corner = CornerSize(16.dp))
        ) {
            Row(Modifier.padding(10.dp)) {
                Image(
                    painter = painterResource(R.drawable.chart),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .aspectRatio(1f)
                )

                Text(
                    new.msg,
                    Modifier
                        .padding(start = 10.dp, end = 10.dp),
                    fontSize = TextUnit(18f, TextUnitType.Sp),
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

    private fun createFullTextAlertDialog(text: String, context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.new_dialog, null)

        val ad = AlertDialog.Builder(context)
            .setView(view)
            .create()

        val binding = NewDialogBinding.bind(view)
        binding.textBody.text = text

        if (ad.window != null)
            ad.window!!.setBackgroundDrawable(ColorDrawable(0))

        ad.show()
    }
}