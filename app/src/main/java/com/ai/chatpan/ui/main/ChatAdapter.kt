package com.ai.chatpan.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ai.chatpan.R
import com.ai.chatpan.data.bean.BaseChatBean
import com.ai.chatpan.data.bean.ChatPanBean
import com.ai.chatpan.databinding.ItemAnswerBinding
import com.ai.chatpan.databinding.ItemHistoryBinding
import com.ai.chatpan.databinding.ItemQuestionBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseMultiItemAdapter

class ChatAdapter(data: ArrayList<BaseChatBean>) : BaseMultiItemAdapter<BaseChatBean>() {

    // 类型 1 的 viewholder
    class ItemAnswer(var viewAnswerBinding: ItemAnswerBinding) :
        RecyclerView.ViewHolder(viewAnswerBinding.root)

    // 类型 2 的 viewholder
    class ItemAsk(var viewQuestionBinding: ItemQuestionBinding) :
        RecyclerView.ViewHolder(viewQuestionBinding.root)

    class ItemHistory(var viewItemHistory: ItemHistoryBinding) :
        RecyclerView.ViewHolder(viewItemHistory.root)


    init {

        addItemType(
            ITEM_TYPE_ASK,
            object : OnMultiItemAdapterListener<BaseChatBean, ItemAsk> { // 类型 1
                override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): ItemAsk {
                    // 创建 viewholder
                    val viewBinding2 =
                        ItemQuestionBinding.inflate(LayoutInflater.from(context), parent, false)

                    return ItemAsk(viewBinding2)
                }

                override fun onBind(holder: ItemAsk, position: Int, item: BaseChatBean?) {
                    // 绑定 item 数据
                    holder.viewQuestionBinding.tvQuestion.text = item!!.question

                }
            }).addItemType(
            ITEM_TYPE_ANSWER,
            object : OnMultiItemAdapterListener<BaseChatBean, ItemAnswer> { // 类型 2
                override fun onCreate(
                    context: Context,
                    parent: ViewGroup,
                    viewType: Int
                ): ItemAnswer {
                    // 创建 viewholder
                    val viewBinding =
                        ItemAnswerBinding.inflate(LayoutInflater.from(context), parent, false)

                    return ItemAnswer(viewBinding)
                }

                override fun onBind(holder: ItemAnswer, position: Int, item: BaseChatBean?) {
//                    if (item!!.answer.isEmpty()) {
//                        holder.viewAnswerBinding.tvAnswer.text = context.getString( R.string.answer_null)
//                    } else {
                        // 绑定 item 数据
                        holder.viewAnswerBinding.tvAnswer.text = item!!.answer
//                    }
                }

            })
            .addItemType(ITEM_TYPE_HISTORY,
                object : OnMultiItemAdapterListener<BaseChatBean, ItemHistory> {
                    override fun onBind(holder: ItemHistory, position: Int, item: BaseChatBean?) {
                        //                        // 绑定 item 数据
                        holder.viewItemHistory.tvQuestion.text = item!!.question.trim()
//                        if (item!!.answer.isEmpty()) {
//                            holder.viewItemHistory.tvAnswer.text = context.getString( R.string.answer_null)
//                        } else {
//                            // 绑定 item 数据
                            holder.viewItemHistory.tvAnswer.text = item!!.answer.trim()
//                        }
//                        holder.viewItemHistory.tvAnswer.text = item!!.answer.trim()

                    }

                    override fun onCreate(
                        context: Context,
                        parent: ViewGroup,
                        viewType: Int
                    ): ItemHistory {
                        // 创建 viewholder
                        val viewBinding =
                            ItemHistoryBinding.inflate(LayoutInflater.from(context), parent, false)
                        return ItemHistory(viewBinding)
                    }

                })
            .onItemViewType { position, list -> // 根据数据，返回对应的 ItemViewType
                if (list[position].type == 0) {
                    ITEM_TYPE_ASK
                } else if (list[position].type == 1) {
                    ITEM_TYPE_ANSWER
                } else {
                    ITEM_TYPE_HISTORY
                }
            }
    }


    companion object {
        private const val ITEM_TYPE_ASK = 0
        private const val ITEM_TYPE_ANSWER = 1
        private const val ITEM_TYPE_HISTORY = 2
    }
}