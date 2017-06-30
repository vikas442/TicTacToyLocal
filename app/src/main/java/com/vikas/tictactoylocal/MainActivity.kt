package com.vikas.tictactoylocal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var playerOne = ArrayList<Int>()
    var playerTwo = ArrayList<Int>()
    var activePlayer = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        val btnSelected = view as Button
        var cellId = 0
        when (view.id) {
            R.id.button -> cellId = 1
            R.id.button2 -> cellId = 2
            R.id.button3 -> cellId = 3
            R.id.button4 -> cellId = 4
            R.id.button5 -> cellId = 5
            R.id.button6 -> cellId = 6
            R.id.button7 -> cellId = 7
            R.id.button8 -> cellId = 8
            R.id.button9 -> cellId = 9
        }

//        Toast.makeText(applicationContext, "Button clicked with ID: $cellId", Toast.LENGTH_SHORT).show()
        playGame(cellId, btnSelected)
    }

    fun playGame(cellId: Int, btnSelected: Button) {
        var checkForAutoPlay: Boolean =false
        when (activePlayer) {
            1 -> {
                btnSelected.text = "X"
                btnSelected.setBackgroundResource(R.color.gray)
                playerOne.add(cellId)
                activePlayer = 2
                checkForAutoPlay=true
            }
            2 -> {
                btnSelected.text = "O"
                btnSelected.setBackgroundResource(R.color.dark_gray)
                playerTwo.add(cellId)
                activePlayer = 1
                checkForAutoPlay=false
            }
        }
        btnSelected.isEnabled = false
        val res=checkWinner()
        //Check result and call for auto play
        if (checkForAutoPlay && !res) {
            autoPlay()
        }
    }

    fun checkWinner(): Boolean {
        var winner = -1

        //Row 1
        if (playerOne.contains(1) && playerOne.contains(2) && playerOne.contains(3)) {
            winner = 1
        }
        if (playerTwo.contains(1) && playerTwo.contains(2) && playerTwo.contains(3)) {
            winner = 2
        }

        //Row 2
        if (playerOne.contains(4) && playerOne.contains(5) && playerOne.contains(6)) {
            winner = 1
        }
        if (playerTwo.contains(4) && playerTwo.contains(5) && playerTwo.contains(6)) {
            winner = 2
        }

        //Row 3
        if (playerOne.contains(7) && playerOne.contains(8) && playerOne.contains(9)) {
            winner = 1
        }
        if (playerTwo.contains(7) && playerTwo.contains(8) && playerTwo.contains(9)) {
            winner = 2
        }
        //Col 1
        if (playerOne.contains(1) && playerOne.contains(4) && playerOne.contains(7)) {
            winner = 1
        }
        if (playerTwo.contains(1) && playerTwo.contains(4) && playerTwo.contains(7)) {
            winner = 2
        }

        //Col 2
        if (playerOne.contains(2) && playerOne.contains(5) && playerOne.contains(8)) {
            winner = 1
        }
        if (playerTwo.contains(2) && playerTwo.contains(5) && playerTwo.contains(8)) {
            winner = 2
        }

        //Col 3
        if (playerOne.contains(3) && playerOne.contains(6) && playerOne.contains(9)) {
            winner = 1
        }
        if (playerTwo.contains(3) && playerTwo.contains(6) && playerTwo.contains(9)) {
            winner = 2
        }

        //Dig 1
        if (playerOne.contains(1) && playerOne.contains(5) && playerOne.contains(9)) {
            winner = 1
        }
        if (playerTwo.contains(1) && playerTwo.contains(5) && playerTwo.contains(9)) {
            winner = 2
        }

        //Dig 2
        if (playerOne.contains(3) && playerOne.contains(5) && playerOne.contains(7)) {
            winner = 1
        }
        if (playerTwo.contains(3) && playerTwo.contains(5) && playerTwo.contains(7)) {
            winner = 2
        }

        if (winner != -1) {
            if (winner == 1) {
                Toast.makeText(applicationContext, "Player One wins the game", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Player Two wins the game", Toast.LENGTH_LONG).show()
            }
            return true
        } else if (playerOne.size + playerTwo.size == 9) {
            Toast.makeText(applicationContext, "It's draw", Toast.LENGTH_LONG).show()
            return true
        }
        return false
    }

    fun onReset(view: View) {
        playerOne.clear()
        playerTwo.clear()
        activePlayer = 1
        button.isEnabled = true
        button.text = ""
        button.setBackgroundColor(resources.getColor(R.color.light_gray))
        button2.isEnabled = true
        button2.text = ""
        button2.setBackgroundColor(resources.getColor(R.color.light_gray))
        button3.isEnabled = true
        button3.text = ""
        button3.setBackgroundColor(resources.getColor(R.color.light_gray))
        button4.isEnabled = true
        button4.text = ""
        button4.setBackgroundColor(resources.getColor(R.color.light_gray))
        button5.isEnabled = true
        button5.text = ""
        button5.setBackgroundColor(resources.getColor(R.color.light_gray))
        button6.isEnabled = true
        button6.text = ""
        button6.setBackgroundColor(resources.getColor(R.color.light_gray))
        button7.isEnabled = true
        button7.text = ""
        button7.setBackgroundColor(resources.getColor(R.color.light_gray))
        button8.isEnabled = true
        button8.text = ""
        button8.setBackgroundColor(resources.getColor(R.color.light_gray))
        button9.isEnabled = true
        button9.text = ""
        button9.setBackgroundColor(resources.getColor(R.color.light_gray))
    }

    fun autoPlay() {
        val emptyBlocks = ArrayList<Int>()

        for (cell in 1..9) {  //we can replace to stdlib operation i.e. filter range
            if (!playerOne.contains(cell) && !playerTwo.contains(cell)) {
                emptyBlocks.add(cell)
            }
        }

        val random = Random()
        val randomIndex = random.nextInt(emptyBlocks.size - 0) + 0

        val cellId = emptyBlocks[randomIndex]

        var btnSelected: Button? = null

        when (cellId) {
            1 -> btnSelected = button
            2 -> btnSelected = button2
            3 -> btnSelected = button3
            4 -> btnSelected = button4
            5 -> btnSelected = button5
            6 -> btnSelected = button6
            7 -> btnSelected = button7
            8 -> btnSelected = button8
            9 -> btnSelected = button9
        }
        if (btnSelected != null)
            playGame(cellId, btnSelected)
    }
}
