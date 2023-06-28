package com.xiaoc.androidbounceeffect.edgeeffect

import android.view.View
import android.widget.AbsListView
import android.widget.HorizontalScrollView
import android.widget.ScrollView
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

/**
 * 设置滚动视图的回弹效果
 * 该回弹效果支持 基于 [NestedScrollView]、[ScrollView]、[HorizontalScrollView]、[RecyclerView] 等组件
 * 所以理论上，大部分控件均支持
 * 注意：该方法仅适配了Android 5.0 以上的内容
 * 当你调用了回弹方法，那么滚动的overScrollMode将会被设置为always
 *
 * @param orientation 对于[RecyclerView]，你可能需要自行传递滑动的方向
 * @param overScrollMagnitude 拉动时的劲度系数，也就是拉动时的压力大小
 * @param flingMagnitude 到边缘时弹动灵敏度
 */
fun View.setBounceEdgeEffect(
    orientation: Int = BounceEdgeEffect.ORIENTATION_VERTICAL,
    overScrollMagnitude: Float = BounceEdgeEffect.OVER_SCROLL_TRANSLATION_MAGNITUDE,
    flingMagnitude: Float = BounceEdgeEffect.FLING_TRANSLATION_MAGNITUDE,
){
    try {
        when(this){
            is RecyclerView ->{
                this.overScrollMode = View.OVER_SCROLL_ALWAYS
                this.edgeEffectFactory = BounceEdgeEffectFactory(orientation)
            }
            is NestedScrollView ->{
                this.overScrollMode = View.OVER_SCROLL_ALWAYS
                val nestedScrollViewClass = Class.forName(this.javaClass.name)
                val acEdgeGlowTop = nestedScrollViewClass.getDeclaredField("mEdgeGlowTop")
                val acEdgeGlowBottom = nestedScrollViewClass.getDeclaredField("mEdgeGlowBottom")
                acEdgeGlowTop.isAccessible = true
                acEdgeGlowBottom.isAccessible = true
                acEdgeGlowTop.set(this, BounceEdgeEffect(this, direction = BounceEdgeEffect.DIRECTION_TOP, overScrollMagnitude = overScrollMagnitude, flingMagnitude = flingMagnitude))
                acEdgeGlowBottom.set(this, BounceEdgeEffect(this, direction = BounceEdgeEffect.DIRECTION_BOTTOM, overScrollMagnitude = overScrollMagnitude, flingMagnitude = flingMagnitude))
            }
            is ScrollView ->{
                this.overScrollMode = View.OVER_SCROLL_ALWAYS
                val scrollViewClass = Class.forName(this.javaClass.name)
                val acEdgeGlowTop = scrollViewClass.getDeclaredField("mEdgeGlowTop")
                val acEdgeGlowBottom = scrollViewClass.getDeclaredField("mEdgeGlowBottom")
                acEdgeGlowTop.isAccessible = true
                acEdgeGlowBottom.isAccessible = true
                acEdgeGlowTop.set(this, BounceEdgeEffect(this, direction = BounceEdgeEffect.DIRECTION_TOP, overScrollMagnitude = overScrollMagnitude, flingMagnitude = flingMagnitude))
                acEdgeGlowBottom.set(this, BounceEdgeEffect(this, direction = BounceEdgeEffect.DIRECTION_BOTTOM, overScrollMagnitude = overScrollMagnitude, flingMagnitude = flingMagnitude))
            }
            is HorizontalScrollView ->{
                this.overScrollMode = View.OVER_SCROLL_ALWAYS
                val horizontalScrollViewClass = Class.forName(this.javaClass.name)
                val acEdgeGlowLeft = horizontalScrollViewClass.getDeclaredField("mEdgeGlowLeft")
                val acEdgeGlowRight = horizontalScrollViewClass.getDeclaredField("mEdgeGlowRight")
                acEdgeGlowLeft.isAccessible = true
                acEdgeGlowRight.isAccessible = true
                acEdgeGlowLeft.set(this, BounceEdgeEffect(this, direction = BounceEdgeEffect.DIRECTION_LEFT, overScrollMagnitude = overScrollMagnitude, flingMagnitude = flingMagnitude))
                acEdgeGlowRight.set(this, BounceEdgeEffect(this, direction = BounceEdgeEffect.DIRECTION_RIGHT, overScrollMagnitude = overScrollMagnitude, flingMagnitude = flingMagnitude))
            }
            is ViewPager ->{
                this.overScrollMode = View.OVER_SCROLL_ALWAYS
                val horizontalViewPagerClass = Class.forName(this.javaClass.name)
                val acEdgeGlowLeft = horizontalViewPagerClass.getDeclaredField("mLeftEdge")
                val acEdgeGlowRight = horizontalViewPagerClass.getDeclaredField("mRightEdge")
                acEdgeGlowLeft.isAccessible = true
                acEdgeGlowRight.isAccessible = true
                acEdgeGlowLeft.set(this, BounceEdgeEffect(this, direction = BounceEdgeEffect.DIRECTION_LEFT, overScrollMagnitude = overScrollMagnitude, flingMagnitude = flingMagnitude))
                acEdgeGlowRight.set(this, BounceEdgeEffect(this, direction = BounceEdgeEffect.DIRECTION_RIGHT, overScrollMagnitude = overScrollMagnitude, flingMagnitude = flingMagnitude))
            }
            is ViewPager2 ->{
                val recyclerView = this.getChildAt(0) as RecyclerView
                recyclerView.overScrollMode = View.OVER_SCROLL_ALWAYS
                this.overScrollMode = View.OVER_SCROLL_ALWAYS
                val transOrientation = when(this.orientation){
                    ViewPager2.ORIENTATION_HORIZONTAL ->{
                        BounceEdgeEffect.ORIENTATION_HORIZONTAL
                    }
                    else ->{
                        BounceEdgeEffect.ORIENTATION_VERTICAL
                    }
                }
                recyclerView.edgeEffectFactory = BounceEdgeEffectFactory(transOrientation, overScrollMagnitude = overScrollMagnitude, flingMagnitude = flingMagnitude)
            }
            is AbsListView ->{
                this.overScrollMode = View.OVER_SCROLL_ALWAYS
                val absListViewClass = Class.forName(this.javaClass.name)
                val acEdgeGlowTop = absListViewClass.getDeclaredField("mEdgeGlowTop")
                val acEdgeGlowBottom = absListViewClass.getDeclaredField("mEdgeGlowBottom")
                acEdgeGlowTop.isAccessible = true
                acEdgeGlowBottom.isAccessible = true
                acEdgeGlowTop.set(this, BounceEdgeEffect(this, direction = BounceEdgeEffect.DIRECTION_TOP, overScrollMagnitude = overScrollMagnitude, flingMagnitude = flingMagnitude))
                acEdgeGlowBottom.set(this, BounceEdgeEffect(this, direction = BounceEdgeEffect.DIRECTION_BOTTOM, overScrollMagnitude = overScrollMagnitude, flingMagnitude = flingMagnitude))
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}