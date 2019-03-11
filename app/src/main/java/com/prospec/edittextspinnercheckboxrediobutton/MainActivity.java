package com.prospec.edittextspinnercheckboxrediobutton;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.prospec.edittextspinnercheckboxrediobutton.lands.LandBuildingActivity;

public class MainActivity extends AppCompatActivity {

    //INSTANCE FIELDS
    private TextInputEditText txtName;
    private CheckBox chkTechnologyExists;
    private Spinner spPropellant;
    private Button btnAdd;

    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //REFERENCE VIEWS
        this.initializeViews();

//        spinner
        populatePropellants();
        //HANDLE EVENTS
        this.handleClickEvents();

        nextTab();

    }

    private void nextTab() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LandBuildingActivity.class));

            }
        });
    }

    /*
    REFERENCE VIEWS
     */
    private void initializeViews()
    {

        txtName= (TextInputEditText) findViewById(R.id.nameTxt);
        chkTechnologyExists= (CheckBox) findViewById(R.id.techExists);
        spPropellant= (Spinner) findViewById(R.id.sp);
        next= (Button) findViewById(R.id.next);
        btnAdd= (Button) findViewById(R.id.addBtn);


    }

    /*
    HANDLE CLICK EVENTS
     */
    private void handleClickEvents()
    {
        //EVENTS : ADD
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //GET VALUES
                String name=txtName.getText().toString();
                String propellant=spPropellant.getSelectedItem().toString();
                Boolean technologyexists=chkTechnologyExists.isChecked();

                //BASIC CLIENT SIDE VALIDATION
                if((name.length()<1 || propellant.length()<1  ))
                {
                    Toast.makeText(MainActivity.this, "กรุณากรอกทุกช่อง", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //SAVE

                    Spacecraft s=new Spacecraft();
                    s.setName(name);//EditText
                    s.setPropellant(propellant);//Spinner
                    s.setTechnologyExists(technologyexists ? 1 : 0);//Checkbox


                    new MySQLClient(MainActivity.this).add(s,txtName,spPropellant);
                }
            }
        });

    }

    private void populatePropellants()
    {
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item);

        adapter.add("เลือกข้อมูล");
        adapter.add("ข้อมูลที่1");
        adapter.add("ข้อมูลที่2");
        adapter.add("ข้อมูลที่3");
        adapter.add("ข้อมูลที่4");
        adapter.add("ข้อมูลที่5");
        adapter.add("ข้อมูลที่6");

        spPropellant.setAdapter(adapter);
        spPropellant.setSelection(0);

    }

}
