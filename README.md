这是一个基于Android官方提供的EdgeEffect边缘效果实现的仿IOS回弹效果的开源库。
实现原理非常简单，基于SpringAnimation弹簧动画，来校对EdgeEffect对滑动内容的控制。
由于是Android提供的EdgeEffect上进行特性修改的，所以理论上支持Android支持EdgeEffect的所有控件。
支持以下控件：
> - RecyclerView
> - AbsListView
> - ViewPager
> - ViewPager2
> - HorizontalScrollView
> - ScrollView
> - NestedScrollView
> 
...

如果第三方控件也使用了EdgeEffect边缘效果器，那么基于简单的实现，也可以达到目的。例如某第三Banner库，是基于ViewPager的，它也可以间接支持回弹效果。（这里只是举个例子，Banner实际上在实际运用过程中并不会滚动到边缘）
### 使用方法
#### 依赖使用
待加入到MavenCentral远程仓库中
#### 使用
对支持EdgeEffect效果器的控件使用扩展方法：
```kotlin
binding.scrollerView.setBounceEdgeEffect()
```
Java：
```java
BounceEffectKt.setBounceEdgeEffect(binding.scrollerView);
```
这样就完成了回弹效果的默认设定。
#### 自定义参数
回弹力度和灵敏度是可以动态调整的，在setBounceEdgeEffect时传入对应需要的力度数值即可。
