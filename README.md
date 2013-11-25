ViewPager + Fragment异步实时刷新
==========

## 背景

类似于网易新闻顶部tab框架想必网上肯定有各种demo，大名鼎鼎的[ViewPagerIndicator](https://github.com/JakeWharton/Android-ViewPagerIndicator)开源项目可以放你很方便的实现这种效果，机制就是ViewPager + Fragment来实现。

但是ViewPager + Fragment默认会提前加载相邻一个页面的内容，意思就是当你处于第一个tab时，会默认把第一个和第二个tab提前加载好，这个时候你再切换到第二个tab的时候，导致第二个tab不会再刷新（此时第三个tab相应的也会提前加载），也就是说最多会有3个tab提前加载好。当然你可以通过设置

    mViewPager.setOffscreenPageLimit(2);

方法来设置提前加载相邻页面的个数。默认是1，可惜的是该方法不能设置为0.

而网易新闻确可以如下效果，看下截图：

截图一：
![Sample image1](https://raw.github.com/stormzhang/NetEaseTab/master/art/netease01.jpg)

截图二：
![Sample image2](https://raw.github.com/stormzhang/NetEaseTab/master/art/netease02.jpg)

可以看到默认是“热门”页面，而这个时候下一个页面确不会提前加载，会等你切换到下一个tab的时候才会刷新当前页面。

于是自己也写了个demo来实现类似的效果。

## 说明

本项目的关键部分主要是利用到了ViewPager的setOnPageChangeListener接口，在onPageSelected(int position)方法中指定fragment的刷新。但是问题是第一个Fragment不会触发onPageSelected方法，于是可以看到代码中单独为第一个Fragment的刷新做了处理。

至此便实现了每切换一个tab都会刷新的demo，但网易新闻再切换相邻tab的时候应该是超过一定时间（比如10分钟）才会刷新，相信实现了每次都刷新之后只须加个时间判断就可以完全了, 这里暂且就不做处理了。

## 备注

最后，运行该项目需要引用[ViewPagerIndicator](https://github.com/JakeWharton/Android-ViewPagerIndicator)的library包。