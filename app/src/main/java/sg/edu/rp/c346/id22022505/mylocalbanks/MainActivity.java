package sg.edu.rp.c346.id22022505.mylocalbanks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView TVdbs;
    TextView TVocbc;
    TextView TVuob;
    ToggleButton tbLan;
    TextView title;

    String wordClicked = "";
    boolean isEnglish = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TVdbs = findViewById(R.id.buttonDBS);
        TVocbc = findViewById(R.id.buttonOCBC);
        TVuob = findViewById(R.id.buttonUOB);
        tbLan = findViewById(R.id.toggleButton);
        title = findViewById(R.id.textView);

        registerForContextMenu(TVdbs);
        registerForContextMenu(TVocbc);
        registerForContextMenu(TVuob);

        tbLan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchToChinese();
                } else {
                    switchToEnglish();
                }
            }
        });
    }

    private void switchToEnglish() {
        TVdbs.setText("DBS");
        TVocbc.setText("OCBC");
        TVuob.setText("UOB");
        title.setText("My local bank");
        isEnglish = true;
        Toast.makeText(MainActivity.this, "Language has been switched to English", Toast.LENGTH_SHORT).show();
    }

    private void switchToChinese() {
        TVdbs.setText("星展银行");
        TVocbc.setText("华侨银行");
        TVuob.setText("大华银行");
        title.setText("我的本地银行");
        isEnglish = false;
        Toast.makeText(MainActivity.this, "Language has been switched to Chinese", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 0, 0, "Website");
        menu.add(0, 1, 1, "Contact bank");
        if (v == TVdbs) {
            wordClicked = "dbs";
        } else if (v == TVocbc) {
            wordClicked = "ocbc";
        } else if (v == TVuob) {
            wordClicked = "uob";
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (wordClicked.equalsIgnoreCase("dbs")) {
            if (item.getItemId() == 0) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dbs.com.sg"));
                startActivity(intent);
                return true;
            } else if (item.getItemId() == 1) {
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 1800111111));
                startActivity(intentCall);
                return true;
            }
        } else if (wordClicked.equalsIgnoreCase("ocbc")) {
            if (item.getItemId() == 0) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ocbc.com"));
                startActivity(intent);
                return true;
            } else if (item.getItemId()==1){
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+1800363333));
                startActivity(intentCall);
                return true;
            }
        } else if (wordClicked.equalsIgnoreCase("uob")){
            if(item.getItemId()==0){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("www.uob.com.sg"));
                startActivity(intent);
                return true;
            } else if (item.getItemId()==1){
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+1800222212));
                startActivity(intentCall);
                return true;
            }
        }
        return super.onContextItemSelected(item);
    }

}
