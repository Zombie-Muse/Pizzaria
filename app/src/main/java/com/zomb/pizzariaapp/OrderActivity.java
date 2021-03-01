package com.zomb.pizzariaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String KEY_SIZE = "com.zomb.pizzariaapp.KEY_SIZE";
    public static final String KEY_SIZE_PRICE = "com.zomb.pizzariaapp.KEY_SIZE_PRICE";
    public static final String KEY_TOPPINGS = "com.zomb.pizzariaapp.KEY_TOPPINGS";
    public static final String KEY_TOPPING_PRICE = "com.zomb.pizzariaapp.KEY_TOPPING_PRICE";
    private String pSize, pToppings;
    private Spinner pizzaSize;
    private StringBuilder sb = new StringBuilder();
    private ArrayList<String> toppings = new ArrayList<>();
    private Button btnNext;
    private double pTopPrice, pSizePrice, toppingPrice, price;
    Order mOrder = new Order();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        if (savedInstanceState != null) {
            pSize = savedInstanceState.getString(KEY_SIZE);
            pToppings = savedInstanceState.getString(KEY_TOPPINGS);
            pSizePrice = savedInstanceState.getDouble(KEY_SIZE_PRICE);
            pTopPrice = savedInstanceState.getDouble(KEY_TOPPING_PRICE);
            mOrder.setPizzaSize(pSize);
            mOrder.setToppings(pToppings);
            mOrder.setSizePrice(pSizePrice);
            mOrder.setToppingPrice(pTopPrice);
        }

//        if (savedInstanceState != null) {
//            pSize = savedInstanceState.getString(KEY_SIZE);
//            pSizePrice = savedInstanceState.getDouble(KEY_SIZE_PRICE);
//            pToppings = savedInstanceState.getString(KEY_TOPPINGS);
//            pTopPrice = savedInstanceState.getDouble(KEY_TOPPING_PRICE);
//
//        }
//        mOrder = new Order();
        pizzaSize = (Spinner) findViewById(R.id.spSize);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.size, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pizzaSize.setAdapter(adapter);
        pizzaSize.setOnItemSelectedListener(this);
//
//        btnNext = (Button) findViewById(R.id.btnNext);
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onNextClick();
//            }
//        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(KEY_SIZE, pSize);
        outState.putString(KEY_TOPPINGS, pToppings);
        outState.putDouble(KEY_TOPPING_PRICE, pTopPrice);
        outState.putDouble(KEY_SIZE_PRICE, pSizePrice);
//    public void onNextClick() {
//
//        Intent intent = new Intent(this, CustomerActivity.class);
//        intent.putExtra(KEY_SIZE, pSize);
//        intent.putExtra(KEY_TOPPINGS, pToppings);
//        intent.putExtra(KEY_TOPPING_PRICE, pTopPrice);
//        startActivity(intent);
    }

    public void onNextClick(View view) {
        Intent intent = new Intent(this, CustomerActivity.class);
        intent.putExtra("com.zomb.pizzariaapp.KEY_SIZE", mOrder.getPizzaSize());
        intent.putExtra("com.zomb.pizzariaapp.KEY_SIZE_PRICE", mOrder.getSizePrice());
        intent.putExtra("com.zomb.pizzariaapp.KEY_TOPPINGS", mOrder.getToppings());
        intent.putExtra("com.zomb.pizzariaapp.KEY_TOPPING_PRICE", mOrder.getToppingPrice());
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
    public void onNothingSelected(AdapterView<?> parent) { }

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
                break;
            } catch (Exception e){
            }
        }
        for (String s : toppings) {
            sb.append(s);
            sb.append("\n");
        }
        mOrder.setToppings(sb.toString());
    }
}