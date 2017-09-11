package fengjw.com.alwaysmarqueetextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private AlwaysMarqueeTextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.btn_test);
        //mTextView = (AlwaysMarqueeTextView) findViewById(R.id.text1);
    }
}
