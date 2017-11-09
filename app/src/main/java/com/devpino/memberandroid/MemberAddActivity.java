package com.devpino.memberandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MemberAddActivity extends AppCompatActivity {

    private EditText editTextEmail;

    private EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_add);

        this.editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        this.editTextName = (EditText)findViewById(R.id.editTextName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemSave) {

            String email = editTextEmail.getText().toString();
            String name = editTextName.getText().toString();

            MemberDbAdapter memberDbAdapter = MemberDbAdapter.getInstance();

            memberDbAdapter.open();

            Member member = new Member();
            member.setEmail(email);
            member.setMemberName(name);

            memberDbAdapter.addMember(member);

            memberDbAdapter.close();

            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
