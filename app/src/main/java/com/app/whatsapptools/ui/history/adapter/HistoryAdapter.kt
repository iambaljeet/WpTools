package com.app.whatsapptools.ui.history.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.whatsapptools.R
import com.app.whatsapptools.callback.RecyclerViewItemClickCallback
import com.app.whatsapptools.database.entitity.DiffUtilMessagesTextEntity
import com.app.whatsapptools.database.entitity.MessagesTextEntity
import kotlinx.android.synthetic.main.list_item_messages.view.*

class HistoryAdapter(private var recyclerViewItemClickCallback: RecyclerViewItemClickCallback): ListAdapter<MessagesTextEntity, HistoryViewHolder>(DiffUtilMessagesTextEntity()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_messages, parent, false)
        return HistoryViewHolder(itemView = itemView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(item = getItem(position), callback = recyclerViewItemClickCallback)
    }
}

class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private lateinit var recyclerViewItemClickCallback: RecyclerViewItemClickCallback

    fun bind(
        item: MessagesTextEntity?,
        callback: RecyclerViewItemClickCallback
    ) {
        this.recyclerViewItemClickCallback = callback

        itemView.text_view_recent_messages.text = item?.messageText
        itemView.text_view_number.text = "(${item?.countryCode}) ${item?.phoneNumber}"

        itemView.container_main.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        recyclerViewItemClickCallback.onItemClick(view = v, position = adapterPosition)
    }
}