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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import kotlin.math.min

@Preview
@Composable
fun Circle() {
    Canvas(
        modifier = Modifier
            .size(60.dp)
            .padding(5.dp)
    ) {
        drawCircle(
            color = Color.Blue,
            style = Stroke(width = 20f)
        )
    }
}
@Preview
@Composable
private fun DrawCircle() {
    val color = MaterialTheme.colors.secondaryVariant
    val animateFloat = remember { Animatable(0f) }
    val circleMargin = (8.dp).value
    val figureWidth = (10.dp).value
    LaunchedEffect(animateFloat) {
        animateFloat.animateTo(targetValue = 1f, animationSpec = tween(easing = LinearEasing))
    }
    Canvas(modifier = Modifier.fillMaxSize()) {
        val sideSize = min(size.width, size.height)
        val diameter = sideSize - circleMargin
        drawArc(
            color = color,
            startAngle = 0f,
            sweepAngle = 360f * animateFloat.value,
            useCenter = false,
            topLeft = Offset((size.width - diameter) / 2, (size.width - diameter) / 2),
            size = Size(diameter, diameter),
            style = Stroke(figureWidth),
        )
    }
}


