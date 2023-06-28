package com.xiaoc.androidbounceeffect.nested

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.xiaoc.androidbounceeffect.R
import com.xiaoc.androidbounceeffect.databinding.FragmentNestedScrollerViewBinding
import com.xiaoc.androidbounceeffect.databinding.FragmentRecyclerViewBinding
import com.xiaoc.androidbounceeffect.databinding.ItemRecyclerViewBinding
import com.xiaoc.androidbounceeffect.edgeeffect.setBounceEdgeEffect

/**
 * @author Xiaoc
 * @since  2023-06-28
 *
 * NestedScrollerView回弹Fragment
 **/
class NestedScrollerVIewFragment: Fragment() {

    private lateinit var binding: FragmentNestedScrollerViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentNestedScrollerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // 设置回弹
        binding.scrollerView.setBounceEdgeEffect()

        // 生成50个
        for(index in 0..50){
            val itemView = ItemRecyclerViewBinding.inflate(layoutInflater)
            itemView.tvInfo.text = "NestedScrollerViewItem"
            itemView.tvTitle.text = "NestedScrollerViewItem"
            binding.container.addView(itemView.root)
        }
    }
}