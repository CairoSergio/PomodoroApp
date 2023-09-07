package com.cairo.daytwopomodoro.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cairo.daytwopomodoro.components.ShortCardModel
import com.cairo.daytwopomodoro.components.Timer
import com.cairo.daytwopomodoro.enums.state
import com.cairo.daytwopomodoro.ui.theme.*


@Composable
fun Home(){

    var pomodoroState by remember {
        mutableStateOf(state.WORKING)
    }

    fun isShortBreak(): Boolean{
        if(pomodoroState == state.SHORT_BREAK){
            return true
        }
        return false
    }


    fun isLongBreak(): Boolean{
        if(pomodoroState == state.LONG_BREAK){
            return true
        }
        return false
    }


    fun isWorking(): Boolean{
        if(pomodoroState == state.WORKING){
            return true
        }
        return false
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(
            horizontal = 20.dp
        )){
        val logoText = buildAnnotatedString {
            append("Pomodoro ")
            withStyle(SpanStyle(color = PrimaryOrange)){
                append("Task")
            }
        }
        Column {
            Spacer(modifier = Modifier.height(35.dp))
            Text(
                text = logoText,
                style = TextStyle(
                    color = TextColor,
                    fontSize = 20.sp,
                    fontFamily = Inter.InterBold
                )
            )
            Text(
                text = "Organize as suas tarefas com a tecnica pomodoro e seja mais produtivo e saudavel",
                style = TextStyle(
                    color = TextColor,
                    fontSize = 10.sp,
                    fontFamily = Inter.InterRegular
                ),
                modifier = Modifier
                    .width(270.dp)
                    .padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                ShortCardModel(text = "Pausa Curta", active = isShortBreak()) {
                    pomodoroState = state.SHORT_BREAK
                }
                ShortCardModel(text = "Trabalhando", active = isWorking()) {
                    pomodoroState = state.WORKING
                }
                ShortCardModel(text = "Pausa Longa", active = isLongBreak()) {
                    pomodoroState = state.LONG_BREAK
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Timer(TimerState = pomodoroState)
            Spacer(modifier = Modifier.height(30.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = PrimaryOrange, shape = Shapes.medium)
            ){
                Row (modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.Center){
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .background(color = PrimaryOrange, shape = Shapes.large),
                        contentAlignment = BiasAlignment(0f,0f)
                        )
                    {
                        Text(
                            text = "+",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 25.sp,
                                fontFamily = Inter.InterBold
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Criar uma nova tarefa",
                        style = TextStyle(
                            color = TextColorLow,
                            fontSize = 15.sp,
                            fontFamily = Inter.InterMedium
                        )
                    )
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomePreview(){
    Home()
}