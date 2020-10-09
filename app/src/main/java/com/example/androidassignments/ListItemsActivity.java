package com.example.androidassignments;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "ListItemsActivity";
    private final int REQUEST_CODE = 1;
    private final CharSequence switchOnText = "Switch is On";
    private final CharSequence switchOffText = "Switch is Off";
    private final int switchOnDuration = Toast.LENGTH_SHORT;
    private final int switchOffDuration = Toast.LENGTH_LONG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        // Set listener for camera button
        final Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ImageButton imageButton = findViewById(R.id.imageButton1);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                takePicture(takePictureIntent);
            }
        });

        // Set listener for switch button
        final Switch switch1 = findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showToastMessage(isChecked);
            }
        });

        // Set listener for checkbox
        final CheckBox checkbox = findViewById(R.id.checkBox);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
                builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Log.i(ACTIVITY_NAME, "Clicked cancel on dialog box");
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

    private void takePicture(Intent takePictureIntent) {
        try {
            //if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
                startActivityForResult(takePictureIntent, REQUEST_CODE);
           // } else {
           //     Log.e(ACTIVITY_NAME, "Failed to open camera.");
            //}
        } catch (ActivityNotFoundException e) {
            Log.e(ACTIVITY_NAME, "Failed to open camera.");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageButton imageButton = findViewById(R.id.imageButton1);
            imageButton.setImageBitmap(imageBitmap);
        }
    }

    private void showToastMessage(boolean isChecked) {
        Toast toast;
        if (isChecked) {
            toast = Toast.makeText(this, switchOnText, switchOnDuration);
        } else {
            toast = Toast.makeText(this, switchOffText, switchOffDuration);
        }
        toast.show();
    }
}