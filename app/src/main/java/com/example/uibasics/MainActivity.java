package com.example.uibasics;

import android.graphics.Color;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.XmlRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Declared up here so it can be used in all methods.
    private RelativeLayout parent;
    private TextView txtHello;
    private EditText edtTxtName;
    private CheckBox checkBoxHarry, checkboxMatrix, checkboxJoker;
    private RadioGroup rgmaritalStatus;
    private ProgressBar progressBar; // Instantiate the progress bar
    private ListView citiesList;
    private Spinner studentsSpinner;
    private FloatingActionButton fab;
    private Button btnShowSnackBar;
    private MaterialCardView cardView;


    @Override
    public void onClick(View v) {       // can be called with any view
        switch (v.getId()) {
            case R.id.btnHello:
                Toast.makeText(MainActivity.this, "Hello button clicked", Toast.LENGTH_SHORT).show();
                txtHello.setText("Hello " + edtTxtName.getText().toString());       // presenting the name back to the user
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //getActionBar().show();

        // The onCreate method is the starting point of the application
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button...
        Button btnHello = findViewById(R.id.btnHello);
        btnHello.setOnClickListener(this);

        btnHello.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(MainActivity.this, "Long press", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        // already declared, time to initialize
        edtTxtName =  findViewById(R.id.editTextName);
        txtHello = findViewById(R.id.txtHello);
        txtHello.setText(getString(R.string.hello));     // retrieves the value of the string from the strings.xml file. This way also makes sure its translated for other languages (german)

        // Check Box...
        checkBoxHarry = findViewById(R.id.checkboxHarry);
        checkboxJoker = findViewById(R.id.checkboxJoker);
        checkboxMatrix = findViewById(R.id.checkboxMatrix);

        checkBoxHarry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {     // listener for checkbox
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "You have watched harry potter", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "You need to watch harry potter", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Progress Bar...
        progressBar = findViewById(R.id.progressBar);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i< 10; i++){
                    progressBar.incrementProgressBy(10);        // Every loop increments progress bar by 10
                    SystemClock.sleep(500);                     // Same as thread.sleep() but no need for a try/catch block
                    // progressBar.getProgress();
                }
            }
        });
        thread.start();

        // Radio Group...
        rgmaritalStatus = findViewById(R.id.rgmaritalstatus);

        // Shows the married toast without clicking on the buttons.
        int checkedButton = rgmaritalStatus.getCheckedRadioButtonId();           // returns the id of the checked radio button
        switch (checkedButton) {
            case R.id.rbMarried:
                Toast.makeText(MainActivity.this, "Married", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rbSingle:
                Toast.makeText(MainActivity.this, "Single", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        // Shows the toasts on button clicks.
        rgmaritalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rbMarried:
                        Toast.makeText(MainActivity.this, "Married", Toast.LENGTH_SHORT).show();
                        //progressBar.setVisibility(View.GONE);        // Changing the visibility of the progress bar whenever the user clicks on married
                        break;
                    case R.id.rbSingle:
                        Toast.makeText(MainActivity.this, "Single", Toast.LENGTH_SHORT).show();
                        //progressBar.setVisibility(View.VISIBLE);        // Changing the visibility of the progress bar whenever the user clicks on single
                        break;
                    default:
                        break;
                }
            }
        });

        // List View...
        // Demonstration of how to use the 'outdated' List View feature in android...
        // There are better alternatives though, e.g. RecyclerView
        citiesList = findViewById(R.id.citiesList);
        // Lets create the data we want to show in our list view
        ArrayList<String> cities = new ArrayList<>();       // I will store the data in this ArrayList
        cities.add("London");
        cities.add("New York");
        cities.add("Chicago");
        cities.add("Madrid");
        cities.add("Moscow");

        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<>(        // The purpose of this adapter is to fetch the data to the ListView
                this,                                       // passing the context
                android.R.layout.simple_list_item_1,        // passing a built-in Layout
                cities                                      // passing the data
        );

        citiesList.setAdapter(citiesAdapter);               // passing the adapter to the ListView

        // Lets now set a listener for each city
        citiesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {       // must be OnItemClickListener, not OnClickListener
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(MainActivity.this, cities.get(position) + " Selected", Toast.LENGTH_SHORT).show();         // cities.get(position) gets the city name which has been clicked
            }
        });


        // Spinner...
        // Useful when the data is dynamic
        studentsSpinner = findViewById(R.id.studentsSpinner);
        // Lets create the data we want to show in our Spinner
        ArrayList<String> students = new ArrayList<>();
        students.add("Martin");
        students.add("Cem");
        students.add("Zaid");
        students.add("Petar");
        students.add("Samuel");

        ArrayAdapter<String> studentsAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                students
        );

        studentsSpinner.setAdapter(studentsAdapter);
        // Can add an OnItemSelectedListener to perform an action on name click


        // Floating Action Button...
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Fab clicked", Toast.LENGTH_SHORT).show();
            }
        });


        // SnackBar...
        parent = findViewById(R.id.parent);
        btnShowSnackBar = findViewById(R.id.btnShowSnackBar);
        btnShowSnackBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSnackBar();     // calling the method
            }
        });


        // CardView...
        cardView = findViewById(R.id.cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Card Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {     // Class to create menus
        MenuInflater inflater = getMenuInflater();      // This will inflate (create) our menu
        inflater.inflate(R.menu.menu_main, menu);       // address desired menu, and menu which is passed to this method
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {      // Creating listener for the menu items
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.alarmMenu:
                Toast.makeText(this, "Alarm Selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void showSnackBar() {
        // SnackBar.make() method needs 3 inputs. (parent layout, content, time)
        Snackbar.make(parent, "This is a snack bar", Snackbar.LENGTH_INDEFINITE)   // LENGTH_INDEFINITE means the SnackBar will remain shown until the action is used
                .setAction("Retry", new View.OnClickListener() {                   // To set an action we need the actions text and an OnClickListener
                    @Override
                    public void onClick(View view) {    // What happens when the user clicks on 'Retry'
                        Toast.makeText(MainActivity.this, "Retry Clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                // Changing the color of the text and the action text
                .setActionTextColor(Color.RED)      // Getting color from the Color Class.
                .setTextColor(Color.YELLOW)
                .show();
    }


}