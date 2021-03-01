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
    public static final String KEY_NAME = "com.zomb.pizzariaapp.KEY_NAME";
    public static final String KEY_PHONE = "com.zomb.pizzariaapp.KEY_PHONE";
    public static final String KEY_ADDRESS = "com.zomb.pizzariaapp.KEY_ADDRESS";
    public static final String KEY_EMAIL = "com.zomb.pizzariaapp.KEY_EMAIL";
    private EditText nameInput, phoneInput, addressInput, emailInput;
    private TextView receiptInfo, receiptTotal;
    String name, phone, address, email, pSize, pToppings;
    double total, pTopPrice, pSizePrice;
    ArrayList<String> toppings;
    Order mOrder;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        Intent intent = getIntent();
        mOrder = new Order();

        pSize = intent.getStringExtra("pSize");
        mOrder.setPizzaSize(pSize);
        pSizePrice = intent.getDoubleExtra("pSizePrice", 0);
        mOrder.setSizePrice(pSizePrice);
        pToppings = intent.getStringExtra("pToppings");
        mOrder.setToppings(pToppings);
        pTopPrice = intent.getDoubleExtra("pTopPrice", 0);
        mOrder.setToppingPrice(pTopPrice);
        name = intent.getStringExtra("com.zomb.pizzariaapp.KEY_NAME");
        phone = intent.getStringExtra("com.zomb.pizzariaapp.KEY_PHONE");
        address = intent.getStringExtra("com.zomb.pizzariaapp.KEY_ADDRESS");
        email = intent.getStringExtra("com.zomb.pizzariaapp.KEY_EMAIL");
//        pSize = mOrder.getPizzaSize();
//        pSizePrice = mOrder.getSizePrice();
//        pToppings = mOrder.getToppings();
//        pTopPrice = mOrder.getToppingPrice();

        receiptInfo = findViewById(R.id.txtOrderInfo);
        receiptTotal = findViewById(R.id.txtOrderTotal);

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
        btnSubmit = findViewById(R.id.btnSubmit);

//        btnSubmit = (Button) findViewById(R.id.btnSubmit);
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                name = nameInput.getText().toString();
//                address = addressInput.getText().toString();
//                phone = phoneInput.getText().toString();
//                email = emailInput.getText().toString();
//
//                mOrder.setName(name);
//                mOrder.setAddress(address);
//                mOrder.setPhone(phone);
//                mOrder.setEmail(email);
//
//                submitOrder();
//            }
//        });
//        if (savedInstanceState != null) {
//            pSize = savedInstanceState.getString(OrderActivity.KEY_SIZE);
//            pToppings = savedInstanceState.getString(OrderActivity.KEY_TOPPINGS);
//            pTopPrice = savedInstanceState.getDouble(OrderActivity.KEY_TOPPING_PRICE);
//            pSizePrice = savedInstanceState.getDouble(OrderActivity.KEY_SIZE_PRICE);
//            name = savedInstanceState.getString(KEY_NAME);
//            phone = savedInstanceState.getString(KEY_PHONE);
//            address = savedInstanceState.getString(KEY_ADDRESS);
//            email = savedInstanceState.getString(KEY_EMAIL);

//        } else {
//            mOrder = new Order();
//            setContentView(R.layout.activity_customer);
//            nameInput = (EditText) findViewById(R.id.etxtName);
//            phoneInput = (EditText) findViewById(R.id.etxtPhone);
//            addressInput = (EditText) findViewById(R.id.etxtAddress);
//            emailInput = (EditText) findViewById(R.id.etxtEmail);
//        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

//        outState.putString(OrderActivity.KEY_SIZE, pSize);
//        outState.putDouble(OrderActivity.KEY_SIZE_PRICE, pSizePrice);
//        outState.putString(OrderActivity.KEY_TOPPINGS, pToppings);
//        outState.putDouble(OrderActivity.KEY_TOPPING_PRICE, pTopPrice);

        outState.putString(KEY_NAME, String.valueOf(nameInput.getText()));
        outState.putString(KEY_PHONE, String.valueOf(phoneInput.getText()));
        outState.putString(KEY_ADDRESS, String.valueOf(addressInput.getText()));
        outState.putString(KEY_EMAIL, String.valueOf(emailInput.getText()));
    }

    public void submitOrder(View view) {
        //set visibility on customer form to GONE
//        Intent intent = getIntent();
//        pSize = intent.getStringExtra(OrderActivity.KEY_SIZE);
//        pToppings = intent.getStringExtra(OrderActivity.KEY_TOPPINGS);
//        pTopPrice = intent.getDoubleExtra(OrderActivity.KEY_TOPPING_PRICE, 0);
//        pSizePrice = intent.getDoubleExtra(OrderActivity.KEY_SIZE_PRICE, 0);
//        name = intent.getStringExtra(KEY_NAME);
//        phone = intent.getStringExtra(KEY_PHONE);
//        address = intent.getStringExtra(KEY_ADDRESS);
//        email = intent.getStringExtra(KEY_EMAIL);
        view.setVisibility(View.VISIBLE);
        btnSubmit.setVisibility(View.GONE);
        nameInput.setVisibility(View.GONE);
        phoneInput.setVisibility(View.GONE);
        addressInput.setVisibility(View.GONE);
        emailInput.setVisibility(View.GONE);
        receiptInfo.setVisibility(View.VISIBLE);
        receiptTotal.setVisibility(View.VISIBLE);


            //Create a string for the text view including the name, address, phone, email, pizza size, and list of toppings
//            receiptInfo = (TextView) findViewById(R.id.txtOrderInfo);
            receiptInfo.setText(mOrder.getName());
//            Order.setTextInfo(mOrder.getName(), mOrder.getAddress(), mOrder.getPhone(), mOrder.getEmail(), mOrder.getPizzaSize(), toppings.toString()));
//            String info = mOrder.getName() + "\n" + mOrder.getAddress() + "\n" + mOrder.getPhone() + "\n" + mOrder.getEmail() + "\n" + mOrder.getPizzaSize() +"\n" + toppings.toString();   //Creates the string
//            receiptInfo.setText(info);      //Sets the string to the textView


        //Calculates the total price and display in textView under the customer info
//        receiptTotal = (TextView) findViewById(R.id.txtOrderTotal);
        total = mOrder.calculateTotal(mOrder.getSizePrice(), mOrder.getToppingPrice());     //Made a "total" variable to store the double. getSizePrice and getToppingPrice are set as arguments for the calculate method
        mOrder.setTotalPrice(total);                        //sets total to the total price
        receiptTotal.setText(getString(R.string.total) + mOrder.getTotalPrice());     //creates string to display the total price
    }
}