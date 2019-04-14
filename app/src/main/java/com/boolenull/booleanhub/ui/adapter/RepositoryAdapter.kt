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

    private var repositoryList = listOf<RepositoryModel>()
    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_repository, parent, false))

    override fun getItemCount(): Int = repositoryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(repositoryList[holder.adapterPosition])

    fun updateRepositoryList(repositories: List<RepositoryModel>) {
        repositoryList = repositories
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item: RepositoryModel) {
            val layoutOnClickListener = View.OnClickListener {
                val uriUrl = Uri.parse(item.link)
                val intent = Intent(Intent.ACTION_VIEW, uriUrl)
                inflater.context.startActivity(intent)
            }

            with(itemView) {
                tvTitle.text = item.title
                tvLanguage.text = item.language
                tvDate.text = item.date
                tvDateUpdate.text = item.dateUpdate
                layout.setOnClickListener(layoutOnClickListener)
            }
        }
    }
}