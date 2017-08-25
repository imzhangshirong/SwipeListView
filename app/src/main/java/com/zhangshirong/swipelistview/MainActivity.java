package com.zhangshirong.swipelistview;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SwipeListView listView;
    private ListAdapter listAdapter;
    private ArrayList<Info> listData = new ArrayList<Info>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (SwipeListView) findViewById(R.id.listView);
        for(int a=0;a<100;a++){
            Info info = new Info();
            info.name = "List "+a;
            info.desc = "Here is description of List "+a;
            listData.add(info);
        }
        listView.setListener(new OnSwipeListItemClickListener() {
            @Override
            public void OnClick(View view, int index) {
                AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
                ab.setTitle("Click");
                ab.setMessage("click item "+index);
                ab.create().show();
            }

            @Override
            public boolean OnLongClick(View view, int index) {
                AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
                ab.setTitle("LongClick");
                ab.setMessage("long click item "+index);
                ab.create().show();
                return false;
            }

            @Override
            public void OnControlClick(int rid, View view, int index) {
                AlertDialog.Builder ab;
                switch (rid){
                    case R.id.modify:
                        ab = new AlertDialog.Builder(MainActivity.this);
                        ab.setTitle("Modify");
                        ab.setMessage("You will modify item "+index);
                        ab.create().show();
                        break;
                    case R.id.delete:
                        ab = new AlertDialog.Builder(MainActivity.this);
                        ab.setTitle("Delete");
                        ab.setMessage("You will delete item "+index);
                        ab.create().show();
                        break;
                }
            }
        },new int[]{R.id.modify,R.id.delete});
        listAdapter = new ListAdapter(listData);
        listView.setAdapter(listAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    class Info{
        public String name="";
        public String desc="";
    }
    class ViewHolder{
        public TextView name;
        public TextView desc;
        public Button modify;
        public Button delete;
    }
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
            return super.bindView(position, convertView);
        }
    }
}
