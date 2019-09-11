package sanatories.uz.sanatories;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import sanatories.uz.sanatories.entity.Reserve;

public class PayActivity extends AppCompatActivity {
TextView pay,payType;
EditText phone;
Button conf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        pay = findViewById(R.id.pay);
        payType = findViewById(R.id.pay_type);

        phone = findViewById(R.id.pay_phone);

        conf = findViewById(R.id.conf);

        Intent intent = getIntent();

        payType.setText(intent.getStringExtra("payType"));
        pay.setText(intent.getStringExtra("sum"));

        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             verify();
            }
        });
    }

    void verify(){
        if (!phone.getText().toString().equals("")&&phone.getText().length()==12){
            ReserveFragment.instance.reserve.setPayPhone(phone.getText().toString());
            finish();
        }else
            phone.setError("Telefon ramingiz to'liq emas ");
        }

}
