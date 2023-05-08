package com.lxndrrud.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.lxndrrud.lab1.domain.Item;
import com.lxndrrud.lab1.repositories.ItemRepo;

import java.util.Date;

public class DisplayItemActivity extends AppCompatActivity {
    private ItemRepo itemRepo;
    private EditText titleEditText;
    private EditText textEditText;
    private long selectedItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemRepo = new ItemRepo(DisplayItemActivity.this);
        setContentView(R.layout.display_item_activity);


        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            selectedItemId = Integer.parseInt(extras.getString("selectedItemId"));
            loadSelectedItem(selectedItemId);
        }
    }

    public void onUpdateButtonClick(View view) {
        titleEditText = findViewById(R.id.titleDisplayEditText);
        textEditText = findViewById(R.id.textDisplayEditText);
        itemRepo.UpdateItem(
            new Item(
                    selectedItemId,
                    titleEditText.getText().toString(),
                    textEditText.getText().toString(),
                    new Date()
            )
        );
        goToMainActivity(view);
    }

    public void onCancelUpdateButtonClick(View view) {
        goToMainActivity(view);
    }

    public void onDeleteDisplayButtonClick(View view) {
        itemRepo.DeleteItem(selectedItemId);
        goToMainActivity(view);
    }

    private void goToMainActivity(View view) {
        /*Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

         */
        finish();
    }

    private void loadSelectedItem(long selectedItemId) {
        titleEditText = findViewById(R.id.titleDisplayEditText);
        textEditText = findViewById(R.id.textDisplayEditText);
        Item item = itemRepo.GetItemById(selectedItemId);
        if (item != null) {
            titleEditText.setText(item.getTitle());
            textEditText.setText(item.getText());

        }
    }
}
