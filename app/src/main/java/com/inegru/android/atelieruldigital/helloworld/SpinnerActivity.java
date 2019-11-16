package com.inegru.android.atelieruldigital.helloworld;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * .
 */
public class SpinnerActivity extends AppCompatActivity {

    private Spinner apiVersionsSpinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_spinner);

        apiVersionsSpinner = findViewById(R.id.api_versions);

        final ArrayAdapter<String> adapter = getApiVersionsAdapter(this);
        apiVersionsSpinner.setAdapter(adapter);




        apiVersionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = adapter.getItem(position);
                Log.d("DEBUG", "Selected " + selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(), "Selection cancelled", Toast.LENGTH_SHORT)
                     .show();
            }
        });
        // apiVersionsSpinner.setSelection(1);
    }

    private ArrayAdapter<String> getApiVersionsAdapter(Context context) {
        return new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line,
                                  getApiVersionsData());
    }

    @NonNull
    private List<String> getApiVersionsData() {
        List<String> items = new ArrayList<>();
        items.add("Clear");
        items.add("Cupcake");
        items.add("Donut");
        items.add("Eclair");
        items.add("Froyo");
        items.add("Gingerbread");
        items.add("Honeycomb");
        items.add("Sandwich");
        items.add("KitKat");
        items.add("Lollipop");
        items.add("Marshmallow");
        items.add("Nougat");
        items.add("Oreo");
        items.add("Pie");
        return items;
    }
}
