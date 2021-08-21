package com.esvisoftech.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.esvisoftech.tictactoe.databinding.ActivityPlayBinding

class play : AppCompatActivity(),View.OnClickListener {
    lateinit var binding:ActivityPlayBinding
    var Player = true;
var name1:String?=""
    var name2:String?=""
    var Turn_Count=0;
    var boardStatus=Array(3){IntArray(3)}
    lateinit var board:Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        board= arrayOf(arrayOf(binding.btn1,binding.btn2,binding.btn3),
            arrayOf(binding.btn4,binding.btn5,binding.btn6),
            arrayOf(binding.btn7,binding.btn8,binding.btn9))
  name1= intent.getStringExtra("Name1")
    name2= intent.getStringExtra("Name2")
        for(i in board){
            for (button:Button in i){
                button.setOnClickListener(this)
            }
        }
        binding.displaytext.text=name1+"'s turn"
        initializeBoardStatus()
        binding.resetbtn.setOnClickListener {
            Turn_Count=0;
            Player=true;
            initializeBoardStatus()
            updateDisplay("$name1's turn")
        }
    }

    private fun initializeBoardStatus() {
        for(i:Int in 0..2)
        {
            for (j:Int in 0..2){
                boardStatus[i][j]=-1

            }
            for(i in board){
                for (button:Button in i){
                    button.isEnabled=true;
                    button.text=""
                }
            }
        }
    }

    override fun onClick(view: View) {

        when(view.id){
            R.id.btn1->{
                updateValue(row = 0,col = 0,player=Player)}
            R.id.btn2->{updateValue(row = 0,col = 1,player=Player)}
            R.id.btn3->{updateValue(row = 0,col = 2,player=Player)}
            R.id.btn4->{updateValue(row = 1,col = 0,player=Player)}
            R.id.btn5->{updateValue(row = 1,col = 1,player=Player)}
            R.id.btn6->{updateValue(row = 1,col = 2,player=Player)}
            R.id.btn7->{updateValue(row = 2,col = 0,player=Player)}
            R.id.btn8->{updateValue(row = 2,col = 1,player=Player)}
            R.id.btn9->{updateValue(row = 2,col = 2,player=Player)}
        }
        Player=!Player
        Turn_Count++
        if(Player){
            updateDisplay("$name1's turn")
        }else{
            updateDisplay("$name2's turn")
        }
        if(Turn_Count==9){
            updateDisplay("Game Drawn")
        }
        checkWinner()
    }
    fun checkWinner(){
        //horizontal row
        for(i in 0..2){
            if(boardStatus[i][0]==boardStatus[i][1] && boardStatus[i][0]==boardStatus[i][2]){
                if (boardStatus[i][0]==1){
                    updateDisplay("\uD83C\uDF89 $name1 Won \uD83C\uDF89")
                    break
                }else if(boardStatus[i][0]==0){
                    updateDisplay("\uD83C\uDF89 $name2 Won \uD83C\uDF89")
                }
            }
        }
        //vertical columns
        for(i in 0..2){
            if(boardStatus[0][i]==boardStatus[1][i]&& boardStatus[0][i]==boardStatus[2][i]){
                if (boardStatus[0][i]==1){
                    updateDisplay("\uD83C\uDF89 $name1 Won \uD83C\uDF89")
                    break
                }else if(boardStatus[0][i]==0){
                    updateDisplay("\uD83C\uDF89 $name2 Won \uD83C\uDF89")
                }
            }
        }
        //diagonal
        if(boardStatus[0][0]==boardStatus[1][1]&&boardStatus[0][0]==boardStatus[2][2]){
            if (boardStatus[0][0]==1){
                updateDisplay("\uD83C\uDF89 $name1 Won \uD83C\uDF89")
            }else if(boardStatus[0][0]==0){
                updateDisplay("\uD83C\uDF89 $name2 Won \uD83C\uDF89")
            }
        }
        if(boardStatus[0][2]==boardStatus[1][1]&&boardStatus[0][2]==boardStatus[2][0]){
            if (boardStatus[0][2]==1){
                updateDisplay("\uD83C\uDF89 $name1 Won \uD83C\uDF89")
            }else if(boardStatus[0][2]==0){
                updateDisplay("\uD83C\uDF89 $name2 Won \uD83C\uDF89")
            }
        }
    }

    private fun updateDisplay(settxt: String) {
        binding.displaytext.text=settxt
        if(settxt.contains("Won")){
            disableButton()
        }
    }

    fun disableButton(){
        for (i in board){
            for (button:Button in i){
                button.isEnabled=false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text=if(Player) "X" else "O"
        val value=if(Player) 1 else 0

        board[row][col].apply {
            isEnabled=false
            setText(text)
        }
        boardStatus[row][col]=value
    }
}