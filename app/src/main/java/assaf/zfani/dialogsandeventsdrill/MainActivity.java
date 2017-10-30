package assaf.zfani.dialogsandeventsdrill;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText color;
    EditText food;
    String colorChosed;
    String foodChosed;
    CharSequence[] foods;
    CharSequence[] colors;
   ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        color= (EditText)findViewById(R.id.ColorEditText);
        food= (EditText)findViewById(R.id.FoodEditText);
        colors= new CharSequence[]{"Black","White","Blue"};
        foods= new CharSequence[]{"pasta","pizza","burger"};

        color.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setSingleChoiceItems(colors, 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                colorChosed= (String)colors[which];
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                        color.setText(colorChosed);
                            }
                        })
                        .show();


                return false;
            }
        });

        food.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setMultiChoiceItems(foods, new boolean[]{false, false, false}, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if(isChecked)
                                foodChosed = (String) foods[which];
                            }
                        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            food.setText(foodChosed);
                    }
                })
                        .show();
                return false;
            }
        });

        Button button = (Button)findViewById(R.id.button);
        progressDialog = new ProgressDialog(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //progressDialog.setIndeterminate(false);
                //progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setMessage("Thanks For Participating!");

                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            Thread.sleep(2000);
                            progressDialog.dismiss();


                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}
