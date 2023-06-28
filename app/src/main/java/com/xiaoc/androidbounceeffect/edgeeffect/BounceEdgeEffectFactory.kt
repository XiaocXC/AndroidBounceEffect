package com.xiaoc.androidbounceeffect.edgeeffect

import android.widget.EdgeEffect
import androidx.recyclerview.widget.RecyclerView
import com.xiaoc.androidbounceeffect.edgeeffect.BounceEdgeEffect

/**
 * @author Xiaoc
 * @since 2023/3/23
 *
 * RecyclerView的边缘回弹工厂
 * 用于创建[BounceEdgeEffect]回弹效果的内容
 *
 * @param orientation RecyclerView的滚动方向
 */
class BounceEdgeEffectFactory(
    private val orientation: Int = BounceEdgeEffect.ORIENTATION_VERTICAL,
    private val overScrollMagnitude: Float = BounceEdgeEffect.OVER_SCROLL_TRANSLATION_MAGNITUDE,
    private val flingMagnitude: Float = BounceEdgeEffect.FLING_TRANSLATION_MAGNITUDE,
): RecyclerView.EdgeEffectFactory() {

    override fun createEdgeEffect(view: RecyclerView, direction: Int): EdgeEffect {
        val transDirection = when(direction){
            DIRECTION_TOP ->{
                BounceEdgeEffect.DIRECTION_TOP
            }
            DIRECTION_BOTTOM ->{
                BounceEdgeEffect.DIRECTION_BOTTOM
            }
            DIRECTION_LEFT ->{
                BounceEdgeEffect.DIRECTION_LEFT
            }
            DIRECTION_RIGHT ->{
                BounceEdgeEffect.DIRECTION_RIGHT
            }
            else ->{
                throw IllegalArgumentException("不支持的回弹方向: $direction")
            }
        }
        return BounceEdgeEffect(view, transDirection, orientation, overScrollMagnitude, flingMagnitude)
    }
}