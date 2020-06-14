package melikelif.sos_demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.melikeey.sos.SoS;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCheckInternet  = findViewById(R.id.btn_check_internet);
        btnCheckInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SoS.isNetworkConnected(getApplicationContext())){
                    SoS.showToast(getApplicationContext(), "You connected !");
                }else {
                    SoS.logError("Error Connection");
                }
            }
        });

        Button btnShowDialog = findViewById(R.id.btn_show_dialog);
        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoS.showTextDialog(MainActivity.this, "This is dialog");
            }
        });

        Button btnSendEmail = findViewById(R.id.btn_send_push);
        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SoS.showNotification(getApplicationContext(), "title", "text", MainActivity.class);

            //  startActivity(SoS.getEmailIntent(new String[]{"test@gmail.com"}, "My Email ", "This is my email", "tomail@gmail.com", "Send email"));
            }
        });

        final Button btn_camel  = findViewById(R.id.btn_camel);
        btn_camel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               btn_camel.setText(SoS.fromCamelCaseToSnakeCase(btn_camel.getText().toString()));
            }
        });

        final Button btnCutMe = findViewById(R.id.btn_cut_me);
        btnCutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCutMe.setText(SoS.splitString(btnCutMe.getText().toString(), "-")[0]

                        + "    " + SoS.splitString(btnCutMe.getText().toString(), "-")[1] );

            }
        });

    }

}
