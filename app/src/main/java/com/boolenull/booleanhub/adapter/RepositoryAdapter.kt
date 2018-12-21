package com.boolenull.booleanhub.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.boolenull.booleanhub.R
import com.boolenull.booleanhub.model.RepositoryModel
import kotlinx.android.synthetic.main.layout_repository.view.*

class RepositoryAdapter() : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    private var repositoryList = mutableListOf<RepositoryModel>()

    fun updateRepositoryList(list: MutableList<RepositoryModel>) {
        repositoryList.clear()
        repositoryList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_repository, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = repositoryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(repositoryList[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: RepositoryModel) {
            with(itemView) {
                tvTitle.text = item.title
                tvLanguage.text = item.language
                tvDate.text = item.date
                tvDateUpdate.text = item.dateUpdate
            }
        }
    }
}