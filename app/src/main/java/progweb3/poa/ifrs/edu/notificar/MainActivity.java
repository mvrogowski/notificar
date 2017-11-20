package progweb3.poa.ifrs.edu.notificar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICACAO_SIMPLES = 1;
    private static final int NOTIFICACAO_COMPLETA = 2;
    private static final int NOTIFICACAO_BIG = 3;

    EditText mEdtTexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEdtTexto = (EditText) findViewById(R.id.editText);
    }

    public void criarNotificacaoSimples(View v) {
        NotificationUtils.criarNotificacaoSimples(
                this,
                mEdtTexto.getText().toString(),
                NOTIFICACAO_SIMPLES);
    }
    public void criarNotificacaoCompleta(View v) {
        NotificationUtils.criarNotificacaoCompleta(
                this,
                mEdtTexto.getText().toString(),
                NOTIFICACAO_COMPLETA);
    }
    public void criarNotificacaoBig(View v) {
        NotificationUtils.criarNotificationBig(
                this,
                NOTIFICACAO_BIG);
    }
}
