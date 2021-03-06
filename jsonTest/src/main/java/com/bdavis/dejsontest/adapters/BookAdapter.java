package com.bdavis.dejsontest.adapters;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bdavis.dejsontest.data.Book;
import com.bdavis.dejsontest.jsontest.R;
import com.facebook.drawee.view.SimpleDraweeView;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.book_item, parent, false);

            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.author = (TextView) convertView.findViewById(R.id.author);
            holder.cover = (SimpleDraweeView) convertView.findViewById(R.id.cover);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Book book = getItem(position);

        holder.title.setText(book.getTitle());

        if (!TextUtils.isEmpty(book.getAuthor())) {
            holder.author.setText("Author: " + book.getAuthor());
            holder.author.setVisibility(View.VISIBLE);
        } else {
            holder.author.setText("");
            holder.author.setVisibility(View.GONE);
        }

        String url = getItem(position).getImageUrl();
        if (url != null) {
            Uri coverUri = Uri.parse(url);
            holder.cover.setImageURI(coverUri);
        }

        return convertView;
    }

    private static class ViewHolder {
        public TextView title;
        public SimpleDraweeView cover;
        public TextView author;
    }
}