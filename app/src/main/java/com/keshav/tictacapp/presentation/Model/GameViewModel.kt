package com.keshav.tictacapp.presentation.Model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    var stateOfGame by mutableStateOf(GameState())
    val boardCellItme : MutableMap<Int,BoardCellVaIue> = mutableMapOf(
        1 to BoardCellVaIue.NONE,
        2 to BoardCellVaIue.NONE,
        3 to BoardCellVaIue.NONE,
        4 to BoardCellVaIue.NONE,
        5 to BoardCellVaIue.NONE,
        6 to BoardCellVaIue.NONE,
        7 to BoardCellVaIue.NONE,
        8 to BoardCellVaIue.NONE,
        9 to BoardCellVaIue.NONE

    )


    fun onAction(action : Action){
        when(action){
            is Action.BoardTape->{
                markBoard(action.cellNo)

            }
            is Action.playeAgain->{
                gameReset()
            }
        }

    }

    private fun gameReset() {
        boardCellItme.forEach{(cellNo,)->
            boardCellItme[cellNo]=BoardCellVaIue.NONE

        }
        stateOfGame = stateOfGame.copy(
            resultText = "Player Won",
            currentTurn = BoardCellVaIue.CIRCLE,
            victoryType = VictoryType.NONE,
            hasWon = false,
            isDraw = false

        )
    }

    private fun markBoard(cellNo: Int) {
        if (boardCellItme[cellNo]!=BoardCellVaIue.NONE){
            return
        }
        if(stateOfGame.currentTurn == BoardCellVaIue.CIRCLE){
            boardCellItme[cellNo]=BoardCellVaIue.CIRCLE
            if(checkWinner(BoardCellVaIue.CIRCLE)){
                stateOfGame= stateOfGame.copy(
                    resultText = "Player O Won",
                    currentTurn = BoardCellVaIue.NONE,
                    hasWon = true
                )
            }else if(isBoardFull()){
                stateOfGame= stateOfGame.copy(
                    resultText = "Game Draw",
                    isDraw = true

                    )
            }else {
                stateOfGame = stateOfGame.copy(
                    resultText = "Player X Turn",
                    currentTurn = BoardCellVaIue.CROSS
                )
            }
        }else if(stateOfGame.currentTurn == BoardCellVaIue.CROSS){
            boardCellItme[cellNo]=BoardCellVaIue.CROSS
            if(checkWinner(BoardCellVaIue.CROSS)){
                stateOfGame= stateOfGame.copy(
                    resultText = "Player X Won",
                    currentTurn = BoardCellVaIue.NONE,
                    hasWon = true
                )
            }else if(isBoardFull()){
                stateOfGame= stateOfGame.copy(

                    resultText = "Game Draw",
                    isDraw = true


                )

            }else{
                stateOfGame =stateOfGame.copy(resultText = "Player 0 Turn",
                    currentTurn = BoardCellVaIue.CIRCLE)
            }

        }

    }


    private fun isBoardFull(): Boolean {
        return !boardCellItme.containsValue(BoardCellVaIue.NONE)
    }

    private fun checkWinner(cellValue: BoardCellVaIue): Boolean {
        when{
            (boardCellItme[1]== cellValue && boardCellItme[2]== cellValue && boardCellItme[3]==cellValue)->{
                stateOfGame = stateOfGame.copy(
                    victoryType = VictoryType.HORIZONTAL1
                )
                return true
            }
            (boardCellItme[4]== cellValue && boardCellItme[5]== cellValue && boardCellItme[6]==cellValue)->{
                stateOfGame = stateOfGame.copy(
                    victoryType = VictoryType.HORIZONTAL2
                )
                return true
            }
            (boardCellItme[7]== cellValue && boardCellItme[8]== cellValue && boardCellItme[9]==cellValue)->{
                stateOfGame = stateOfGame.copy(
                    victoryType = VictoryType.HORIZONTAL3
                )
                return true
            }
            (boardCellItme[1]== cellValue && boardCellItme[4]== cellValue && boardCellItme[7]==cellValue)->{
                stateOfGame = stateOfGame.copy(
                    victoryType = VictoryType.VERTICAL1
                )
                return true
            }
            (boardCellItme[2]== cellValue && boardCellItme[5]== cellValue && boardCellItme[8]==cellValue)->{
                stateOfGame = stateOfGame.copy(
                    victoryType = VictoryType.VERTICAL2
                )
                return true
            }
            (boardCellItme[3]== cellValue && boardCellItme[6]== cellValue && boardCellItme[9]==cellValue)->{
                stateOfGame = stateOfGame.copy(
                    victoryType = VictoryType.VERTICAL3
                )
                return true
            }
            (boardCellItme[1]== cellValue && boardCellItme[5]== cellValue && boardCellItme[9]==cellValue)->{
                stateOfGame = stateOfGame.copy(
                    victoryType = VictoryType.DIAGONAL1
                )
                return true
            }
            (boardCellItme[3]== cellValue && boardCellItme[5]== cellValue && boardCellItme[7]==cellValue)->{
                stateOfGame = stateOfGame.copy(
                    victoryType = VictoryType.DIAGONAL2
                )
                return true
            }


        }
        return false

    }
}