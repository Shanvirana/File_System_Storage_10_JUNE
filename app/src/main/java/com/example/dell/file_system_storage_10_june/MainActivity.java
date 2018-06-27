package com.example.dell.file_system_storage_10_june;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    String states;
    int position;
    static public int ex = 0;
    String FILENAME = "myFile.txt";
    EditText nametext;
    EditText emailtext;
    EditText Mobtext;
    EditText citytext;
    EditText statetext;
    final String ALLstates[] = {"Uttrakhand", "Uttar Pradesh", "Himachal Pradesh", "Madhya Pradesh", "Odisha"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

         nametext = findViewById(R.id.edit_name);
          emailtext = findViewById(R.id.email);
          Mobtext = findViewById(R.id.MobNo);
          citytext = findViewById(R.id.City);
        final Spinner state = findViewById(R.id.statename);
        Button submit = findViewById(R.id.submit);
        Button View = findViewById(R.id.View_Data);

        submit.setOnClickListener(this);
        View.setOnClickListener(this);
       // final String ALLstates[] = {"Uttrakhand", "Uttar Pradesh", "Himachal Pradesh", "Madhya Pradesh", "Odisha"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, ALLstates);
        state.setAdapter(arrayAdapter);

        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                states = ALLstates[i];
                position=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "You haven't selected anything ! ", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.submit) {

            // Save Data To File
            try {
                FileOutputStream fileOutputStream = openFileOutput(FILENAME,MODE_PRIVATE);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                outputStreamWriter.write(nametext.getText().toString()+"*");
                outputStreamWriter.write(emailtext.getText().toString()+"*");
                outputStreamWriter.write(Mobtext.getText().toString()+"*");
                outputStreamWriter.write(citytext.getText().toString()+"*");
                outputStreamWriter.write(ALLstates[position]+"*");

                outputStreamWriter.close();
                Toast.makeText(this, "File Saved Successfully", Toast.LENGTH_SHORT).show();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(v.getId() == R.id.View_Data)
        {

            try {
                FileInputStream fileInputStream = openFileInput(FILENAME);

                InputStreamReader inputReader = new InputStreamReader(fileInputStream);

                char [] inputText = new char[100];
                String finalFileText = "";

                int charCount;

                // using inputreader , read from file
                // maximu size kiska : array : 100
                // charCount
                // charCount == 0
                while ((charCount = inputReader.read(inputText))>0){
                    finalFileText = finalFileText + String.copyValueOf(inputText);
                }
                inputReader.close();
                //fetchTV.setText(finalFileText);
                //fetchTV.setVisibility(TextView.VISIBLE);
                String Information[] = new String[5];
                String st="";
                int c = 0;
                for(int i=0;i<finalFileText.length();i++)
                {
                    if(finalFileText.charAt(i) != '*')
                    {
                        st+= finalFileText.charAt(i);

                    }
                    else
                    {
                        Information[c%5] = st;
                        c++;
                        st="";
                    }
                }



                Intent intent = new Intent(MainActivity.this, Disaplay_Activity.class);
                intent.putExtra("name", Information[0]);
                intent.putExtra("mail", Information[1]);
                intent.putExtra("phone", Information[2]);
                intent.putExtra("state", Information[4]);
                intent.putExtra("city", Information[3]);
                startActivity(intent);

                Toast.makeText(this, "File Read , see the TV", Toast.LENGTH_SHORT).show();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }




}
