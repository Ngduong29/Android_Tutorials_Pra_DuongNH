package com.sun.android.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun.android.databinding.WordlistItemBinding
import java.util.*


class WordListAdapter( wordList: MutableList<String>): RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {
    var mWordList: MutableList<String> = wordList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListAdapter.WordViewHolder {
       val binding = WordlistItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return WordViewHolder(binding, this)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val mCurrent: String = mWordList[position]
        holder.wordItemView.setText(mCurrent)
    }

    override fun getItemCount(): Int {
        return mWordList.size
    }

    inner class WordViewHolder(binding: WordlistItemBinding, adapter: WordListAdapter): RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        var wordItemView = binding.word
        var mAdapter: WordListAdapter = adapter
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val mPosition: Int = layoutPosition
            val element: String = mWordList[mPosition]
            mWordList[mPosition] = "Clicked! $element"
            mAdapter.notifyDataSetChanged()

        }

    }
}
