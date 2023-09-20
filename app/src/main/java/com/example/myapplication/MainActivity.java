package com.example.myapplication;


import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expandableListView;

    private final List<String> listDataHeader = new ArrayList<>();
    private final HashMap<String, List<Item>> listDataChild = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableListView = findViewById(R.id.expandableListView);

        fetchData();
    }
    private void fetchData() {
        ApiService apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);

        Call<List<Item>> call = apiService.getItems();

        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(@NonNull Call<List<Item>> call,  @NonNull Response<List<Item>> response) {
                if (response.body() != null) {
                    List<Item> items = response.body();
                    // Remove null and empty values
                    items.removeIf(item -> item.getName() == null || item.getName().trim().isEmpty());

                    HashMap<Integer, List<Item>> groupedItems = new HashMap<>();
                    for (Item item : items) {
                        if (!groupedItems.containsKey(item.getListId())) {
                            groupedItems.put(item.getListId(), new ArrayList<>());
                        }
                        groupedItems.get(item.getListId()).add(item);
                    }

                    for (int listId : groupedItems.keySet()) {
                        List<Item> groupItems = groupedItems.get(listId);
                        if(groupItems != null){
                            groupItems.sort((item1, item2) -> {
                                if (item1.getName() == null || item2.getName() == null) {
                                    return 0;
                                }
                                // Extract numerical part of the name for comparison
                                String regex = "\\D+";
                                String num1 = item1.getName().replaceAll(regex, "");
                                String num2 = item2.getName().replaceAll(regex, "");

                                // If names don't contain a number, fall back to lexicographic comparison
                                if (num1.isEmpty() || num2.isEmpty()) {
                                    return item1.getName().compareTo(item2.getName());
                                }

                                return Integer.compare(Integer.parseInt(num1), Integer.parseInt(num2));
                            });
                        }
                        listDataHeader.add("List ID: " + listId);
                        listDataChild.put("List ID: " + listId, groupItems);
                    }

                    listAdapter = new ExpandableListAdapter(MainActivity.this, listDataHeader, listDataChild);
                    expandableListView.setAdapter(listAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Item>> call, @NonNull Throwable t) {
                Log.e("onFailure: ", "Failed to fetch data from URL");
            }
        });
    }

}
