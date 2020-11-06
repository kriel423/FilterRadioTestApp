package com.example.filterradiotestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView mEditTextTitle;
    private TextView mEditTextDescription;
    private TextView mEditTextFilter;
    private TextView mQuantity;
    private RadioButton mRadioEssential;
    private RadioButton mRadioToiletries;
    private RadioButton mRadioConfectionery;
    private RadioButton mRadioProtection;
    private Button mUploadButton;
    private TextView mShowCardsText;
    private ProgressBar mProgressBar;

    private StorageReference mStorageReference;
    private DatabaseReference mDatabaseReference;

    private StorageTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextTitle = findViewById(R.id.edit_text_title);
        mEditTextDescription = findViewById(R.id.edit_text_description);
        mQuantity = findViewById(R.id.quantity);
        mRadioEssential = findViewById(R.id.essential_button);
        mRadioToiletries = findViewById(R.id.toiletries_button);
        mRadioConfectionery = findViewById(R.id.confection_button);
        mRadioProtection = findViewById(R.id.protection_button);
        mUploadButton = findViewById(R.id.upload_button);
        mShowCardsText = findViewById(R.id.text_view_cards);

        mStorageReference = FirebaseStorage.getInstance().getReference("cards");
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("cards");

        mUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadItem();
            }
        });

        mShowCardsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItemsActivity();
            }
        });
    }

    private void uploadItem()
    {
        if(mRadioEssential.isChecked())
        {
            mEditTextFilter = mRadioEssential;
            Upload upload = new Upload(mEditTextTitle.getText().toString().trim(),
                    mEditTextDescription.getText().toString().trim(),
                    mEditTextFilter.getText().toString().trim(),
                    mQuantity.getText().toString().trim());
            String uploadId = mDatabaseReference.push().getKey();
            mDatabaseReference.child(uploadId).setValue(upload);
        }
        else if(mRadioConfectionery.isChecked())
        {
            mEditTextFilter = mRadioConfectionery;
            Upload upload = new Upload(mEditTextTitle.getText().toString().trim(),
                    mEditTextDescription.getText().toString().trim(),
                    mEditTextFilter.getText().toString().trim(),
                    mQuantity.getText().toString().trim());
            String uploadId = mDatabaseReference.push().getKey();
            mDatabaseReference.child(uploadId).setValue(upload);
        }
        else if(mRadioProtection.isChecked())
        {
            mEditTextFilter = mRadioProtection;
            Upload upload = new Upload(mEditTextTitle.getText().toString().trim(),
                    mEditTextDescription.getText().toString().trim(),
                    mEditTextFilter.getText().toString().trim(),
                    mQuantity.getText().toString().trim());
            String uploadId = mDatabaseReference.push().getKey();
            mDatabaseReference.child(uploadId).setValue(upload);
        }
        else if(mRadioToiletries.isChecked())
        {
            mEditTextFilter = mRadioToiletries;
            Upload upload = new Upload(mEditTextTitle.getText().toString().trim(),
                    mEditTextDescription.getText().toString().trim(),
                    mEditTextFilter.getText().toString().trim(),
                    mQuantity.getText().toString().trim());
            String uploadId = mDatabaseReference.push().getKey();
            mDatabaseReference.child(uploadId).setValue(upload);
        }
        else
        {
            mEditTextFilter.setText("");
            Upload upload = new Upload(mEditTextTitle.getText().toString().trim(),
                    mEditTextDescription.getText().toString().trim(),
                    mEditTextFilter.getText().toString().trim(),
                    mQuantity.getText().toString().trim());
            String uploadId = mDatabaseReference.push().getKey();
            mDatabaseReference.child(uploadId).setValue(upload);
        }
    }

    private void openItemsActivity()
    {
        Intent intent = new Intent(this, CardDisplayActivity.class);
        startActivity(intent);
    }


}