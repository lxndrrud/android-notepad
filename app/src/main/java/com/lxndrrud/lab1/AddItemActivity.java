package com.lxndrrud.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.lxndrrud.lab1.domain.Item;
import com.lxndrrud.lab1.repositories.ItemRepo;

import java.util.Date;


public class AddItemActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText textEditText;

    private Button saveButton;
    private Button cancelAddButton;
    private ItemRepo itemRepo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemRepo = new ItemRepo(AddItemActivity.this);
        setContentView(R.layout.add_item_activity);
    }


    public void onSaveButtonClick(View view) {
        titleEditText = findViewById(R.id.titleEditText);
        textEditText = findViewById(R.id.textEditText);

        itemRepo.AddItem(
            new Item(
                titleEditText.getText().toString(),
                textEditText.getText().toString(),
                new Date()
            )
        );
        goToMainActivity(view);
    }

    public void onCancelAddButtonClick(View view) {
        goToMainActivity(view);
    }

    private void goToMainActivity(View view) {
        /*
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
         */
        finish();
    }
}
