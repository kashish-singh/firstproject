package com.dsc.android.bootcamp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etName,etemail , etpassword , etconfirmpassword ;
            private Button btnregister;
            private String name,email,password,confirmpassword;
            public TinyDB db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnregister.setOnClickListener(this);
        initView();
    }
    private void initView()
    {
        etName= findViewById(R.id.etname);
        etemail= findViewById(R.id.etemail);
        etpassword= findViewById(R.id.etpassword);
        etconfirmpassword= findViewById(R.id.confirmpassword);
        btnregister= findViewById(R.id.btnregister);

    }

    private void getinfo()
    {
        name = etName.getText().toString().trim();
        email= etemail.getText().toString().trim();
        password = etpassword.getText().toString();
        confirmpassword= etconfirmpassword.getText().toString();

    }


    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.btnregister:
                getinfo();
                register();
                break;
        }
    }

    private void register()
    {
        if(name.isEmpty()||email.isEmpty()||password.isEmpty()||confirmpassword.isEmpty())
            Toast.makeText(this,"one or more field is empty",Toast.LENGTH_LONG ).show();
        else
        {
            if(password.equals(confirmpassword))
            {
                Toast.makeText(this,"USER REGISTERED",Toast.LENGTH_LONG).show();
                db.putString("name",name);
                db.putString("email",email);
                db.putString("password",password);

                Toast.makeText(this,"user registered",Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(this,"password did not match.",Toast.LENGTH_LONG).show();
            }
        }
    }

}
