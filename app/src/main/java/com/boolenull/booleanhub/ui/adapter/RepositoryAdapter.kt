package com.boolenull.booleanhub.ui.adapter

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.boolenull.booleanhub.R
import com.boolenull.booleanhub.ui.model.RepositoryModel
import kotlinx.android.synthetic.main.layout_repository.view.*

class RepositoryAdapter: RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    private var repositoryList = mutableListOf<RepositoryModel>()
    private var searchList = mutableListOf<RepositoryModel>()
    private lateinit var inflater: LayoutInflater

    fun updateRepositoryList(repositories: List<RepositoryModel>) {
        repositoryList.clear()
        searchList.clear()

        repositoryList.addAll(repositories)
        searchList.addAll(repositories)

        notifyDataSetChanged()
    }

    fun filter(text: String) {
        searchList.clear()
        if (text.isEmpty())
            searchList.addAll(repositoryList)
        else
            repositoryList.forEach {
                if (it.date.contains(text, true) || it.dateUpdate.contains(text, true) || it.language.contains(
                        text,
                        true
                    ) || it.title.contains(text, true)
                ) {
                    searchList.add(it)
                }
            }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_repository, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = searchList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(searchList[holder.adapterPosition])

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: RepositoryModel) {
            with(itemView) {
                tvTitle.text = item.title
                tvLanguage.text = item.language
                tvDate.text = item.date
                tvDateUpdate.text = item.dateUpdate
                layout.setOnClickListener {
                    val uriUrl = Uri.parse(item.link)
                    val intent = Intent(Intent.ACTION_VIEW, uriUrl)
                    inflater.context.startActivity(intent)
                }
            }
        }
    }
}