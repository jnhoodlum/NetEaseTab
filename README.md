ViewPager + Fragment异步实时刷新
==========

## 说明

类似于网易新闻顶部tab框架想必网上肯定有各种demo，大名鼎鼎的[ViewPagerIndicator](https://github.com/JakeWharton/Android-ViewPagerIndicator)开源项目可以放你很方便的实现这种效果，机制就是ViewPager + Fragment来实现。

但是ViewPager + Fragment默认会提前加载相邻一个页面的内容，意思就是当你处于第一个tab时，会默认把第一个和第二个tab提前加载好，这个时候你再切换到第二个tab的时候，导致第二个tab不会再刷新（此时第三个tab相应的也会提前加载），也就是说最多会有3个tab提前加载好。当然你可以通过设置

    mViewPager.setOffscreenPageLimit(2);

方法来设置提前加载相邻页面的个数。默认是1，可惜的是该方法不能设置为0.

而网易新闻确可以达到这个效果，默认是“热门”页面，而这个时候下一个页面确不会提前加载，会等你切换到下一个tab的时候才会刷新当前页面。看下截图：


