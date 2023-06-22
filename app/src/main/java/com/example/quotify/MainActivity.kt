package com.example.quotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    private val quoteText:TextView
        get() = findViewById(R.id.quoteText)

    private val quoteAuthor:TextView
        get() = findViewById(R.id.quoteAuthor)

    private lateinit var nextButton:TextView
    private lateinit var previousButton: TextView
    //private lateinit var shareButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel= ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)

        setQuote(mainViewModel.getQuote())

        nextButton=findViewById(R.id.nextQuote)
        nextButton.setOnClickListener {
            setQuote(mainViewModel.nextQuote())
        }

        previousButton=findViewById(R.id.previousQuoteTV)
        previousButton.setOnClickListener {
            setQuote(mainViewModel.previousQuote())
        }
    }

    fun setQuote(quote: Quote){
        quoteText.text= quote.text
        quoteAuthor.text= quote.author
    }

    fun onShare(view: View) {
        val intent= Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
        startActivity(intent)
    }
}