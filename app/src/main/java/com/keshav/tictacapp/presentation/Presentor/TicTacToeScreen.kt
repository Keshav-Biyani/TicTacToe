package com.keshav.tictacapp.presentation.Presentor

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.dialog.Alert
import com.keshav.tictacapp.presentation.Model.Action
import com.keshav.tictacapp.presentation.Model.BoardCellVaIue
import com.keshav.tictacapp.presentation.Model.GameState
import com.keshav.tictacapp.presentation.Model.GameViewModel
import com.keshav.tictacapp.presentation.Model.VictoryType
import kotlinx.coroutines.delay

@Composable
fun TicTacToeScreen(
    viewModel: GameViewModel
){
    var state = viewModel.stateOfGame
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        BoardBase()
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .aspectRatio(1f)
            ,columns = GridCells.Fixed(3)){
            viewModel.boardCellItme.forEach{(cellNo,boardCellValue)->
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clickable {
                                viewModel.onAction(Action.BoardTape(cellNo))

                            },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center

                    ) {
                        AnimatedVisibility(visible = viewModel.boardCellItme[cellNo] != BoardCellVaIue.NONE,
                            enter = scaleIn(tween(1000))
                        ) {


                            if (boardCellValue == BoardCellVaIue.CIRCLE) {
                                Circle()

                            } else if (boardCellValue == BoardCellVaIue.CROSS) {
                                Cross()

                            }
                        }
                    }
                }
            }
        }
        AnimatedVisibility(visible = state.hasWon != false,
            enter = fadeIn(tween(1000))
        ){
            DrawWinLine(state)
            WearableDialog(viewModel = viewModel)
        }
        if(state.isDraw){
            WearableDialog(viewModel = viewModel)
        }

    }
}

@Composable
fun DrawWinLine(state: GameState) {
    when(state.victoryType){
        VictoryType.HORIZONTAL1-> WinHorizontalLine1()
        VictoryType.HORIZONTAL2-> WinHorizontalLine2()
        VictoryType.HORIZONTAL3-> WinHorizontalLine3()
        VictoryType.VERTICAL1-> WinVerticalLine1()
        VictoryType.VERTICAL2-> WinVerticalLine2()
        VictoryType.VERTICAL3-> WinVerticalLine3()
        VictoryType.DIAGONAL1-> WinDiagonalLine1()
        VictoryType.DIAGONAL2-> WinDiagonalLine2()
        VictoryType.NONE->{}
    }

}


@Composable
fun WearableDialog(
    viewModel: GameViewModel
) {

    Alert(
        title = {
                Text(text =viewModel.stateOfGame.resultText)
        },
        negativeButton = {
          Button(onClick = {
            viewModel.onAction(Action.playeAgain)
          }) {
              Text(text = "Replay")
          }
        },
        positiveButton = {
            Button(onClick = {
            System.exit(0)
            }) {
                Text(text = "Exit")
            }

        }

    )
}