package com.molo.app.challenge.mobile.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.molo.app.challenge.mobile.R;
import com.molo.app.challenge.mobile.models.Repository;
import com.molo.app.challenge.mobile.utils.Utils;

public class RepositoryListAdapter extends ArrayAdapter<Repository>{

    public RepositoryListAdapter(Context context) {
        super(context, R.layout.repository_list_item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.repository_list_item,null);
            holder=new ViewHolder();
            holder.avatar=convertView.findViewById(R.id.avatar);
            holder.description=convertView.findViewById(R.id.description);
            holder.name=convertView.findViewById(R.id.name);
            holder.starCount=convertView.findViewById(R.id.star_count);
            holder.owner=convertView.findViewById(R.id.owner);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        Repository repository=getItem(position);
        if(repository!=null){
            holder.starCount.setText(Utils.formatStarCount(repository.getStargazersCount()));
            holder.name.setText(repository.getName());
            holder.description.setText(repository.getDescription());
            holder.owner.setText(repository.getOwner().getLogin());
            //holder.avatar.setImageURI(Uri.parse(repository.getOwner().getAvatarUrl()));
            Bitmap bmp=Utils.resolveFromCache(repository.getOwner().getAvatarUrl());
            if(bmp==null)
                new  Utils.DownloadImageTask(holder.avatar).execute(repository.getOwner().getAvatarUrl());
            else holder.avatar.setImageBitmap(bmp);
        }
        return convertView;
    }
    class ViewHolder{
        ImageView avatar;
        TextView name;
        TextView description;
        TextView starCount;
        TextView owner;
    }
}
