
package com.example.manillable;

        import android.os.Bundle;

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
    EditText editName, editAmount;
    Button submitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        editName = view.findViewById(R.id.client_name);
        editAmount = view.findViewById(R.id.amount);
        submitButton = view.findViewById(R.id.button);

        DatabaseHelper databaseHelper = DatabaseHelper.getDB(getActivity().getApplicationContext());

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String clientName = editName.getText().toString();
                String amount = editAmount.getText().toString();


                databaseHelper.invoiceDao().addInvoice(
                        new Invoice(clientName, amount)
                );

                ArrayList<Invoice> arrInvoices = (ArrayList<Invoice>) databaseHelper.invoiceDao()
                        .getAllInvoice();

                for(int i = 0; i<arrInvoices.size(); i++) {
                    Log.d(TAG, "Client Name: " + arrInvoices.get(i).getClientName() +
                            " Amount: " + arrInvoices.get(i).getAmount());
                }

            }
        });

        // Inflate the layout for this fragment
        Log.d(TAG, "View Inflated");
        return view;
    }
}