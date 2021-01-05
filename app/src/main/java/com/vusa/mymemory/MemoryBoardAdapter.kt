package com.vusa.mymemory

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vusa.mymemory.models.BoardSize
import kotlin.math.min

//subclass of recycler view
class MemoryBoardAdapter(
    private val context: Context,
    private val boardSize: BoardSize,
    private val cardImages: List<Int>
) :
        RecyclerView.Adapter<MemoryBoardAdapter.ViewHolder>() {

    //singleton where we define constants to be accessed via containing class
    companion object {
        private const val MARGIN_SIZE = 10
        private const val TAG = "MemoryBoardAdapter"
    }

            //how to create one view of our recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //width of this card will be the parent (full screen) divided by the number of board columns
        val cardWidth : Int = parent.width / boardSize.getWidth() - (2 * MARGIN_SIZE)
        //height of this card will be the parent (full screen) divided by the number of board rows
        val cardHeight : Int = parent.height / boardSize.getHeight() - (2 * MARGIN_SIZE)
        val cardSideLength : Int = min(cardWidth, cardHeight)
        val view:View =  LayoutInflater.from(context).inflate(R.layout.memory_card, parent, false)
        //cast layout params to Margin layout Params
        val layoutParams : ViewGroup.MarginLayoutParams = view.findViewById<CardView>(R.id.cardView).layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.width = cardSideLength
        layoutParams.height = cardSideLength
        layoutParams.setMargins(MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE)
        return ViewHolder(view)
    }

    //how many elements are in our recycler view based on value passed from boardSize
    override fun getItemCount() = boardSize.numCards

    //taking the data at this section and binding it to the view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //grab reference to image button in card view
        private val imageButton = itemView.findViewById<ImageButton>(R.id.imageButton)
        //recognize that we clicked a specific button via its position
        fun bind(position: Int) {
            //set the position of the click to reference the image at that position
            imageButton.setImageResource(cardImages[position])
            imageButton.setOnClickListener{
                Log.i(TAG, "Clicked on position $position")
            }
        }
    }

}
