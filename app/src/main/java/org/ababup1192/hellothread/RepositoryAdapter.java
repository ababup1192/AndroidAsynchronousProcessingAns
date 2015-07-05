package org.ababup1192.hellothread;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.ababup1192.hellothread.github.Repository;

import java.util.List;

public class RepositoryAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater = null;
    private List<Repository> repositoryList;
    private ViewHolder viewHolder;

    public RepositoryAdapter(Context context, List<Repository> repositoryList) {
        this.repositoryList = repositoryList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return repositoryList.size();
    }

    @Override
    public Object getItem(int i) {
        return repositoryList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return repositoryList.get(i).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // ViewHolder Pattern
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.repository_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = ((TextView) convertView.findViewById(R.id.text_reponame));
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ((TextView) convertView.findViewById(R.id.text_reponame)).setText(repositoryList.get(position).fullName);


        return convertView;
    }

    private class ViewHolder {
        TextView textView;
    }
}

