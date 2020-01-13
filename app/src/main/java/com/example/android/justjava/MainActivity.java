
/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox ChocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = ChocolateCheckBox.isChecked();
        EditText userName = (EditText) findViewById(R.id.edit_text);
        String NameOfTheUser = userName.getText().toString();
        int price = calculatePrice(hasWhippedCream, hasChocolate);


        Intent intent = new Intent(Intent.ACTION_SEND);
        String body = "NAME:" + NameOfTheUser + "\nAdd Whipped cream?" + hasWhippedCream + "\nAdd Chocolate?" + hasChocolate + "\nQuantity:" + quantity + "\n" + "Total:$" + price + "\n" + "Thank you!";
        String subject = "Order Coffee";
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email"));
    }

    private String createOrderSummary(int price, boolean whippedCreamState, boolean chocolateState, String Name) {
        String summary = "NAME:" + Name + "\nAdd Whipped cream?" + whippedCreamState + "\nAdd Chocolate?" + chocolateState + "\nQuantity:" + quantity + "\n" + "Total:$" + price + "\n" + getString(R.string.thank_you);
        return summary;
    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int basePrice = 5;

        if (hasWhippedCream) {
            basePrice += 1;
        }
        if (hasChocolate) {
            basePrice += 2;
        }
        return basePrice * quantity;
    }

    public void increment(View view) {
        //int quantity = 2;
        //quantity = 3;
        if(quantity == 100){
        Toast.makeText(this, "You can't order more than 100 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        //int quantity = 2;
        //quantity = 1;
        if (quantity == 1) {
            Toast.makeText(this, "You can't order less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }

        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }



}

