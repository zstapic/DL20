package hr.foi.air.discountlocator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.database.MyDatabase;

import static hr.foi.air.database.MyDatabase.getInstance;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txt_hello)
    TextView txtHello;

    public static MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = getInstance(this);

        ButterKnife.bind(this);

        txtHello.setText(R.string.message_hello);
    }
}