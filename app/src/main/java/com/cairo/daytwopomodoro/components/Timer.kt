package com.cairo.daytwopomodoro.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cairo.daytwopomodoro.R
import com.cairo.daytwopomodoro.enums.state
import com.cairo.daytwopomodoro.ui.theme.*
import java.util.Timer
import kotlin.concurrent.schedule

@Composable
fun Timer(TimerState: state){

    var isRunning by remember {
        mutableStateOf(false)
    }
    val animatedProgress = remember { Animatable(1f) }

    var minutes by remember {
        mutableStateOf(0)
    }

    var seconds by remember {
        mutableStateOf(0)
    }

    var totalMinutes by remember {
        mutableStateOf(0)
    }

    var totalSeconds by remember {
        mutableStateOf(0)
    }

    var progress by remember {
        mutableStateOf(1f)
    }

    if (!isRunning) {
        if (TimerState == state.WORKING) {
            totalMinutes = 25
        } else if (TimerState == state.LONG_BREAK) {
            totalMinutes = 15
        } else {
            totalMinutes = 5
        }

        totalSeconds = totalMinutes * 60
        minutes = totalMinutes
        seconds = 0
    }
    LaunchedEffect(progress) {
        animatedProgress.animateTo(
            targetValue = progress,
            animationSpec = tween(durationMillis = 1500)
        )
    }
    fun startCountDown() {
        val timer = Timer()
        isRunning = true
        timer.schedule(0, 1000) {
            if (totalSeconds > 0) {
                totalSeconds--
                minutes = totalSeconds / 60
                seconds = totalSeconds % 60
                progress = totalSeconds.toFloat() / (totalMinutes * 60)
            } else {
                isRunning = false
                timer.cancel()
            }
        }
    }

    fun Organizer(): String {
        val minuteOrganized: String = if (minutes < 10) {
            "0$minutes"
        } else {
            minutes.toString()
        }
        val secondOrganized: String = if (seconds < 10) {
            "0$seconds"
        } else {
            seconds.toString()
        }
        return "$minuteOrganized : $secondOrganized"
    }


    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        Box(
            Modifier
                .size(290.dp)
                .background(color = PrimaryOrange, shape = Shapes.large), contentAlignment = Alignment.Center,) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(color = Color.White, shape = Shapes.large), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        text = Organizer(),
                        style = TextStyle(
                            fontFamily = Inter.InterBold,
                            color = TextColor,
                            fontSize = 50.sp
                        )
                    )
                        Box(modifier = Modifier
                            .size(35.dp)
                            .background(color = PrimaryOrange, shape = Shapes.large)
                            .clickable {
                                if (!isRunning) {
                                    startCountDown()
                                }
                            },
                            contentAlignment = Alignment.Center
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.play),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(start = 4.dp))
                        }
                }
                CircularProgressIndicator(
                    progress = 1f,
                    strokeWidth = 5.dp,
                    color = Color(0xFFCACACA),
                    modifier = Modifier.fillMaxSize()
                )
                CircularProgressIndicator(
                    progress = animatedProgress.value,
                    strokeWidth = 5.dp,
                    color = PrimaryOrange,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}


@Preview
@Composable
fun TimerPreview(){
    Timer(TimerState = state.WORKING)
}