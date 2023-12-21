package com.example.nafs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import java.util.List;

public class DiaryEntryAdapter extends ArrayAdapter<String> {

    private Context aContext;
    private final Context context;
    private final List<String> entries;


    public DiaryEntryAdapter(Context context, List<String> entries) {
        super(context, R.layout.list_item_diary_entry, entries);
        this.context = context;
        this.entries = entries;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.list_item_diary_entry, parent, false);

            // Create a ViewHolder and store references to the views to avoid multiple findViewById calls
            holder = new ViewHolder();
            holder.textViewEntry = view.findViewById(R.id.textViewEntry);
            holder.imageButtonDelete = view.findViewById(R.id.imageButtonDelete);

            view.setTag(holder);
        } else {
            // Use the existing ViewHolder
            holder = (ViewHolder) view.getTag();
        }
        holder.textViewEntry.setText(entries.get(position));

        holder.imageButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entries.remove(position);
                notifyDataSetChanged();

//                if (context instanceof MainActivity) {
//                    ((MainActivity) context).saveEntriesToSharedPreferences();
//                }
            }
        });

        return view;
    }
    static class ViewHolder {
        TextView textViewEntry;
        ImageButton imageButtonDelete;
    }


}
