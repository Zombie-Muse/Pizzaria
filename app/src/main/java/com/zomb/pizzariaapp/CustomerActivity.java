package com.zomb.pizzariaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomerActivity extends AppCompatActivity {
    private EditText nameInput, phoneInput, addressInput, emailInput;
    private TextView receiptInfo, receiptTotal;
    private String name, phone, address, email;
    private double total;
    private ArrayList<String> toppings;
    private Order mOrder;
    private Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);


        Intent intent = getIntent();
        String pSize = intent.getStringExtra(OrderActivity.EXTRA_SIZE);
        String pToppings = intent.getStringExtra(OrderActivity.EXTRA_TOPPINGS);

        mOrder.setPizzaSize(pSize);
        mOrder.setToppings(pToppings);

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

    public void submitOrder() {
        //set visibility on customer form to GONE
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