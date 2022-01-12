package com.example.jsonandasycntask;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class ItemJSONAdapter extends RecyclerView.Adapter<ItemJSONAdapter.ItemViewHolder> {
    JSONArray jsonArray;
    private OnClick click;

    public ItemJSONAdapter(JSONArray jsonArray, OnClick click) {
        this.jsonArray = jsonArray;
        this.click = click;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(position);

            Picasso.get().load("https://lebavui.github.io" + jsonObject.getJSONObject("avatar").getString("photo")).into(holder.avatar);
            holder.userName.setText(jsonObject.getString("username"));
            holder.email.setText(jsonObject.getString("email"));
            String avatarDetail = "https://lebavui.github.io" + jsonObject.getJSONObject("avatar").getString("photo");
            String usernameDetail = jsonObject.getString("username");
            String nameDetail = jsonObject.getString("name");
            String emailDetail = jsonObject.getString("email");
            String addressDetail = jsonObject.getJSONObject("address").getString("street");
            String phoneDetail = jsonObject.getString("phone");
            String companyDetail = jsonObject.getJSONObject("company").getString("name");
            holder.userModel = new UserModel(avatarDetail, usernameDetail, nameDetail, emailDetail, addressDetail, phoneDetail, companyDetail);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        UserModel userModel;
        ImageView avatar;
        TextView userName;
        TextView email;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.avatar);
            userName = itemView.findViewById(R.id.userName);
            email = itemView.findViewById(R.id.email);

            avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click.userClick(userModel);
                }
            });
        }
    }
}
