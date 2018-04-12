package com.trungbkit.currencyexchanger;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

  private Spinner srcSpinner;
  private Spinner destSpinner;
  private EditText srcValueEditText;
  private TextView resultTextView;

  private HashMap<String, Unit> currencyList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    currencyList = new HashMap<>();
    currencyList.put("EUR", new Unit("EUR", 1));
    currencyList.put("VND", new Unit("VND", 28053.382784));
    currencyList.put("USD", new Unit("USD", 1.231222));
    currencyList.put("RON", new Unit("RON", 4.656969));
    currencyList.put("AED", new Unit("RON", 4.52215));
    currencyList.put("AFN", new Unit("RON", 85.816138));

    srcSpinner = findViewById(R.id.spn_source);
    destSpinner = findViewById(R.id.spn_dest);
    srcValueEditText = findViewById(R.id.et_source);
    resultTextView = findViewById(R.id.tv_result);

    ArrayAdapter<String> adapter = new ArrayAdapter<String>
            (this, android.R.layout.simple_spinner_item, currencyList.keySet().toArray(new String[currencyList.size()]));
    adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
    srcSpinner.setAdapter(adapter);
    destSpinner.setAdapter(adapter);

    srcValueEditText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {}
      @Override
      public void afterTextChanged(Editable s) {
        onChangeInput();
      }
    });
    srcSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        onChangeInput();
      }
      @Override
      public void onNothingSelected(AdapterView<?> parent) {}
    });
    destSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        onChangeInput();
      }
      @Override
      public void onNothingSelected(AdapterView<?> parent) {}
    });
  }
  @SuppressLint("DefaultLocale")
  private void onChangeInput() {
    try {
      String srcUnitName = srcSpinner.getSelectedItem().toString();
      if (srcUnitName.equals("")) {
        resultTextView.setText("...");
        return;
      }
      String destUnitName = destSpinner.getSelectedItem().toString();
      double srcValue = Double.parseDouble(srcValueEditText.getText().toString());

      Unit srcUnit = currencyList.get(srcUnitName);
      Unit destUnit = currencyList.get(destUnitName);

      double result = srcValue * srcUnit.exchangeTo(destUnit);
      resultTextView.setText(String.format("%,2f", result));
    }
    catch (Exception ex) {
      resultTextView.setText("...");
    }
  }
}
