package com.ai.chatpan.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ai.chatpan.data.bean.ChatPanBean
import com.ai.chatpan.databinding.ItemAnswerBinding
import com.ai.chatpan.databinding.ItemQuestionBinding
import com.chad.library.adapter.base.BaseMultiItemAdapter

class ChatAdapter(data: ArrayList<ChatPanBean>) : BaseMultiItemAdapter<ChatPanBean>() {

    // 类型 1 的 viewholder
    class ItemAnswer(var viewAnswerBinding: ItemAnswerBinding) :
        RecyclerView.ViewHolder(viewAnswerBinding.root)

    // 类型 2 的 viewholder
    class ItemAsk(var viewQuestionBinding: ItemQuestionBinding) :
        RecyclerView.ViewHolder(viewQuestionBinding.root)


    init {

        addItemType(
            ITEM_TYPE_ASK,
            object : OnMultiItemAdapterListener<ChatPanBean, ItemAsk> { // 类型 1
                override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): ItemAsk {
                    // 创建 viewholder
                    val viewBinding2 =
                        ItemQuestionBinding.inflate(LayoutInflater.from(context), parent, false)
                    return ItemAsk(viewBinding2)
                }

                override fun onBind(holder: ItemAsk, position: Int, item: ChatPanBean?) {
                    // 绑定 item 数据
                }
            }).addItemType(
            ITEM_TYPE_ANSWER,
            object : OnMultiItemAdapterListener<ChatPanBean, ItemAnswer> { // 类型 2
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

                override fun onBind(holder: ItemAnswer, position: Int, item: ChatPanBean?) {
                    // 绑定 item 数据
                }

                override fun isFullSpanItem(itemType: Int): Boolean {
                    // 使用GridLayoutManager时，此类型的 item 是否是满跨度
                    return true;
                }

            }).onItemViewType { position, list -> // 根据数据，返回对应的 ItemViewType
            if (list[position].answer.equals("1111")) {
                ITEM_TYPE_ASK
            } else {
                ITEM_TYPE_ANSWER

            }
        }
    }


    companion object {
        private const val ITEM_TYPE_ASK = 0
        private const val ITEM_TYPE_ANSWER = 1
    }
}