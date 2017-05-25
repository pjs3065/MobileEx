package org.androidtown.lab6;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    //FilePath
    public static final String filePath = "notes.txt";
    //EditBox
    private EditText txtUIData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get Contents by ID
        txtUIData = (EditText) findViewById(R.id.txtData);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);

        //Make Directory
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/MyFiles");
        directory.mkdir();

        //Make File
        final File file = new File(directory , filePath);

        //Write File Button Event
        button1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    OutputStreamWriter osw = new OutputStreamWriter(out);

                    osw.write(txtUIData.getText().toString());
                    osw.close();
                } catch (Throwable t) {
                    Toast.makeText(getApplicationContext(), "Exception: " + t.toString(), Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(getApplicationContext(), "Done Writing SD '" + filePath +" '", Toast.LENGTH_SHORT).show();
            }
        });

        //Clear EditBox Button Event
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtUIData.setText("");
            }
        });

        //Read File Button Event
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String read = new String();

                try {
                    FileInputStream in = new FileInputStream(file);
                    InputStreamReader isr = new InputStreamReader(in);

                    //Get Characters
                    int res;
                    while((res = isr.read()) != -1) {
                        read += (char)res;
                    }
                    isr.close();

                    //Print into EditBox
                    txtUIData.setText(read);

                }
                catch (Throwable t) {
                    Toast.makeText(getApplicationContext(), "Exception: " + t.toString(), Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(), "Done Reading SD '" + filePath +" '", Toast.LENGTH_SHORT).show();
            }
        });

        //Finish Application Button Event
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
    }
}

