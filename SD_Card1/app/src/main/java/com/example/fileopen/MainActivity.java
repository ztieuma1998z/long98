package com.example.fileopen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> ad;
    List<String> filename;
    List<String> filenameState;
    List<String> path;
    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
    ListView listView;
    File directory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                Log.v("TAG", "Permission granted");
            else {
                Log.v("TAG", "Permission denied");
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1234);
            }
        }
        listView = (ListView) findViewById(R.id.list);

        directory = new File(extStorageDirectory);
        filename = new ArrayList<>();
        filenameState = new ArrayList<>();
        path = new ArrayList<>();
        String[] lib = directory.list();
        for (String libItem : lib) {
            filename.add(libItem);
            path.add(extStorageDirectory + "/" + libItem);
        }

        ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, filename);
        listView.setAdapter(ad);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String folderList = path.get(position);
                File fileFolder = new File(folderList);
                if (fileFolder.isDirectory()) {
//                    Log.v("TAG", "All path");
//                    for (String x : path){
//                        Log.v("TAG", x);
//                    }
                    File[] files = fileFolder.listFiles();
                    if (files == null) {
                        String msg = "Can't open this folder!";
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                    } else if (files.length == 0) {
                        String msg = "This folder is empty!";
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                    } else {
                        int i = position + 1;
                        for (File f : files) {
                            if (path.indexOf(f.getAbsolutePath()) != -1) break;
                            Log.v("TAG", f.getAbsolutePath());
                            Log.v("TAG", f.getName());
//                            filename = insertX(filename, f.getName(), position + 1);
                            filename.add(i, f.getName());
                            path.add(i, f.getAbsolutePath());
                            filenameState.add(f.getName());
                            i++;
                        }
                        int num = i - position - 1;
                        filenameState.add(Integer.toString(num));
                    }
                    ad.notifyDataSetChanged();
                } else {
                    //Xu ly
                    String extension = fileFolder.getAbsolutePath().substring(fileFolder.getAbsolutePath().lastIndexOf("."));
                    Log.v("TAG","ex " + extension);
                    if (extension.equals(".txt")){
                        StringBuilder text = new StringBuilder();
                        try {
                            BufferedReader br = new BufferedReader(new FileReader(fileFolder));
                            String line;

                            while ((line = br.readLine()) != null) {
                                text.append(line);
                                text.append('\n');
                            }
                            br.close();
                        }
                        catch (IOException e) {
                        }
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                        alertDialogBuilder.setTitle(fileFolder.getName());
                        alertDialogBuilder.setMessage(text.toString());
                        alertDialogBuilder.show();
                    } else {
                        String msg = "Can't open this file. It's not .txt!";
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1234)
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Log.v("TAG", "Permission granted");
            else
                Log.v("TAG", "Permission denied");
    }

    @Override
    public void onBackPressed() {
        if (filenameState.isEmpty()) {
            super.onBackPressed();
        } else {
            int num = Integer.parseInt(filenameState.get(filenameState.size() - 1));
            for (int i = 1; i <= num; i++){
                int del = filename.indexOf(filenameState.get(filenameState.size() - 2));
                filename.remove(del);
                path.remove(del);
                filenameState.remove(filenameState.size() - 2);
            }
            ad.notifyDataSetChanged();
            filenameState.remove(filenameState.size() - 1);
        }
    }
}