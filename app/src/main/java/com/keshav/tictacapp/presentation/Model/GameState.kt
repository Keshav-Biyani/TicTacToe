package com.keshav.tictacapp.presentation.Model

data class GameState(
    val  resultText : String = "Player O Won",
    val currentTurn: BoardCellVaIue = BoardCellVaIue.CIRCLE,
    val victoryType: VictoryType = VictoryType.NONE,
    val hasWon : Boolean = false,
    val isDraw : Boolean= false
)

enum class BoardCellVaIue {
    CIRCLE,
    CROSS,
    NONE
}
enum class VictoryType{
    HORIZONTAL1,
    HORIZONTAL2,
    HORIZONTAL3,
    VERTICAL1,
    VERTICAL2,
    VERTICAL3,
    DIAGONAL1,
    DIAGONAL2,
    NONE
}
