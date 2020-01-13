package com.example.zab.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.zab.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
            Button b1;
            EditText e1,e2;
            ProgressBar prBar;
         ConnectionClass connectionClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         connectionClass=new ConnectionClass();
        b1=(Button) findViewById(R.id.lg);
        e1=(EditText) findViewById(R.id.us);
        e2=(EditText) findViewById(R.id.ps);
        prBar=(ProgressBar) findViewById(R.id.pb);
        prBar.setVisibility(View.GONE);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DoLogin doLogin =new DoLogin();
                doLogin.execute();
                e1.setText("");   e2.setText("");
                e1.requestFocus();
              
            }
        });
    }
    public class DoLogin extends AsyncTask<String,String,String> {
        String z=null;
        Boolean isSuccess=false;
        String username=e1.getText().toString();
        String passw=e2.getText().toString();

        @Override
        protected void onPreExecute(){prBar.setVisibility(View.VISIBLE);}
        @Override
        protected void onPostExecute(String r ) {
            prBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this,r,Toast.LENGTH_SHORT).show();
            if(isSuccess){
                Toast.makeText(MainActivity.this,r,Toast.LENGTH_SHORT).show();

            }
        }
        @Override
        protected String doInBackground(String... strings) {
            if(username.trim().equals("") || passw.trim().equals("")){
                z="Please Give User ID and Password.";

            }
                else
            {
                try {
                    Connection con=  connectionClass.Conn();
                    if (con==null){
                        z="SQL Server connection failed.";

                    }
                    else   {
                        String sql="select * from xusers where zemail = '"+username+ "' and xpassword = '"+passw+"' ";
                        Statement stmt=con.createStatement();
                        ResultSet rs=stmt.executeQuery(sql);
                    if (rs.next()){



                        z="Log In Successfully.";
                     //  i= new Intent(getApplicationContext(), LoginActivity.class);
                       // startActivity(i);
                        isSuccess=true;
                        con.close();
                    }
                    else    {
                        z="Invalid Credentials.";
                        isSuccess=false;
                    }
                    }
                }
                catch (Exception t) {
                    isSuccess=false;
                    z="Final Exeption.";

                }
            }
            return z;
        }
    }

}
