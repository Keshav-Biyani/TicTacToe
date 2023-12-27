package com.keshav.tictacapp.presentation.Model

sealed class Action {
        object playeAgain :Action()
        data class BoardTape(val cellNo : Int): Action()
}