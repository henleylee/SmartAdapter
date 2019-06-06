# SmartAdapter —— Android 万能Adapter
Android 万能的Adapter for ListView,GridView,RecyclerView,ViewPager等，支持多种Item类型的情况。

## Download ##
### Gradle ###
```gradle
dependencies {
    implementation 'com.henley.android:smartadapter:1.0.1'
}
```

### APK Demo ###

下载 [APK-Demo](https://github.com/HenleyLee/SmartAdapter/raw/master/app/app-release.apk)

## ViewHolder类介绍 ##
ViewHolder中封装了大量的常用的方法，实现了View的复用，省去了自己编写ViewHolder等大量的重复的代码。

#### ViewHolder类方法说明： ####
* getView(int viewId)：通过控件的资源ID获取控件
* setText(int viewId, CharSequence text)：为TextView设置文本
* setText(int viewId, int resId)：为TextView设置文本
* setTextColor(int viewId, int textColor)：为TextView设置文本颜色
* setTextColorRes(int viewId, int textColorRes)：为TextView设置文本颜色
* linkify(int viewId, int mask)：为TextView设置超链接
* setTypeface(Typeface typeface, int... viewIds)：为TextView设置文本字体
* setImageResource(int viewId, int resId)：为ImageView设置图片
* setImageBitmap(int viewId, Bitmap bitmap)：为ImageView设置图片
* setImageDrawable(int viewId, Drawable drawable)：为ImageView设置图片
* setBackgroundColor(int viewId, int color)：为ImageView设置背景颜色
* setBackgroundRes(int viewId, int backgroundRes)：为ImageView设置背景图片
* setVisible(int viewId, int visibility)：为View设置可视状态
* setProgress(int viewId, int progress)：为ProgressBar设置进度
* setMax(int viewId, int max)：为ProgressBar设置最大值
* setProgress(int viewId, int progress, int max)：为ProgressBar设置进度和最大值
* setRating(int viewId, float rating)：为RatingBar设置等级
* setRating(int viewId, float rating, int numStars)：为RatingBar设置等级和星星的数目
* setChecked(int viewId, boolean checked)：为Checkable设置选中状态
* setAlpha(int viewId, float alpha)：为View设置透明度
* setVisible(int viewId, boolean visible)：为View设置可视状态
* setTag(int viewId, final Object tag)：为View设置TAG
* setTag(int viewId, int key, final Object tag)：为View设置TAG
* setOnClickListener(int viewId, View.OnClickListener listener)：为View设置点击事件
* setOnLongClickListener(int viewId, View.OnLongClickListener listener)：为View设置长点击事件
* setOnTouchListener(int viewId, View.OnTouchListener listener)：为View设置触摸事件

## ListView,GridView,RecyclerView的使用 ##
ListView,GridView,RecyclerView用法相同

#### 准备初始化工作 ####
1. 初始化ListView,GridView,RecyclerView等目标控件targetView；
2. 创建数据类型DataModel并初始化数据源Collection<DataModel> datas。

#### 单种Item的创建和数据绑定： ####
```java
    targetView.setAdapter(new CommonAdapter<DataModel>(datas) {
        @Override
        public int getItemLayoutID() {
            return R.layout.item_layout_type_single;
        }

        @Override
        public void convert(ViewHolder viewHolder, DataModel data, int position) {
            holder.setText(R.id.single_common_name, data.getName());
            holder.setText(R.id.single_common_content, data.getContent());
            holder.setImageResource(R.id.single_common_icon, data.getIcon());
        }
    });
```
#### 多种Item的创建和数据绑定(ListView,GridView,RecyclerView用法相同)： ####
1.根据每种不同类型的Item创建对应的ItemViewDelegate：
```java
    public class ItemTypeFirstDelagate implements ItemViewDelegate<DataModel> {

        @Override
        public int getItemLayoutID() {
            return R.layout.item_layout_type_first;
        }

        @Override
        public boolean isForViewType(DataModel data, int position) {
            return data.getType() == DataModelType.TYPE_First;
        }

        @Override
        public void convert(ViewHolder holder, DataModel data, int position) {
            holder.setText(R.id.first_send_name, data.getName());
            holder.setText(R.id.first_send_content, data.getContent());
            holder.setImageResource(R.id.first_send_icon, data.getIcon());
        }
    }
```
2.将创建的ItemViewDelegate添加到创建的MultiItemTypeAdapter适配器中：
```java
    MultiItemTypeAdapter<DataModel> multiAdapter = new MultiItemTypeAdapter<>(datas);
    multiAdapter.addItemViewDelegate(new ItemTypeFirstDelagate());
    multiAdapter.addItemViewDelegate(new ItemTypeSecondDelagate());
    targetView.setAdapter(multiAdapter);
```
注意：只添加一个ItemViewDelegate相当于单种Item类型的使用。

## ExpandableListView的使用 ##

#### CommonExpandableAdapter(CommonExpandableAdapter继承BaseExpandableListAdapter)： ####
```java
    HashMap<GroupModel, List<DataModel>> datas = new HashMap<>();
    expandableListView.setAdapter(new CommonExpandableAdapter<GroupModel, DataModel>(datas) {
        @Override
        protected int getGroupLayoutID() {
            return R.layout.item_layout_group;
        }

        @Override
        public int getItemLayoutID() {
            return R.layout.item_layout_child;
        }

        @Override
        protected void convertGroup(ViewHolder holder, GroupModel group, List<DataModel> childs, int groupPosition) {
            holder.setText(R.id.group_name, group.getName());
            holder.setText(R.id.group_content, group.getContent());
        }

        @Override
        protected void convertChild(ViewHolder holder, DataModel child, int groupPosition, int childPosition) {
            holder.setText(R.id.child_name, child.getName());
            holder.setText(R.id.child_content, child.getContent());
            holder.setImageResource(R.id.child_icon, child.getIcon());
        }
    });
```

## ViewPager的使用 ##

#### CommonPagerAdapter(CommonPagerAdapter继承PagerAdapter)： ####
```java
    List<View> viewList = new ArrayList<>();
    List<String> titleList = new ArrayList<>();
    // 不设置标题
    viewPager.setAdapter(new CommonPagerAdapter(viewList));
    // 设置标题
    viewPager.setAdapter(new CommonPagerAdapter(viewList, titleList));
```

#### CommonFragmentPagerAdapter(CommonFragmentPagerAdapter继承FragmentPagerAdapter)： ####
```java
    List<Fragment> fragments = new ArrayList<>();
    viewPager.setAdapter(new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragments));
```

#### CommonFragmentStatePagerAdapter(CommonFragmentStatePagerAdapter继承FragmentStatePagerAdapter)： ####
```java
    List<Fragment> fragments = new ArrayList<>();
    viewPager.setAdapter(new CommonFragmentStatePagerAdapter(getSupportFragmentManager(), fragments));
```

## 感谢 ##
1. [baseAdapter](https://github.com/hongyangAndroid/baseAdapter)
2. [AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates)
