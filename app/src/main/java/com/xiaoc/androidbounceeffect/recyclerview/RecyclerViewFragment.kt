package com.xiaoc.androidbounceeffect.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.xiaoc.androidbounceeffect.R
import com.xiaoc.androidbounceeffect.databinding.FragmentRecyclerViewBinding
import com.xiaoc.androidbounceeffect.edgeeffect.setBounceEdgeEffect

/**
 * @author Xiaoc
 * @since  2023-06-28
 *
 * RecyclerView回弹Fragment
 **/
class RecyclerViewFragment: Fragment() {

    private lateinit var binding: FragmentRecyclerViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // 设置回弹
        binding.recyclerView.setBounceEdgeEffect()

        binding.recyclerView.adapter = RecyclerAdapter()
    }

    class RecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val rootView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
            return object: RecyclerView.ViewHolder(rootView){}
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        }

        override fun getItemCount(): Int {
            return 30
        }

    }
}