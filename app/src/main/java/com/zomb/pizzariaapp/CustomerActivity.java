package com.zomb.pizzariaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CustomerActivity extends AppCompatActivity {
    public static final String KEY_CLICKED = "com.zomb.pizzariaapp.KEY_CLICKED";
    public static final String KEY_NAME = "com.zomb.pizzariaapp.KEY_NAME";
    public static final String KEY_PHONE = "com.zomb.pizzariaapp.KEY_PHONE";
    public static final String KEY_ADDRESS = "com.zomb.pizzariaapp.KEY_ADDRESS";
    public static final String KEY_EMAIL = "com.zomb.pizzariaapp.KEY_EMAIL";
    private EditText nameInput, phoneInput, addressInput, emailInput;
    private TextView receiptInfo, receiptTotal;
    String name, phone, address, email;
    double total;
    boolean clicked = false;
    Order mOrder;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        Intent intent = getIntent();
        mOrder = new Order();

        mOrder.setPizzaSize(intent.getStringExtra("com.zomb.pizzariaapp.KEY_SIZE"));
        mOrder.setSizePrice(intent.getDoubleExtra("com.zomb.pizzariaapp.KEY_SIZE_PRICE", 0));
        mOrder.setToppings(intent.getStringExtra("com.zomb.pizzariaapp.KEY_TOPPINGS"));
        mOrder.setToppingPrice(intent.getDoubleExtra("com.zomb.pizzariaapp.KEY_TOPPING_PRICE", 0));

        receiptInfo = findViewById(R.id.txtOrderInfo);
        receiptTotal = findViewById(R.id.txtOrderTotal);

        btnSubmit = findViewById(R.id.btnSubmit);

        if (savedInstanceState != null) {
            clicked = savedInstanceState.getBoolean(KEY_CLICKED);
            if (clicked) {
                submitOrder(btnSubmit);
                nameInput.setText(savedInstanceState.getString(KEY_NAME));
                phoneInput.setText(savedInstanceState.getString(KEY_PHONE));
                addressInput.setText(savedInstanceState.getString(KEY_ADDRESS));
                emailInput.setText(savedInstanceState.getString(KEY_EMAIL));
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(KEY_NAME, String.valueOf(nameInput.getText()));
        outState.putString(KEY_PHONE, String.valueOf(phoneInput.getText()));
        outState.putString(KEY_ADDRESS, String.valueOf(addressInput.getText()));
        outState.putString(KEY_EMAIL, String.valueOf(emailInput.getText()));
        if (btnSubmit.getVisibility() == View.VISIBLE) {
            outState.putBoolean(KEY_CLICKED, false);
        } else {
            outState.putBoolean(KEY_CLICKED, true);
        }
    }

    public void submitOrder(View view) {

        nameInput = findViewById(R.id.etxtName);
        name = nameInput.getText().toString();
        mOrder.setName(name);
        phoneInput = findViewById(R.id.etxtPhone);
        phone = phoneInput.getText().toString();
        mOrder.setPhone(phone);
        addressInput = findViewById(R.id.etxtAddress);
        address = addressInput.getText().toString();
        mOrder.setAddress(address);
        emailInput = findViewById(R.id.etxtEmail);
        email = emailInput.getText().toString();
        mOrder.setEmail(email);

        // Sets visibility for form and button to GONE
        btnSubmit.setVisibility(View.GONE);
        nameInput.setVisibility(View.GONE);
        phoneInput.setVisibility(View.GONE);
        addressInput.setVisibility(View.GONE);
        emailInput.setVisibility(View.GONE);

        // Sets visibility of textViews to VISIBLE
        receiptInfo.setVisibility(View.VISIBLE);
        receiptTotal.setVisibility(View.VISIBLE);

        //Create a string for the text view including the name, address, phone, email, pizza size, and list of toppings
        receiptInfo = (TextView) findViewById(R.id.txtOrderInfo);
        receiptInfo.setText(mOrder.setTextInfo(mOrder.getName(), mOrder.getAddress(), mOrder.getPhone(), mOrder.getEmail(), mOrder.getPizzaSize(), mOrder.getToppings()));

        //Calculates the total price and display in textView under the customer info
        receiptTotal = findViewById(R.id.txtOrderTotal);
        total = mOrder.calculateTotal(mOrder.getSizePrice(), mOrder.getToppingPrice());     //Made a "total" variable to store the double. getSizePrice and getToppingPrice are set as arguments for the calculate method
        mOrder.setTotalPrice(total);                        //sets total to the total price
        receiptTotal.setText(getString(R.string.total) + " " +DecimalFormat.getCurrencyInstance().format(mOrder.getTotalPrice()));     //creates string to display the total price with currency notation
    }
}