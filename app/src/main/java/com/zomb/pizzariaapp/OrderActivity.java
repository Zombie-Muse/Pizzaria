package com.zomb.pizzariaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner pizzaSize;
    private StringBuilder sb = new StringBuilder();
    private ArrayList<String> toppings = new ArrayList<>();
    private Button btnNext;
    private double toppingPrice, price;
    Order mOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        mOrder = new Order();

        pizzaSize = (Spinner) findViewById(R.id.spSize);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.size, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pizzaSize.setAdapter(adapter);
        pizzaSize.setOnItemSelectedListener(this);


        for (String s : toppings) {
            sb.append(s);
            sb.append("\n");
        }
        mOrder.setToppings(sb.toString());

        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextClick();
            }
        });
    }

    public void onNextClick() {
        Intent intent = new Intent(this, CustomerActivity.class);
        startActivity(intent);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String size = parent.getItemAtPosition(position).toString();
        mOrder.setPizzaSize(size);
        if (size.equals("Small - $8")) {
            mOrder.setPizzaSize("Small Pizza");
            mOrder.setSizePrice(8.0);
        } else if (size.equals("Medium - $10")) {
            mOrder.setPizzaSize("Medium Pizza");
            mOrder.setSizePrice(10.0);
        } else if (size.equals("Large - $12")) {
            mOrder.setPizzaSize("Large Pizza");
            mOrder.setSizePrice(12.0);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onCheckboxClick(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        while (checked) {
            try {
                switch (view.getId()) {
                    case R.id.chkPepperoni:
                        if (checked)
                            toppingPrice = 1;
                        price += toppingPrice;
                        toppings.add("Pepperoni");
                        break;
                    case R.id.chkChicken:
                        if (checked)
                            toppingPrice = 1.25;
                        price += toppingPrice;
                        toppings.add("Chicken");
                        break;
                    case R.id.chkMushrooms:
                        if (checked)
                            toppingPrice = 0.75;
                        price += toppingPrice;
                        toppings.add("Mushrooms");
                        break;
                    case R.id.chkGreenPepper:
                        if (checked)
                            toppingPrice = 0.75;
                        price += toppingPrice;
                        toppings.add("Green Pepper");
                        break;
                    case R.id.chkOlive:
                        if (checked)
                            toppingPrice = 0.75;
                        price += toppingPrice;
                        toppings.add("Olives");
                        break;
                    case R.id.chkXtraCheese:
                        if (checked)
                            toppingPrice = 1.50;
                        price += toppingPrice;
                        toppings.add("Extra Cheese");
                        break;
                }
                mOrder.setToppingPrice(price);
//            StringBuilder sb = new StringBuilder();
//            for (String s : toppings) {
//                sb.append(s);
//                sb.append("\n");
//            }
//            mOrder.setToppings(sb.toString());
                break;
            } catch (Exception e){
            }
        }}
}