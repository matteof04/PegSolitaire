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

fun main(){
    println("""
        ComicManager  Copyright (C) 2022  Matteo Franceschini
        This program comes with ABSOLUTELY NO WARRANTY.
        This is free software, and you are welcome to redistribute it
        under certain conditions; type `-l' or '--license' for details.
    """.trimIndent())
    var chessboard = Chessboard()
    var retry = 0
    while (!chessboard.isFinished()){
        val positions = chessboard.findAvailablePositions()
        if(positions.isNotEmpty()){
            val pos = positions[positions.indices.random()]
            val moves = chessboard.findPossibleMoves(pos)
            if(moves.isNotEmpty()){
                chessboard.move(pos, moves[moves.indices.random()])
                println(chessboard.toString())
            }
        }else{
            retry++
            chessboard = Chessboard()
            println("""
                
                ~~~~~~~~~~ REBOOT ~~~~~~~~~~
                
            """.trimIndent())
        }
    }
    println(chessboard.toString())
    println("Failed attempts: $retry")
}