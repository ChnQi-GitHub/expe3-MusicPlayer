package com.example.mymusic;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MusicList extends AppCompatActivity implements Serializable {
    private ArrayList<String> list;
    private File[] songFiles;
    private String[] ing_list=new String[50];
    public static boolean[] ing=new boolean[50];
    private ListAdapter targetAdapter;
    private ArrayList<String> list2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        list = new ArrayList<String>();   //音乐列表
        list2=new ArrayList<String>();
        final File sdpath = Environment.getExternalStorageDirectory(); //获得手机SD卡路径
        File path = new File(sdpath + "/music//");      //获得SD卡的mp3文件夹

        //返回以.mp3结尾的文件 (自定义文件过滤)
        songFiles = path.listFiles(new MyFilter(".mp3"));
        for (File file : songFiles) {
            String str = file.getAbsolutePath();
            String str1 = str.substring(str.indexOf("/music/") + 7, str.indexOf(".mp3"));
            list.add(str1);

        }



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MusicList.this,
                android.R.layout.simple_expandable_list_item_1, list);
        ListView listView = (ListView) findViewById(R.id.list_item2);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position,
                                           long id) {
                if(!ing[position]){
                    ing_list[position]=list.get(position);
                    ing[position]=true;
                    list2.add(ing_list[position]);
                    Toast.makeText(MusicList.this,"succeed",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MusicList.this,MusicActivity.class);
                    intent.putStringArrayListExtra("infolist",list2);
                    startActivity(intent);
                }
                else {
                    ing_list[position]=null;
                    ing[position]=false;
                    Toast.makeText(MusicList.this," ",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

    }







    }





