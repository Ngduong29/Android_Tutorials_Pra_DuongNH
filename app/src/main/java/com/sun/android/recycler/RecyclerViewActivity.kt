package com.sun.android.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sun.android.databinding.ActivityRecyclerViewBinding
import java.util.*

class RecyclerViewActivity : AppCompatActivity() {

    private var mWordList: MutableList<String> = mutableListOf()
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: WordListAdapter? = null
    val binding by lazy { ActivityRecyclerViewBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        for (i: Int in 0..20){
            mWordList.add("word $i")
        }
       mRecyclerView = binding.recyclerView
        mAdapter = WordListAdapter(mWordList)
        mRecyclerView?.adapter = mAdapter

        binding.fab.setOnClickListener {
            val wordListSize = mWordList.size
            mWordList.add("+ Word $wordListSize")
            mRecyclerView?.adapter?.notifyItemInserted(wordListSize)
            mRecyclerView?.smoothScrollToPosition(wordListSize)
        }

    }
}
