package com.example.manillable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

import com.googlecode.tesseract.android.TessBaseAPI;
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.text.PDFTextStripper;

public class CameraFragment extends Fragment {

    private static final int CHOOSE_FILE = 2;
    private static final int TAKE_IMAGE = 1;

    Button fromFilesButton;
    TextView previewTextView;
    View inflatedView;

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
            this.previewTextView.setText(uri.toString());

            ContentResolver contentResolver = this.getContext().getContentResolver();
            InputStream inputStream;

            try {
                inputStream = contentResolver.openInputStream(uri);
                PDDocument pdfDocument = PDDocument.load(inputStream);

                PDFTextStripper pdfTextStripper = new PDFTextStripper();

                String recognizedText = pdfTextStripper.getText(pdfDocument);
                System.out.print(recognizedText);

                pdfDocument.close();
                inputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


        } else if (requestCode == TAKE_IMAGE) {
            this.previewTextView.setText("Image received....processing....");

            TessBaseAPI tess = new TessBaseAPI();
            String dataPath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "tessdata").getAbsolutePath();

            if (!tess.init(dataPath, "eng")) {
                tess.recycle();
                return;
            }

            //tess.setImage(bmpFile);
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

        PDFBoxResourceLoader.init(getContext());
        return inflatedView;
    }
}
