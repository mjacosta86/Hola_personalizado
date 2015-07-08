package com.acme.heyyou;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.hello);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // código a ejecutar cuando sea pulsado
                EditText text = (EditText)findViewById(R.id.entry);
                //comprobar si existe nombre
                if("".equals(text.getText().toString().trim()))
                {
                    //mostrar dialogo
                    showAlert();
                    //mostrar toast
                    showToast();
                    return;
                }
                String salutation = null;
                String enteredName = text.getText().toString();
                // referencia al radioButton
                RadioGroup radio = (RadioGroup)findViewById(R.id.RadioGroup01);
                if (R.id.rdsr == radio.getCheckedRadioButtonId()){
                    //para señor
                    salutation = getResources().getString(R.string.saludoSr).toLowerCase();
                }
                else
                {
                    //para señora
                    salutation = getResources().getString(R.string.saludoSra).toLowerCase();
                }
                salutation = getResources().getString(R.string.hello) + " " + salutation + " " + enteredName;
                // obtención de la hora y fecha
                CheckBox timeCheckBox = (CheckBox)findViewById(R.id.checkBox);
                if (timeCheckBox.isChecked())
                {
                    DatePicker date = (DatePicker) findViewById(R.id.datePicker);
                    String dateToShow = date.getDayOfMonth() + "/" + (date.getMonth() + 1) + "/" + date.getYear();
                    TimePicker time = (TimePicker) findViewById(R.id.timePicker);
                    dateToShow += " " + time.getCurrentHour() + ":" + time.getCurrentMinute();
                    salutation += " " + dateToShow;
                }

                TextView out = (TextView)findViewById(R.id.out);
                out.setText(salutation);
                /*Intent intent = new Intent(MainActivity.this, Salutation.class);
                intent.putExtra("salutation", salutation);
                startActivity(intent);*/

            }
        });
        CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                int visibility = isChecked?View.VISIBLE:View.GONE;
                View view = findViewById(R.id.timePicker);
                view.setVisibility(visibility);
                view = findViewById(R.id.datePicker);
                view.setVisibility(visibility);
            }
        });
    }
    protected void showAlert()
    {
        CharSequence text = getResources().getString(R.string.noNameMsg);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(text);
        alert.setPositiveButton(android.R.string.ok, null);
        alert.show();
    }

    protected void showToast()
    {
        Context context = getApplicationContext();
        CharSequence text = getResources().getString(R.string.noNameMsg);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        /*
        Toast.makeText(getApplicationContext().getResources().getString(R.string.noNameMsg), Toast.LENGTH_SHORT).show();
         */
    }

}
