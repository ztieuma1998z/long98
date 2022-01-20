package com.ldh.sdcard_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FoldertAdapter extends RecyclerView.Adapter<FoldertAdapter.ItemViewHolder>{
    ArrayList<Folder> folders;
    private FolderClickListener folderClickListener;

    public FoldertAdapter(ArrayList<Folder> folders, FolderClickListener folderClickListener) {
        this.folders = folders;
        this.folderClickListener = folderClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_folder_item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
             Folder folder = folders.get(position);
             holder.textView.setText(folder.getName());
    }

    @Override
    public int getItemCount() {
        return folders.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
       private ImageView imageView;
       private TextView textView;
       private FolderClickListener _folderClickListener;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_name);
            imageView = itemView.findViewById(R.id.item_avatar);
            _folderClickListener = folderClickListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (_folderClickListener!=null){
                        _folderClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
