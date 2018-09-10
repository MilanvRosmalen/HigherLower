package com.example.milan.higherlower

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.*
import java.util.ArrayList
import android.R.attr.duration
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Declare variables
        var mTextScore: TextView
        var mTextHighScore: TextView
        var currentImageIndex: Int
        var mLastThrow: Int = 0
        var newThrow: Int = 0
        var mScore: Int = 0
        var mHighScore: Int = 0
        val mButUp: ImageButton
        val mButDown: ImageButton
        val mImageView: ImageView
        val mImageNames: IntArray
        var mHistory: ArrayList<History>
        var mAdapter: ArrayAdapter<History>
        var mListView: ListView

        //initialize variables
        mTextScore = findViewById(R.id.textScore)
        mTextHighScore = findViewById(R.id.textHighScore)
        mButUp = findViewById(R.id.butUp)
        mButDown = findViewById(R.id.butDown)
        mImageView = findViewById(R.id.ImageView1)
        mListView = findViewById(R.id.Listview1)
        mHistory = ArrayList<History>()
        mAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mHistory)
        mListView.adapter = mAdapter
        mImageNames = intArrayOf(R.drawable.dobbel_1, R.drawable.dobbel_2, R.drawable.dobbel_3, R.drawable.dobbel_4, R.drawable.dobbel_5, R.drawable.dobbel_6)

        //function to randomly roll the dice and filling out the listview
        fun rollDice(): Int {
            //rolling random dice
            currentImageIndex = (Math.random() * ((5) + 1)).toInt()
            mImageView.setImageResource(mImageNames[currentImageIndex])

            //adding items to listview
            val newHistory = History("Your throw was " + (currentImageIndex + 1).toString())
            mHistory.add(newHistory)
            mAdapter.notifyDataSetChanged()

            return currentImageIndex
        }

        //function to check if the answer was good or false and determine highscore
        fun scoreCheck() {
            if (mScore >= mHighScore) {
                mHighScore = mScore
            }
            val score = "Score " + mScore.toString()
            val highScore = "HighScore " + mHighScore.toString()
            mTextScore.text = score
            mTextHighScore.text = highScore

        }

        //checks if the up button is pressed
        mButUp.setOnClickListener {
            newThrow = rollDice()
            if (newThrow >= mLastThrow) {
                mScore++
                Snackbar.make(findViewById(android.R.id.content), "Goed !", Snackbar.LENGTH_SHORT).show()
            } else {

                Snackbar.make(findViewById(android.R.id.content), "Fout !", Snackbar.LENGTH_SHORT).show()
                mScore = 0
            }
            mLastThrow = newThrow
            scoreCheck()
        }

        //checks if the down button is pressed
        mButDown.setOnClickListener {
            newThrow = rollDice()
            if (newThrow <= mLastThrow) {
                mScore++
                Snackbar.make(findViewById(android.R.id.content), "Goed !", Snackbar.LENGTH_SHORT).show()
            } else {
                mScore = 0
                Snackbar.make(findViewById(android.R.id.content), "Fout !", Snackbar.LENGTH_SHORT).show()
            }
            mLastThrow = newThrow
            scoreCheck()
        }


    }

}
