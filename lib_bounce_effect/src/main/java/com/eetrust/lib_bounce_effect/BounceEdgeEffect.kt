package com.eetrust.lib_bounce_effect

import android.view.View
import android.widget.EdgeEffect
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.lifecycle.LifecycleObserver

/**
 * @author Xiaoc
 * @since 2021/2/2
 *
 * 滚动View回弹效果
 * 它是一个过度边缘滚动的效果的 [EdgeEffect] 边缘效果的对象
 *
 * 我们这里不保留滚动View的原始过度滑动效果
 *
 * 实现原理：利用[SpringAnimation]的回弹动画，更改视图的位置，并在松开时播放回弹动画
 *
 * @param bounceView 需要回弹的View视图
 * @param orientation 回弹方向（水平/竖直（默认））
 * @param overScrollMagnitude 拉动时的劲度系数，也就是拉动时的压力大小
 * @param flingMagnitude 到边缘时弹动灵敏度
 *
**/
class BounceEdgeEffect(
    private val bounceView: View,
    private val direction: Int = DIRECTION_TOP,
    private val orientation: Int = ORIENTATION_VERTICAL,
    private val overScrollMagnitude: Float = OVER_SCROLL_TRANSLATION_MAGNITUDE,
    private val flingMagnitude: Float = FLING_TRANSLATION_MAGNITUDE,
): EdgeEffect(bounceView.context), LifecycleObserver {

    companion object{

        /** 缓冲值，拉动时的比例系数 */
        const val OVER_SCROLL_TRANSLATION_MAGNITUDE = 0.3f

        /** 到边缘时弹动的比例系数 */
        const val FLING_TRANSLATION_MAGNITUDE = 0.45f

        const val DIRECTION_TOP = 1
        const val DIRECTION_BOTTOM = 2
        const val DIRECTION_LEFT = 3
        const val DIRECTION_RIGHT = 4

        const val ORIENTATION_VERTICAL = 5
        const val ORIENTATION_HORIZONTAL = 6
    }

    private val bounceAnimation: SpringAnimation = if(orientation == ORIENTATION_HORIZONTAL){
        SpringAnimation(bounceView, SpringAnimation.TRANSLATION_X)
            .setSpring(
                SpringForce()
                    .setFinalPosition(0f)
                    .setDampingRatio(SpringForce.DAMPING_RATIO_NO_BOUNCY)
                    .setStiffness(SpringForce.STIFFNESS_LOW)
            )
    } else {
        SpringAnimation(bounceView, SpringAnimation.TRANSLATION_Y)
            .setSpring(
                SpringForce()
                    .setFinalPosition(0f)
                    .setDampingRatio(SpringForce.DAMPING_RATIO_NO_BOUNCY)
                    .setStiffness(SpringForce.STIFFNESS_LOW)
            )
    }

    /**
     * 在用户朝远离边缘的方向拉动的时候调用
     */
    override fun onPull(deltaDistance: Float) {
        handlePull(deltaDistance)
    }

    /**
     * 在用户朝远离边缘的方向拉动的时候调用
     */
    override fun onPull(deltaDistance: Float, displacement: Float) {
        handlePull(deltaDistance)
    }

    /**
     * 处理拉动效果
     * 将RecyclerView设置translationY属性，让RecyclerView移动 deltaDistance 距离
     * @param deltaDistance 移动的距离
     *
     * 如果是上拉，则sign为负，移动的Y轴极为向上移动，反之亦然
     * 这里还有一个 overScrollMagnitude 为缓冲值，相当于一个比例系数，用于控制拉动劲度
     */
    private fun handlePull(deltaDistance: Float){
        // 如果是下拉或是右拉则修改为负标记
        val sign = if (direction == DIRECTION_RIGHT || direction == DIRECTION_BOTTOM) -1 else 1

        // 判断拉动方向是水平还是竖直
        if(direction == DIRECTION_LEFT || direction == DIRECTION_RIGHT){
            val translationXDelta =
                sign * bounceView.height * deltaDistance * overScrollMagnitude

            bounceAnimation.cancel()
            bounceView.translationX += translationXDelta
        } else {
            val translationYDelta =
                sign * bounceView.width * deltaDistance * overScrollMagnitude

            bounceAnimation.cancel()
            bounceView.translationY += translationYDelta
        }
    }

    /**
     * 当用户放开的手指的那一刻
     * 我们需要在这里让recyclerView的每一个可见item的translationY值变成0
     * 这里使用 SpringAnimation 动画去模拟返回到原始位置，可以随意调整
     */
    override fun onRelease() {
        bounceAnimation.start()
    }

    /**
     * recyclerView在脱离用户手指滑动期间滚动，但到了recyclerview的边缘时速度不为0会调用此方法
     * @param velocity 力度
     *
     * 我们刚好可以利用SpringAnimation设置对应力度，来达到弹性力度的动画效果
     * 这里有一个 flingMagnitude 即：到边缘时弹动的比例系数，可以随意调整
     */
    override fun onAbsorb(velocity: Int) {
        val sign = if (direction == DIRECTION_BOTTOM || direction == DIRECTION_RIGHT) -1 else 1
        val translationVelocity = sign * velocity * flingMagnitude

        bounceAnimation.setStartVelocity(translationVelocity)?.start()
    }

}