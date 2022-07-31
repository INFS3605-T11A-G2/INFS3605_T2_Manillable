package com.example.manillable;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CameraFragment extends Fragment {

    private static final int CHOOSE_FILE = 2;
    private static final int TAKE_IMAGE = 1;

    Button fromFilesButton;
    TextView previewTextView;
    View inflatedView;

    Uri pickedUri;

    private void openFile(Uri pickerInitialUri, String mimeType) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType(mimeType);

        this.startActivityForResult(intent, CHOOSE_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null && resultCode != Activity.RESULT_OK) {
            return;
        }

        System.out.println(data);

        if (requestCode == CHOOSE_FILE) {
            Uri uri = data.getData();
            this.pickedUri = uri;
            this.previewTextView.setText(uri.toString());
        } else if (requestCode == TAKE_IMAGE) {
            this.previewTextView.setText("Image received....processing....");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflatedView = inflater.inflate(R.layout.fragment_cameratab, container, false);

        TextView textView = (TextView) this.inflatedView.findViewById(R.id.textView3);
        this.previewTextView = textView;

        CameraFragment self = this;

        this.fromFilesButton = (Button) this.inflatedView.findViewById(R.id.button_from_files);
        this.fromFilesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("clicked from files btn");
                self.openFile(null, "application/pdf");
            }
        });

        Button fromCameraButton = (Button) this.inflatedView.findViewById(R.id.button_from_camera);
        fromCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    self.startActivityForResult(takePictureIntent, TAKE_IMAGE);
                } catch (ActivityNotFoundException e) {
                    Log.e("picker", e.toString());
                }
            }
        });
        return inflatedView;
    }
}
