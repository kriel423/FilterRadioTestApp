package com.example.filterradiotestapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardDisplayActivityAdapter extends RecyclerView.Adapter<CardDisplayActivityAdapter.CardDisplayViewHolder> {

    private Context mContext;
    private List<Upload> mUploads;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemDecreaseClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public CardDisplayActivityAdapter(Context context, List<Upload> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    //recommended to keep the class static

    public class CardDisplayViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewTitle;
        public TextView textViewDescription;
        public TextView textViewFilter;
        public TextView textViewQuantity;
        public Button decreaseQuantity;
        public String itemCountValue;

        public CardDisplayViewHolder(@NonNull View itemView)
        {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewFilter = itemView.findViewById(R.id.text_view_filter);
            textViewQuantity = itemView.findViewById(R.id.text_view_quantity);
            decreaseQuantity = itemView.findViewById(R.id.subtract_quantity_button);

            decreaseQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    itemCountValue = textViewQuantity.getText().toString();

                    if(mListener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            if(itemCountValue == "0")
                            {
                                textViewQuantity.setText("Sold Out");
                                textViewQuantity.setTextColor(Color.RED);
                            }
                            else if(Integer.parseInt(itemCountValue) > 0)
                            {
                                String val = String.valueOf(Integer.parseInt(itemCountValue)-1);
                                textViewQuantity.setText(val);
                            }
                            else if(itemCountValue.equals("Sold Out"))
                            {
                                Toast.makeText(mContext, "Item already sold out", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(mContext, "Item already sold out", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public CardDisplayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.card_item, parent, false);
        return new CardDisplayViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardDisplayViewHolder holder, int position) {
        Upload uploadCurrent = mUploads.get(position);
        holder.textViewTitle.setText(uploadCurrent.getmTitle());
        holder.textViewDescription.setText(uploadCurrent.getmDescription());
        holder.textViewFilter.setText(uploadCurrent.getmFilter());
        holder.textViewQuantity.setText(uploadCurrent.getmQuantity());
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

}
