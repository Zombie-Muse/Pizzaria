package com.zomb.pizzariaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomerActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "com.zomb.pizzariaapp.EXTRA_NAME";
    public static final String EXTRA_PHONE = "com.zomb.pizzariaapp.EXTRA_PHONE";
    public static final String EXTRA_ADDRESS = "com.zomb.pizzariaapp.EXTRA_ADDRESS";
    public static final String EXTRA_EMAIL = "com.zomb.pizzariaapp.EXTRA_EMAIL";
    private EditText nameInput, phoneInput, addressInput, emailInput;
    private TextView receiptInfo, receiptTotal;
    private String name, phone, address, email, pSize, pToppings;
    private double total, pTopPrice, pSizePrice;
    private ArrayList<String> toppings;
    private Order mOrder;
    private Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_customer);
        if (savedInstanceState != null) {
            pSize = savedInstanceState.getString(OrderActivity.EXTRA_SIZE);
            pToppings = savedInstanceState.getString(OrderActivity.EXTRA_TOPPINGS);
            pTopPrice = savedInstanceState.getDouble(OrderActivity.EXTRA_TOPPING_PRICE);
            pSizePrice = savedInstanceState.getDouble(OrderActivity.EXTRA_SIZE_PRICE);
            name = savedInstanceState.getString(EXTRA_NAME);
            phone = savedInstanceState.getString(EXTRA_PHONE);
            address = savedInstanceState.getString(EXTRA_ADDRESS);
            email = savedInstanceState.getString(EXTRA_EMAIL);

        } else {
            setContentView(R.layout.activity_customer);
            nameInput = (EditText) findViewById(R.id.etxtName);
            phoneInput = (EditText) findViewById(R.id.etxtPhone);
            addressInput = (EditText) findViewById(R.id.etxtAddress);
            emailInput = (EditText) findViewById(R.id.etxtEmail);
        }

        nameInput = (EditText) findViewById(R.id.etxtName);
        phoneInput = (EditText) findViewById(R.id.etxtPhone);
        addressInput = (EditText) findViewById(R.id.etxtAddress);
        emailInput = (EditText) findViewById(R.id.etxtEmail);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameInput.getText().toString();
                address = addressInput.getText().toString();
                phone = phoneInput.getText().toString();
                email = emailInput.getText().toString();

                mOrder.setName(name);
                mOrder.setAddress(address);
                mOrder.setPhone(phone);
                mOrder.setEmail(email);

                submitOrder();
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString(OrderActivity.EXTRA_SIZE, pSize);
        outState.putDouble(OrderActivity.EXTRA_SIZE_PRICE, pSizePrice);
        outState.putString(OrderActivity.EXTRA_TOPPINGS, pToppings);
        outState.putDouble(OrderActivity.EXTRA_TOPPING_PRICE, pTopPrice);

        outState.putString(EXTRA_NAME, name);
        outState.putString(EXTRA_PHONE, phone);
        outState.putString(EXTRA_ADDRESS, address);
        outState.putString(EXTRA_EMAIL, email);
    }

    public void submitOrder() {
        //set visibility on customer form to GONE
        Intent intent = getIntent();
        pSize = intent.getStringExtra(OrderActivity.EXTRA_SIZE);
        pToppings = intent.getStringExtra(OrderActivity.EXTRA_TOPPINGS);
        pTopPrice = intent.getDoubleExtra(OrderActivity.EXTRA_TOPPING_PRICE, 0);
        pSizePrice = intent.getDoubleExtra(OrderActivity.EXTRA_SIZE_PRICE, 0);
        name = intent.getStringExtra(EXTRA_NAME);
        phone = intent.getStringExtra(EXTRA_PHONE);
        address = intent.getStringExtra(EXTRA_ADDRESS);
        email = intent.getStringExtra(EXTRA_EMAIL);

        btnSubmit.setVisibility(View.GONE);
        nameInput.setVisibility(View.GONE);
        phoneInput.setVisibility(View.GONE);
        addressInput.setVisibility(View.GONE);
        emailInput.setVisibility(View.GONE);


        //Create a string for the text view including the name, address, phone, email, pizza size, and list of toppings
        receiptInfo = (TextView) findViewById(R.id.txtOrderInfo);
        receiptInfo.setText(mOrder.setTextInfo(mOrder.getName(), mOrder.getAddress(), mOrder.getPhone(), mOrder.getEmail(), mOrder.getPizzaSize(), toppings.toString()));
//            String info = mOrder.getName() + "\n" + mOrder.getAddress() + "\n" + mOrder.getPhone() + "\n" + mOrder.getEmail() + "\n" + mOrder.getPizzaSize() +"\n" + toppings.toString();   //Creates the string
//            receiptInfo.setText(info);      //Sets the string to the textView


        //Calculates the total price and display in textView under the customer info
        receiptTotal = (TextView) findViewById(R.id.txtOrderTotal);
        total = mOrder.calculateTotal(mOrder.getSizePrice(), mOrder.getToppingPrice());     //Made a "total" variable to store the double. getSizePrice and getToppingPrice are set as arguments for the calculate method
        mOrder.setTotalPrice(total);                        //sets total to the total price
        receiptTotal.setText(getString(R.string.total) + mOrder.getTotalPrice());     //creates string to display the total price
    }
}