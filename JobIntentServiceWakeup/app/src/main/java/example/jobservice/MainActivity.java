package example.jobservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


//https://androidwave.com/working-with-jobintentservice/
public class MainActivity extends AppCompatActivity {
EditText et_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_input =findViewById(R.id.editText);


    }
    public void onStartJobIntentService(View view) {

        Intent intent = new Intent(this, MyJobIntentService.class);
        intent.putExtra("times", Integer.parseInt(et_input.getText().toString()));  //should do error checking here!
        MyJobIntentService.enqueueWork(this, intent);

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}