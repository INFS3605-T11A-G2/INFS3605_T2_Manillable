
package com.example.manillable;

        import android.content.DialogInterface;
        import android.os.Bundle;

        import androidx.appcompat.app.AlertDialog;
        import androidx.fragment.app.Fragment;

        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;

        import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hometab, container, false);





        Log.d(TAG, "View Inflated");
        return view;
    }


}