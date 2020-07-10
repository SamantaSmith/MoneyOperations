package com.example.hrenmoney;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditOperationActivity extends AppCompatActivity {

    //ШАГ 40
    public static final String EXTRA_CATEGORY = "com.extracategory";
    public static final String EXTRA_DESCRIPTION = "com.extradescription";
    public static final String EXTRA_VALUE = "com.extravalue";
    //ШАг 40

    //ШАГ 35
    private EditText editTextCategory;
    private EditText editTextDescription;
    private EditText editTextValue;
    //ШАГ 35


    //ШАГ 54
    public static final String EXTRA_ID = "com.extraid";
    //ШАГ 54


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_operation);

        //ШАГ 36
        editTextCategory = findViewById(R.id.edit_text_category);
        editTextDescription = findViewById(R.id.edit_text_description);
        editTextValue = findViewById(R.id.edit_text_value);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        //ШАГ 36

        //ШАГ 57
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {

            setTitle("Edit Operation");
            editTextCategory.setText(intent.getStringExtra(EXTRA_CATEGORY));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            editTextValue.setText(String.valueOf(intent.getIntExtra(EXTRA_VALUE, 1)));
        } else {

            setTitle("Add Operation");
        }
        //ШАГ 57

    }

    //ШАГ 37
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_operation_menu, menu);
        return true;
    }
    //ШАГ 37

    //ШАГ 38
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.save_operation:
                saveOperation();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    //ШАГ 38

    //ШАГ 39
    private void saveOperation() {

        String category = editTextCategory.getText().toString();
        String description = editTextDescription.getText().toString();
        int value;

        if (editTextValue != null) {
            value = Integer.parseInt(editTextValue.getText().toString());
        } else {
            Toast.makeText(this, "Пожалуйста, введите сумму операции", Toast.LENGTH_SHORT).show();
            return;
        }

        if (category.trim().isEmpty()) {
            category = "Без категории";
        }
        if (description.trim().isEmpty()) {
            description = "Без описания";
        }
        //ШАГ 39

        //ШАГ 41
        Intent data = new Intent();
        data.putExtra(EXTRA_CATEGORY, category);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_VALUE, value);

            //ШАГ 58
            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            if (id != -1) {
                data.putExtra(EXTRA_ID, id);
            }
            //ШАГ 58

        setResult(RESULT_OK, data);
        finish();
        //ШАГ 41

    }


}
