package com.cairo.daytwopomodoro.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.cairo.daytwopomodoro.ui.theme.*


@Composable
fun ShortCardModel (
    active: Boolean = false,
    text: String,
    onTarget: () -> Unit,
){


    fun chooseBackgroundColor(): Color {
        if(active){
            return PrimaryOrange
        }
        return GreyLow
    }
    fun chooseTextColor(): Color {
        if(active){
            return Color.White
        }
        return TextColorLow
    }

    Box(
        modifier = Modifier
            .clickable {
                onTarget()
            }
            .background(color = chooseBackgroundColor(), shape = Shapes.medium),
    ){
        Text(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 8.dp)
            ,
            text = text,
            style = TextStyle(
                fontSize = 14.sp,
                color = chooseTextColor(),
                fontFamily = Inter.InterBold
            )
        )

    }
}