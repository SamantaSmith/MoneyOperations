package com.example.hrenmoney;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //ШАГ 18
    private OperationViewModel operationViewModel;
    public static final int ADD_OPERATION_REQUEST = 1;
    //ШАГ 18


    //ШАГ 55
    public static final int EDIT_OPERATION_REQUEST = 2;
    //ШАГ 55


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ШАГ 42
        FloatingActionButton buttonAddOperation = findViewById(R.id.button_add_operation);
        buttonAddOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEditOperationActivity.class);
                startActivityForResult(intent, ADD_OPERATION_REQUEST);

            }
        });
        //ШАГ 42


        //ШАГ 29
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final OperationAdapter adapter = new OperationAdapter();
        recyclerView.setAdapter(adapter);
        //ШАГ 29


        //ШАГ 19
        operationViewModel = ViewModelProviders.of(this).get(OperationViewModel.class);
        operationViewModel.getAllOperations().observe(this, new Observer<List<Operation>>() {
            @Override
            public void onChanged(List<Operation> operations) {


                //Toast.makeText(getApplication(), "Changed", Toast.LENGTH_SHORT).show();
                //ШАГ 30
                //adapter.setOperations(operations);
                adapter.submitList(operations);
            }
        });
        //ШАГ 19

        //ШАГ 45
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                //ШАГ 47
                operationViewModel.delete(adapter.getOperationAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Operation deleted", Toast.LENGTH_SHORT).show();
                //ШАГ 47

            }
        }).attachToRecyclerView(recyclerView);
        //ШАГ 45

        //ШАГ 53
        adapter.setOnItemClickListener(new OperationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Operation operation) {

                Intent intent = new Intent(MainActivity.this, AddEditOperationActivity.class);
                intent.putExtra(AddEditOperationActivity.EXTRA_CATEGORY, operation.getCategory());
                intent.putExtra(AddEditOperationActivity.EXTRA_DESCRIPTION, operation.getDescription());
                intent.putExtra(AddEditOperationActivity.EXTRA_VALUE, operation.getValue());


                //ШАГ 56
                intent.putExtra(AddEditOperationActivity.EXTRA_ID, operation.getId());
                startActivityForResult(intent, EDIT_OPERATION_REQUEST);
                //ШАГ 56
            }
        });
        //ШАГ 53

    }

    //ШАГ 43
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_OPERATION_REQUEST && resultCode == RESULT_OK) {

            String category = data.getStringExtra(AddEditOperationActivity.EXTRA_CATEGORY);
            String description = data.getStringExtra(AddEditOperationActivity.EXTRA_DESCRIPTION);
            int value = data.getIntExtra(AddEditOperationActivity.EXTRA_VALUE, 1);

            Operation operation = new Operation(category, description, value);
            operationViewModel.insert(operation);

            Toast.makeText(this, "Operation saved", Toast.LENGTH_SHORT).show();
        }

        //ШАГ 59
            else if (requestCode == EDIT_OPERATION_REQUEST && resultCode == RESULT_OK) {

                int id = data.getIntExtra(AddEditOperationActivity.EXTRA_ID, -1);

                if (id == -1) {
                    Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show();
                    return;
                }

                String category = data.getStringExtra(AddEditOperationActivity.EXTRA_CATEGORY);
                String description = data.getStringExtra(AddEditOperationActivity.EXTRA_DESCRIPTION);
                int value = data.getIntExtra(AddEditOperationActivity.EXTRA_VALUE, 1);

                Operation operation = new Operation(category, description, value);
                operation.setId(id);
                operationViewModel.update(operation);
                Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
            }
        //ШАГ 59


        else {

            Toast.makeText(this, "Operation isnot saved", Toast.LENGTH_SHORT).show();
        }


    }
    //ШАГ 43


    //ШАГ 49
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.delete_all_operations:
                operationViewModel.deleteAllOperations();
                Toast.makeText(this, "All operations deleted", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //ШАГ 49
}
