package com.example.hrenmoney;

import android.graphics.Path;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//ШАГ 23
//public class OperationAdapter extends RecyclerView.Adapter<OperationAdapter.OperationHolder> {

//ШАГ 60
public class OperationAdapter extends ListAdapter<Operation, OperationAdapter.OperationHolder> {

    //ШАГ 24
    //private List<Operation> operations = new ArrayList<>();


    //ШАГ 51
    private OnItemClickListener listener;


    //ШАГ 61
    public OperationAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Operation> DIFF_CALLBACK = new DiffUtil.ItemCallback<Operation>() {
        @Override
        public boolean areItemsTheSame(@NonNull Operation oldItem, @NonNull Operation newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Operation oldItem, @NonNull Operation newItem) {

            return oldItem.getCategory().equals(newItem.getCategory()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getValue() == newItem.getValue();
        }
    };
    //ШАГ 61


    //ШАГ 25
    @NonNull
    @Override
    public OperationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.operaion_item, parent, false);
        return new OperationHolder(itemView);
    }
    //ШАГ 25


    //ШАГ 26
    @Override
    public void onBindViewHolder(@NonNull OperationHolder holder, int position) {


        //Operation currentOperation = operations.get(position);

        //ШАГ 62
        Operation currentOperation = getItem(position);

        holder.textViewCategory.setText(currentOperation.getCategory());
        holder.textViewDescription.setText(currentOperation.getDescription());
        holder.textViewValue.setText(String.valueOf(currentOperation.getValue()));
        holder.textViewValue.append("₽");
    }
    //ШАГ 26


/*
    @Override
    public int getItemCount() {
        //ШАГ 27
        return operations.size();
    }

    //ШАГ 28
    public void setOperations(List<Operation> operations) {

        this.operations = operations;
        notifyDataSetChanged();
    }
    //ШАГ 28
*/


    //ШАГ 22
    class OperationHolder extends RecyclerView.ViewHolder {

        private TextView textViewCategory;
        private TextView textViewDescription;
        private TextView textViewValue;


        public OperationHolder(@NonNull View itemView) {
            super(itemView);

            textViewCategory = itemView.findViewById(R.id.text_view_category);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewValue = itemView.findViewById(R.id.text_view_value);

            //ШАГ 52
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        //listener.onItemClick(operations.get(position));
                        //ШАГ 63
                        listener.onItemClick(getItem(position));
                    }
                }
            });
            //ШАГ 52

        }
    }
    //ШАГ 22

    //ШАГ 46
    public Operation getOperationAt(int position) {

        //ШАГ 63
        return getItem(position);
        //return operations.get(position);
    }
    //ШАГ 46


    //ШАГ 50
    public interface OnItemClickListener {
        void onItemClick(Operation operation);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    //ШАГ 50
}
