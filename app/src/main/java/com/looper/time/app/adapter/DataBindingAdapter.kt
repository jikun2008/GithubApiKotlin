package com.looper.time.app.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.looper.time.app.R
import com.looper.time.app.data.GitHubResponse
import com.looper.time.app.databinding.HistoryItemViewBinding

class DataBindingAdapter :
    BaseQuickAdapter<GitHubResponse, BaseDataBindingHolder<HistoryItemViewBinding>>(R.layout.history_item_view) {
    override fun convert(
        holder: BaseDataBindingHolder<HistoryItemViewBinding>,
        item: GitHubResponse
    ) {
        holder.dataBinding?.item = item
    }


}