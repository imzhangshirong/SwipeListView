# SwipeListView
## 仿qq列表滑动效果，适应效果强，可进行自定义或动态调整抽屉内容，操作事件绑定简单。


<img src="preview.gif" />


### 使用说明
- 1、建立列表项Layout文件，基本要求如下（简单举例，详细参考Demo）<br><pre>
&lt;SwipeListViewScroll&gt;<br>
    &lt;LinearLayout&gt;<br>
        &lt;LinearLayout&gt;<br>
            &lt;!--&gt;列表主题内容&lt;--&gt;
        &lt;/LinearLayout&gt;<br>
        &lt;LinearLayout&gt;<br>
            &lt;!--&gt;操作的滑动抽屉菜单按钮&lt;--&gt;
        &lt;/LinearLayout&gt;<br>
    &lt;/LinearLayout&gt;<br>
&lt;/SwipeListViewScroll&gt;</pre>

- 2、在Activity Layout里使用SwipeListView
- 3、SwipeListView使用SetListener进行相关的Item事件和控制按钮事件的注册<br><pre>
listView.setListener(new OnSwipeListItemClickListener() {
            @Override
            public void OnClick(View view, int index) {
                //item点击
            }
            @Override
            public boolean OnLongClick(View view, int index) {
                //item点击长按
            }
            @Override
            public void OnControlClick(int rid, View view, int index) {
                AlertDialog.Builder ab;
                switch (rid){
                    case R.id.modify:
                        //item点击modify
                        break;
                    case R.id.delete:
                        //item点击delete
                        break;
                }
            }
        },new int[]{R.id.modify,R.id.delete});//按钮view的id
</pre>

- 4、设置ListAdapter（继承自SwipeListAdapter）具体使用如下（请参考案例）<br><pre>
class ListAdapter extends com.zhangshirong.swipelistview.SwipeListAdapter {
        private ArrayList<Info> listData;
        public ListAdapter(ArrayList<Info> listData){
            this.listData= (ArrayList<Info>) listData.clone();
        }
        @Override
        public int getCount() {
            return listData.size();
        }
        @Override
        public Object getItem(int position) {
            return listData.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = new ViewHolder();
            if(convertView == null){
                convertView = View.inflate(getBaseContext(),R.layout.style_list,null);
                viewHolder.name = (TextView) convertView.findViewById(R.id.name);
                viewHolder.desc = (TextView) convertView.findViewById(R.id.desc);
                viewHolder.modify = (Button) convertView.findViewById(R.id.modify);
                viewHolder.delete = (Button) convertView.findViewById(R.id.delete);
                convertView.setTag(viewHolder);
            }
            else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.name.setText(listData.get(position).name);
            viewHolder.desc.setText(listData.get(position).desc);
            //注意这里的使用
            return super.bindView(position, convertView);
        }
    }
</pre>