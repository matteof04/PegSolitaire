/*
 * Copyright (C) 2022 Matteo Franceschini <matteof5730@gmail.com>
 *
 * This file is part of PegSolitaire.
 * PegSolitaire is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * PegSolitaire is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with PegSolitaire.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.matteof04.pegsolitaire

enum class State {EMPTY, FULL, BLOCKED}
enum class Move {UP, DOWN, RIGHT, LEFT}

class Chessboard {
    private val chessboard = Array(7) {Array(7) { State.BLOCKED } }
    init {
        for (x in 2..4){
            for (i in chessboard[x].indices){
                chessboard[x][i] = State.FULL
            }
        }
        chessboard[0] = arrayOf(
            State.BLOCKED,
            State.BLOCKED,
            State.FULL,
            State.FULL,
            State.FULL,
            State.BLOCKED,
            State.BLOCKED
        )
        chessboard[1] = arrayOf(
            State.BLOCKED,
            State.BLOCKED,
            State.FULL,
            State.FULL,
            State.FULL,
            State.BLOCKED,
            State.BLOCKED
        )
        chessboard[5] = arrayOf(
            State.BLOCKED,
            State.BLOCKED,
            State.FULL,
            State.FULL,
            State.FULL,
            State.BLOCKED,
            State.BLOCKED
        )
        chessboard[6] = arrayOf(
            State.BLOCKED,
            State.BLOCKED,
            State.FULL,
            State.FULL,
            State.FULL,
            State.BLOCKED,
            State.BLOCKED
        )
        chessboard[3][3] = State.EMPTY
    }
    fun isFinished() : Boolean{
        var counter = 0
        for (i in chessboard) {
            for (j in i){
                if(j == State.FULL){
                    counter++
                }
            }
        }
        return counter == 1
    }
    private fun getState(pos: Position) = chessboard[pos.y][pos.x]
    private fun getFullPosition() : List<Position>{
        val pos = ArrayList<Position>()
        for (i in chessboard.indices){
            for (j in chessboard[i].indices){
                if(chessboard[i][j] == State.FULL){
                    pos.add(Position(j,i))
                }
            }
        }
        return pos
    }
    fun findAvailablePositions() : List<Position>{
        val pos = ArrayList<Position>()
        for (i in getFullPosition()){
            if(findPossibleMoves(i).isNotEmpty()){
                pos.add(i)
            }
        }
        return pos
    }
    fun findPossibleMoves(pos : Position) : List<Move>{
        val x = pos.x
        val y = pos.y
        val moves = ArrayList<Move>()
        if(x+2 <= 6){
            if(getState(Position(x+2, y)) == State.EMPTY && getState(Position(x+1, y)) == State.FULL){
                moves.add(Move.RIGHT)
            }
        }
        if(x-2 >= 0){
            if(getState(Position(x-2, y)) == State.EMPTY && getState(Position(x-1, y)) == State.FULL){
                moves.add(Move.LEFT)
            }
        }
        if(y+2 <= 6){
            if(getState(Position(x, y+2)) == State.EMPTY && getState(Position(x, y+1)) == State.FULL){
                moves.add(Move.DOWN)
            }
        }
        if(y-2 >= 0){
            if(getState(Position(x, y-2)) == State.EMPTY && getState(Position(x, y-1)) == State.FULL){
                moves.add(Move.UP)
            }
        }
        return moves
    }
    fun move(from: Position, movement: Move){
        when(movement){
            Move.UP -> {
                chessboard[from.y-1][from.x] = State.EMPTY
                chessboard[from.y-2][from.x] = State.FULL
                chessboard[from.y][from.x] = State.EMPTY
            }
            Move.DOWN -> {
                chessboard[from.y+1][from.x] = State.EMPTY
                chessboard[from.y+2][from.x] = State.FULL
                chessboard[from.y][from.x] = State.EMPTY
            }
            Move.RIGHT -> {
                chessboard[from.y][from.x+1] = State.EMPTY
                chessboard[from.y][from.x+2] = State.FULL
                chessboard[from.y][from.x] = State.EMPTY
            }
            Move.LEFT -> {
                chessboard[from.y][from.x-1] = State.EMPTY
                chessboard[from.y][from.x-2] = State.FULL
                chessboard[from.y][from.x] = State.EMPTY
            }
        }
    }
    override fun toString(): String {
        var string = ""
        for(i in chessboard){
            for (j in i){
                string += when(j){
                    State.BLOCKED -> "   "
                    State.FULL -> " X "
                    State.EMPTY -> " O "
                }
            }
            string += "\n"
        }
        return string
    }
}