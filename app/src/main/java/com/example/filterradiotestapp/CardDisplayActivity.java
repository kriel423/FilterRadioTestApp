package com.example.filterradiotestapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CardDisplayActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private CardDisplayActivityAdapter mAdapter;

    private DatabaseReference mDatabaseRef;
    private List<Upload> mUpload;

    private String selectedFilter = "all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_display);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUpload = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("cards");

        initSearchViewItems();

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUpload.add(upload);
                }

                mAdapter = new CardDisplayActivityAdapter(CardDisplayActivity.this, mUpload);
                mRecyclerView.setAdapter(mAdapter);

                mAdapter.setOnItemClickListener(new CardDisplayActivityAdapter.OnItemClickListener() {
                    @Override
                    public void onItemDecreaseClick(int position) {
                        String val = String.valueOf(Integer.parseInt(mUpload.get(position).getmQuantity().toString()) - 1);
                        mUpload.get(position).setmQuantity(val);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CardDisplayActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSearchViewItems()
    {
        SearchView searchView = findViewById(R.id.search_view_items);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Upload> uploads = new ArrayList<>();
                for(Upload uploadItem : mUpload)
                {
                    if(uploadItem.getmFilter().toLowerCase().contains(newText.toLowerCase()))
                    {
                        uploads.add(uploadItem);
                    }
                }

                CardDisplayActivityAdapter cardAdapter = new CardDisplayActivityAdapter(getApplicationContext(), uploads);
                mRecyclerView.setAdapter(cardAdapter);
                return false;
            }
        });
    }

    private void filteredList(String status)
    {
        selectedFilter = status;

        ArrayList<Upload> filteredItemList = new ArrayList<Upload>();

        for(Upload upload : mUpload)
        {
            if(upload.getmFilter().toLowerCase().contains(status))
            {
                filteredItemList.add(upload);
            }
        }

        CardDisplayActivityAdapter cardAdapter = new CardDisplayActivityAdapter(getApplicationContext(), filteredItemList);
        mRecyclerView.setAdapter(cardAdapter);
    }

    public void allFilterTapped(View view)
    {
        selectedFilter = "all";
        CardDisplayActivityAdapter cardAdapter = new CardDisplayActivityAdapter(getApplicationContext(), mUpload);
        mRecyclerView.setAdapter(cardAdapter);
    }

    public void essentialFilterTapped(View view) {
        filteredList("essential");
    }

    public void confectioneryFilterTapped(View view) {
        filteredList("confectionery");
    }

    public void toiletriesFilterTapped(View view) {
        filteredList("toiletries");
    }

    public void protectionFilterTapped(View view) {
        filteredList("protection");
    }
}