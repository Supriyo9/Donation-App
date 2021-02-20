package com.example.mycontribution;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

public class DetailsActivity extends AppCompatActivity implements PaymentResultListener {


    private Button payy;
    private EditText Emobile, Email, Eamount;
    private String mobile, mail, amount, Samount;

    String b="";
    int a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        Checkout.preload(getApplicationContext());

        payy = findViewById(R.id.payy);
        Email = findViewById(R.id.mail);
        Emobile = findViewById(R.id.mobile);
        Eamount = findViewById(R.id.amount);




        payy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mobile = Emobile.getText().toString();
                mail = Email.getText().toString();

                Samount = Eamount.getText().toString();
                try {
                    a = Integer.parseInt(Samount) * 100;

                    amount = String.valueOf(a);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Failed ", Toast.LENGTH_SHORT).show();
                }

                makepayment();
            }
        });


    }

    private void makepayment() {


        Checkout checkout = new Checkout();

        checkout.setKeyID("rzp_test_nwqFjoDnJAYPPW");
        /**
         * Instantiate Checkout
         */


        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "The Spark");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");

            options.put("amount", amount);//pass amount in currency subunits 100*100
            options.put("prefill.email", mail);
            options.put("prefill.contact", mobile);
            checkout.open(activity, options);
        } catch (Exception e) {
            //Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }

    }


    @Override
    public void onPaymentSuccess(String s) {
        //Toast.makeText(this, "Succesfull " + s, Toast.LENGTH_SHORT).show();

        b="SUCCESS";

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        Intent intent=new Intent(DetailsActivity.this,InvoiceActivity.class);

        intent.putExtra("FROM",mail);
        intent.putExtra("AMOUNT",Samount);
        intent.putExtra("DATE",currentDateTimeString);
        intent.putExtra("PAYMENTID",s);
        intent.putExtra("COMPLETE",b);

        startActivity(intent);
        finish();

    }

    @Override
    public void onPaymentError(int i, String s) {
        //Toast.makeText(this, "Failed " + s, Toast.LENGTH_SHORT).show();
        b="FAILED";

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        Intent intent=new Intent(DetailsActivity.this,InvoiceActivity.class);
        intent.putExtra("FROM",mail);
        intent.putExtra("AMOUNT",Samount);
        intent.putExtra("DATE",currentDateTimeString);
        intent.putExtra("PAYMENTID",s);
        intent.putExtra("COMPLETE",b);

        startActivity(intent);
        finish();

    }
}