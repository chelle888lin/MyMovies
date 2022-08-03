package sg.edu.rp.c346.id21028514.mymovies;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ModifyMovie extends AppCompatActivity {

    TextView tvID;
    EditText etNewTitle, etNewGenre, etNewYear;
    Button btnUpdate, btnDelete, btnCancel;
    RadioButton rbEdit,starEdit1,starEdit2,starEdit3,starEdit4,starEdit5;
    RadioGroup rgEditContent;
    Movies data;
    Spinner spinGenreEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_movie);

        //initialize the variables with UI here

        tvID = findViewById(R.id.tvID);
        etNewTitle = findViewById(R.id.etMovieTitle);
        etNewGenre = findViewById(R.id.etGenreName);
        etNewYear = findViewById(R.id.etNewYear);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        spinGenreEdit = findViewById(R.id.spinnerGenreEdit);
        //starEdit1 = findViewById(R.id.radioButton1);
        //starEdit2 = findViewById(R.id.radioButton2);
        //starEdit3 = findViewById(R.id.radioButton3);
        //starEdit4 = findViewById(R.id.radioButton4);
        //starEdit5 = findViewById(R.id.radioButton5);



        Intent i = getIntent();
        data = (Movies) i.getSerializableExtra("data");

        tvID.setText(data.getId() +"");
        etNewTitle.setText(data.getTitle());
        etNewGenre.setText(data.getGenre());
        etNewYear.setText(data.getYear()+"");
        Log.d("data.getRating().equals",data.getRating());

        spinGenreEdit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //new
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                if (data.getRating().equals("G")) {
                    int position = 0;
                    spinGenreEdit.setSelection(position);

                } else if (data.getRating().equals("PG")) {
                    int position = 1;
                    spinGenreEdit.setSelection(position);

                } else if (data.getRating().equals("PG13")) {
                    int position = 2;
                    spinGenreEdit.setSelection(position);

                } else if (data.getRating().equals("NC16")) {
                    int position = 3;
                    spinGenreEdit.setSelection(position);

                } else if (data.getRating().equals("M18")) {
                    int position = 4;
                    spinGenreEdit.setSelection(position);

                } else if (data.getRating().equals("R21")) {
                    int position = 5;
                    spinGenreEdit.setSelection(position);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyMovie.this);
                data.setTitle(etNewTitle.getText().toString());
                data.setGenre(etNewGenre.getText().toString());
                data.setYear(Integer.parseInt(etNewYear.getText().toString()));
                String movieRatingE = spinGenreEdit.getSelectedItem().toString();
//                int data3 = rgEditContent.getCheckedRadioButtonId();
//                rbEdit = findViewById(data3);
//                String rating =(rbEdit.getText().toString());
//                Log.d("result",rating+"");
//
//                Log.d("result",data3+"");
//                int star = rgEditContent.getCheckedRadioButtonId();
//                String radio = "";
//
//                if(star == R.id.radioEditButton1) {
//                    radio = "*";
//                } else if(star == R.id.radioEditButton2) {
//                    radio = "**";
//                } else if(star == R.id.radioEditButton3) {
//                    radio = "***";
//                } else if(star == R.id.radioEditButton4) {
//                    radio = "****";
//                } else if(star == R.id.radioEditButton5) {
//                    radio = "*****";
//                } else {
//                    Toast.makeText(EditActivity.this, "Wrong star",
//                            Toast.LENGTH_SHORT).show();
//                }

                data.setRating(movieRatingE);

                dbh.updateMovie(data);
                dbh.close();
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyMovie.this);
                myBuilder.setTitle("Danger");
                String Title = data.getTitle();
                myBuilder.setMessage("Are you sure you want to delete the movie " + Title +"?");

                myBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(ModifyMovie.this);
                        dbh.deleteMovie(data.getId());
                        finish();
                    }
                });
                myBuilder.setNegativeButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyMovie.this);
                myBuilder.setTitle("Danger");
                String Title = data.getTitle();
                myBuilder.setMessage("Are you sure you want to discard changes?");

                myBuilder.setPositiveButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                myBuilder.setNegativeButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });
    }
}
