package com.example.nafs;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class diary extends Fragment {
    private Context dContext;

    private ListView listViewDiary;
    private ImageButton buttonAdd;
    private SharedPreferences sharedPreferences;
    private DiaryEntryAdapter adapter; // Change ArrayAdapter to DiaryEntryAdapter
    private ArrayList<String> diaryEntriesList;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_diary, container, false);

        listViewDiary = rootView.findViewById(R.id.listViewDiary);
        buttonAdd = rootView.findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDiaryActivity();
            }
        });

        sharedPreferences = getActivity().getSharedPreferences("diary_prefs", Context.MODE_PRIVATE);

        // Set up the ListView with the custom adapter
        diaryEntriesList = new ArrayList<>();
        adapter = new DiaryEntryAdapter(getActivity(), diaryEntriesList);
        listViewDiary.setAdapter(adapter);

        // Set up a long click listener for the ListView items
        listViewDiary.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle long click (for example, show a context menu for delete)
                showDeleteDialog(position);
                return true; // Consume the long click event
            }
        });

        displayDiaryEntries();

        return rootView;
    }

    public void goToDiaryActivity() {
        Intent intent = new Intent(getActivity(), DiaryActivities.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        displayDiaryEntries();
    }

    private void displayDiaryEntries() {
        String diaryEntries = sharedPreferences.getString("diary_entries", "");
        String[] entriesArray = diaryEntries.split("\n\n");
        diaryEntriesList.clear();
        for (String entry : entriesArray) {
            diaryEntriesList.add(entry);
        }
        adapter.notifyDataSetChanged();
    }

    private void showDeleteDialog(int position) {
        deleteEntry(position);
    }

    private void deleteEntry(int position) {
        diaryEntriesList.remove(position);
        updateSharedPreferences();
        adapter.notifyDataSetChanged();
    }

    private void updateSharedPreferences() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String entry : diaryEntriesList) {
            stringBuilder.append(entry).append("\n\n");
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("diary_entries", stringBuilder.toString().trim());
        editor.apply();
    }

    public void saveEntriesToSharedPreferences() {
        updateSharedPreferences();
    }

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        dContext = getActivity();
    }
}