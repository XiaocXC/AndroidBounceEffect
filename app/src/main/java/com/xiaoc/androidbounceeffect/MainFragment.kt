package com.xiaoc.androidbounceeffect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xiaoc.androidbounceeffect.databinding.FragmentMainBinding
import com.xiaoc.androidbounceeffect.nested.NestedScrollerVIewFragment
import com.xiaoc.androidbounceeffect.recyclerview.RecyclerViewFragment
import com.xiaoc.androidbounceeffect.viewpager.ViewPager2Fragment

class MainFragment: Fragment() {

    private lateinit var binding: FragmentMainBinding

    /**
     * 记录一下当前键入的位置，方便删除
     */
    private var startInputIndex = -1
    private var totalCacheCount = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnNestedView.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, NestedScrollerVIewFragment())
                .addToBackStack("effect")
                .commit()
        }

        binding.btnRecyclerView.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, RecyclerViewFragment())
                .addToBackStack("effect")
                .commit()
        }

        binding.btnViewpager2.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, ViewPager2Fragment())
                .addToBackStack("effect")
                .commit()
        }
    }

}