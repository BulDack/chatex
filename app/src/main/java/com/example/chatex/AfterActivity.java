package com.example.chatex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class AfterActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth ;
    Button btnRevoke,btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);

        btnLogout=findViewById(R.id.btn_logout);
        btnRevoke=findViewById(R.id.btn_revoke);

        mAuth=FirebaseAuth.getInstance();

        btnLogout.setOnClickListener((View.OnClickListener) this);
        btnRevoke.setOnClickListener((View.OnClickListener) this);
    }
    private void signOut() {
        FirebaseAuth.getInstance().signOut();
    }
    private void revokeAccess() {
        mAuth.getCurrentUser().delete();
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_logout:
                signOut();
                finish();
                break;
            case R.id.btn_revoke:
                 revokeAccess();
                 finish();
                 break;
        }

    }
}
