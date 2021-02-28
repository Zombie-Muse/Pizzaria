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
    public static final String EXTRA_SIZE = "com.zomb.pizzariaapp.EXTRA_SIZE";
    public static final String EXTRA_TOPPINGS = "com.zomb.pizzariaapp.EXTRA_TOPPINGS";
    private String pSize, pToppings;
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
        if (savedInstanceState != null) {
            pSize = savedInstanceState.getString(EXTRA_SIZE);
            pToppings = savedInstanceState.getString(EXTRA_TOPPINGS);

        }
        mOrder = new Order();
        pizzaSize = (Spinner) findViewById(R.id.spSize);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.size, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pizzaSize.setAdapter(adapter);
        pizzaSize.setOnItemSelectedListener(this);

        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextClick();
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString(EXTRA_SIZE, pSize);
        outState.putString(EXTRA_TOPPINGS, pToppings);
    }

    public void onNextClick() {

        Intent intent = new Intent(this, CustomerActivity.class);
        intent.putExtra(EXTRA_SIZE, pSize);
        intent.putExtra(EXTRA_TOPPINGS, pToppings);
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