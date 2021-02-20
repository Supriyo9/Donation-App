package com.example.mycontribution;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InvoiceActivity extends AppCompatActivity {

    private ImageView paymentSucessImg,PaymentfailedImg;
    private TextView fromm,to,paymentid,date,amount,paid,fail;

    private String Ifrom,Ipaymentid,Idate,Iamount,Ipaidfail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);


        paymentSucessImg=findViewById(R.id.successimg);
        PaymentfailedImg=findViewById(R.id.failedimg);
        fromm=findViewById(R.id.fromm);
        to=findViewById(R.id.to);
        paymentid=findViewById(R.id.paymentid);
        date=findViewById(R.id.date);
        paid=findViewById(R.id.paid);
        fail=findViewById(R.id.fail);
        amount=findViewById(R.id.successamount);


        ////////////////////////////////////////////accessing value of previous activity

        Ifrom=getIntent().getStringExtra("FROM");
        Ipaymentid=getIntent().getStringExtra("PAYMENTID");
        Iamount=getIntent().getStringExtra("AMOUNT");
        Idate=getIntent().getStringExtra("DATE");
        Ipaidfail=getIntent().getStringExtra("COMPLETE");

        /////////////////////////////////////////////////////////////////////////////////

        fromm.setText("From : "+Ifrom);
        paymentid.setText("Payment Id : "+Ipaymentid);
        to.setText("To : The Spark");
        date.setText(Idate);
        amount.setText("Rs. "+Iamount+" /-");


        if(Ipaidfail.equals("SUCCESS"))
        {
            paid.setText("PAID");
            paid.setTextColor(Color.parseColor("#00FB1A"));
        }
        else if(Ipaidfail.equals("FAILED"))
        {
            paymentSucessImg.setVisibility(View.INVISIBLE);
            PaymentfailedImg.setVisibility(View.VISIBLE);
            paid.setVisibility(View.GONE);
            fail.setVisibility(View.VISIBLE);
            fail.setText("FAILED");
            fail.setTextColor(Color.parseColor("#FFED1202"));
        }



    }
}