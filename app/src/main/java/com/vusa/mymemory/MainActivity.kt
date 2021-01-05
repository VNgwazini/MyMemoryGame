package com.vusa.mymemory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vusa.mymemory.models.BoardSize
import com.vusa.mymemory.utils.DEFAULT_ICONS

class MainActivity : AppCompatActivity() {

    private lateinit var  rvBoard: RecyclerView
    private lateinit var tvNumMoves: TextView
    private lateinit var tvNumPairs: TextView

    //initialze boardSize
    private var boardSize: BoardSize = BoardSize.EASY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //refrences to rosources i.e. textview and recycle view
        rvBoard = findViewById(R.id.rvBoard)
        tvNumMoves = findViewById(R.id.tvNumMoves)
        tvNumPairs = findViewById(R.id.tvNumPairs)

        //grab the desired number of icons, after randomizing
        val chosenImages : List<Int> =  DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        //make sure we have two of each image selected
        val randomizedImages : List<Int> = (chosenImages + chosenImages).shuffled()

        //define layout to be dynamically set based on screen size
        rvBoard.adapter = MemoryBoardAdapter(this, boardSize, randomizedImages)
        //makes application effecient by telling the app that the recyclerview size is constant
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager = GridLayoutManager(this, boardSize.getWidth())
    }
}