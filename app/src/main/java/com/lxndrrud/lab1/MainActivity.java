package com.lxndrrud.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.lxndrrud.lab1.adapters.ItemListAdapter;
import com.lxndrrud.lab1.domain.Item;
import com.lxndrrud.lab1.repositories.ItemRepo;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ListView itemsListView;
    private Button addButton;
    private Button deleteButton;
    private TextView errorTextView;

    private ItemRepo itemRepo;

    private long selectedItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemRepo = new ItemRepo(MainActivity.this);
        setContentView(R.layout.activity_main);
        itemsListView = findViewById(R.id.itemsListView);
        itemsListView.setOnItemClickListener((adapterView, view, index, id) -> {
            selectedItem = id;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayItems();
    }

    public void displayItems() {
        itemsListView = findViewById(R.id.itemsListView);
        List<Item> itemsList = itemRepo.getAll();
        ItemListAdapter adapter = new ItemListAdapter(this, (ArrayList<Item>) itemsList);
        itemsListView.setAdapter(adapter);
    }

    public void onAddButtonClick(View view) {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivity(intent);
    }

    public void onShowButtonClick(View view) {
        if (selectedItem != 0) {
            Intent intent = new Intent(this, DisplayItemActivity.class);
            intent.putExtra("selectedItemId", ((Long) selectedItem).toString());
            startActivity(intent);
        }
    }

    public void onDeleteButtonClick(View view) {
        if (selectedItem != 0) {
            itemRepo.DeleteItem(selectedItem);
            selectedItem = 0;
        }
        displayItems();
    }
}