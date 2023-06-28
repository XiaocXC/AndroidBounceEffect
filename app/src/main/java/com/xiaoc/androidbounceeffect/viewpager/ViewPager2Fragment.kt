package com.xiaoc.androidbounceeffect.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.xiaoc.androidbounceeffect.R
import com.xiaoc.androidbounceeffect.databinding.FragmentRecyclerViewBinding
import com.xiaoc.androidbounceeffect.databinding.FragmentViewPagerBinding
import com.xiaoc.androidbounceeffect.edgeeffect.setBounceEdgeEffect
import com.xiaoc.androidbounceeffect.recyclerview.RecyclerViewFragment

/**
 * @author Xiaoc
 * @since  2023-06-28
 *
 * ViewPager2回弹Fragment
 **/
class ViewPager2Fragment: Fragment() {

    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // 设置回弹
        binding.viewPager.setBounceEdgeEffect()

        binding.viewPager.adapter = object: FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return 5
            }

            override fun createFragment(position: Int): Fragment {
                return RecyclerViewFragment()
            }

        }
    }
}