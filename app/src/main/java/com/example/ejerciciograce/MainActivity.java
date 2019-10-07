package com.example.ejerciciograce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String PULSADO = "pulsado";
    private static final String TEXTO_INTRODUCIDO = "texto";

    private EditText etTexto;
    private Button btn;
    private TextView TvTexto;

    private boolean pulsado;
    private String textoIntroducido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTexto = (EditText) findViewById(R.id.etTexto);
        btn = (Button) findViewById(R.id.btn);
        TvTexto = (TextView) findViewById(R.id.TvTexto);

        restaurarCampos(savedInstanceState);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = etTexto.getText().toString();
                if (!texto.isEmpty()) {
                    textoIntroducido = etTexto.getText().toString();
                    pulsado = true;
                    mostrarTextView();
                }
            }
        });

        if (pulsado) {
            mostrarTextView();
        }
    }

    public void mostrarTextView(){
        TvTexto.setText(textoIntroducido);
        TvTexto.setVisibility(View.VISIBLE);
    }

    private void restaurarCampos(Bundle savedInstanceState){

        // Si hay algo en el bundle, es que se ha guardado algo y lo recuperaremos
        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean(PULSADO, false)){
                this.pulsado = savedInstanceState.getBoolean(PULSADO);
                this.textoIntroducido =
                        savedInstanceState.getString(TEXTO_INTRODUCIDO, "");
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Metemos en el bundle lo que queremos conservar
        if (pulsado){
            outState.putBoolean(PULSADO, pulsado);
            outState.putString(TEXTO_INTRODUCIDO, textoIntroducido);
        }
    }
}