package com.example.chatex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private Button btn_register2;
    private EditText et_re_id,et_re_pw;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth mAuth = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_re_id=findViewById(R.id.et_re_id);
        et_re_pw=findViewById(R.id.et_re_pw);
        btn_register2=findViewById(R.id.btn_register2);

        firebaseAuth= FirebaseAuth.getInstance();

        btn_register2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String email = et_re_id.getText().toString().trim();
                String pwd = et_re_pw.getText().toString().trim();

                firebaseAuth.createUserWithEmailAndPassword(email,pwd)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if ((task.isSuccessful())) {
                                    FirebaseUser user=mAuth.getCurrentUser();
                                    updateUI(user);

                                }else{
                                    Toast.makeText(RegisterActivity.this,"등록에러",Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                        });
            }
        });


    }
    private void updateUI(FirebaseUser user) { //update ui code here
        if (user != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
