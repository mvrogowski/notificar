package progweb3.poa.ifrs.edu.notificar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetalheActivity extends AppCompatActivity {
    public static final String EXTRA_TEXTO = "texto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);
        String string = getIntent().getStringExtra(EXTRA_TEXTO);

        TextView txt = (TextView)findViewById(R.id.txtDetalhe);
        txt.setText(string);
    }
}
