package com.keshav.tictacapp.presentation.Presentor

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme


@Composable
 fun DrawCross() {
    val animVal = remember { Animatable(0f) }
    val animVal2 = remember { Animatable(0f) }
    LaunchedEffect(animVal) {
        animVal.animateTo(targetValue = 1f, animationSpec = tween(easing = LinearEasing))
        animVal2.animateTo(targetValue = 1f, animationSpec = tween(easing = LinearEasing))
    }
    val color = MaterialTheme.colors.secondary
    val figureWidth = (10.dp).value
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(animVal.value * size.width, animVal.value * size.height),
            strokeWidth = figureWidth
        )
        drawLine(
            color = color,
            start = Offset(size.width, 0f),
            end = Offset(size.width - (animVal2.value * size.width), animVal2.value * size.height),
            strokeWidth = figureWidth
        )
    }
}
@Preview
@Composable
fun Cross() {
    Canvas(
        modifier = Modifier
            .size(60.dp)
            .padding(5.dp)
    ) {
        drawLine(
            color = Color.Cyan,
            strokeWidth = 20f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = size.height)
        )
        drawLine(
            color = Color.Cyan,
            strokeWidth = 20f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height),
            end = Offset(x = size.width, y = 0f)
        )
    }
}