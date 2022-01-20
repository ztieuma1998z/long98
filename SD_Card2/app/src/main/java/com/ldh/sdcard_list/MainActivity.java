package com.ldh.sdcard_list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.text.CaseMap;
import android.media.audiofx.EnvironmentalReverb;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FolderClickListener {
    private ArrayList<Folder> folders = new ArrayList<>();
    private RecyclerView recyclerView;
    private ArrayList<String> file_list_name = new ArrayList<>();
    private ArrayList<String> file_list_path = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // check quyền truy cập vào sdcard
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Log.v("TAG", "Permission granted");
        } else {
            Log.v("TAG", "Permission denied");
            // cần yêu cầu quyền truy cập từ người dùng
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1234); // sau đó  overrider hàm onRequestPermissionResults
        }
       // lấy dữ liệu từ trang trước để in ra màn hình
        Intent intent = getIntent();
        file_list_name = intent.getStringArrayListExtra("file_list_name");
        file_list_path = intent.getStringArrayListExtra("file_list_path");
        if (file_list_name!=null)
        {
            for (int i=0;i<file_list_name.size();i++)
            {
                folders.add(new Folder(R.drawable.avatar,file_list_name.get(i),file_list_path.get(i)));
            }
        }
        else {
            File[] files = Environment.getExternalStorageDirectory().listFiles();
            for (File f : files) {
                folders.add(new Folder(R.drawable.avatar, f.getName(),f.getAbsolutePath()));
            }
        }
        recyclerView = findViewById(R.id.folder_list);
        recyclerView.setHasFixedSize(true);
        FoldertAdapter adapter = new FoldertAdapter(folders,MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                MainActivity.this,
                LinearLayoutManager.VERTICAL,
                false
        );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        ArrayList<String> temp_name = new ArrayList<>();
        ArrayList<String> temp_path = new ArrayList<>();
        Folder folder = folders.get(position);
    //    Toast.makeText(getApplicationContext(),folder.getName()+" is clicked!",Toast.LENGTH_SHORT).show();
        File directory = new File(folder.getPath());
        File [] files = directory.listFiles();
        if (files != null){
         //   Toast.makeText(getApplicationContext(),"Not null",Toast.LENGTH_SHORT).show();
            for (File f:files){
                temp_name.add(f.getName());
                temp_path.add(f.getAbsolutePath());
            }
            openActivity(temp_name,temp_path);
        }
        else{
        //   Toast.makeText(getApplicationContext(),"Null",Toast.LENGTH_SHORT).show();
            try {
                FileInputStream fis = new FileInputStream(folder.getPath());
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line=reader.readLine())!=null){
                    builder.append(line+"\n");
                }
                reader.close();
                openContent(builder.toString());
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 1234)
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                Log.v("TAG","Permission Granted");
//            else
//                Log.v("TAG","Permission denied");
    }
    public void openActivity(ArrayList<String> name, ArrayList<String> path)
    {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putStringArrayListExtra("file_list_name",name);
        intent.putStringArrayListExtra("file_list_path",path);
        startActivity(intent);
    }
    public void openContent(String s)
    {
        Intent intent = new Intent(this,MainActivity2.class);
        intent.putExtra("content",s);
        startActivity(intent);
    }
}