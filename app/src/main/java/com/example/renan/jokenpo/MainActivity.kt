package com.example.renan.jokenpo

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

enum class Result{
    WON,
    LOST,
    DREW
}

class MainActivity : AppCompatActivity() {
    private val images = arrayOf( R.drawable.pedra,R.drawable.papel, R.drawable.tesoura)
    private val idsViews = arrayOf( R.id.imageRock,R.id.imagePaper, R.id.imageScissors)

    private val imageDefaultScaleX = 1.0f
    private val imageDefaultScaleY = 1.0f

    private val imageSelectedScaleX = 1.3f
    private val imageSelectedScaleY = 1.3f


    private val message: Map<Result, String> = mapOf(
        Result.DREW to "Empatou",
        Result.LOST to "Perdeu",
        Result.WON to "Parabêns você Ganhou!"
    )
    private val matrix = arrayOf(
        arrayOf(Result.DREW,Result.LOST, Result.WON),
        arrayOf(Result.WON,Result.DREW, Result.LOST),
        arrayOf(Result.LOST,Result.WON, Result.DREW),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun selectedOption(view: View) {
        val userPlay = view.tag.toString().toInt()
        val playMachine = Random.nextInt(2)

        val imageMachine = findViewById<ImageView>(R.id.imageMachine)

        imageMachine.setImageResource(images[playMachine])


        val result = matrix[userPlay][playMachine]
        val messageResult = message[result]

        val textView = findViewById<TextView>(R.id.textResult)

        val color = if (result == Result.WON) Color.parseColor("#00FF00") else Color.parseColor("#FF0000")
        textView.setTextColor(color)
        textView.text = messageResult

        setDisplay(result,userPlay)
    }


   private fun setDisplay(resultGamer: Result, userPlay: Int){
        val textView = findViewById<TextView>(R.id.textResult)
        displaySelected(userPlay)
        val color = if (resultGamer == Result.WON) Color.parseColor("#00FF00") else Color.parseColor("#FF0000")
        textView.setTextColor(color)
    }

    private fun displaySelected(userPlay: Int){
        val imageUser = findViewById<ImageView>(idsViews[userPlay])
        imageUser.scaleX = imageSelectedScaleX
        imageUser.scaleY = imageSelectedScaleY

       idsViews.indices.map {
           if(!it.equals(userPlay)){
               val image = findViewById<ImageView>(idsViews[it])
               image.scaleX = imageDefaultScaleX
               image.scaleY = imageDefaultScaleY
           }
       }
    }

}