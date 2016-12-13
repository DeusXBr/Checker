package checker.ifrs.edu.checker.view.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.model.bll.AvaliacaoBll;
import checker.ifrs.edu.checker.vo.Avaliacao;

public class ResultadoActivity extends AppCompatActivity {

    private ImageView vejaRelatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        initToolBar();

        SharedPreferences sharedPrefs = this.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);
        final String avaliacaoTitulo = sharedPrefs.getString("avaliacaoTitulo", null);

        AvaliacaoBll avaliacaoBll = new AvaliacaoBll();
        Avaliacao avaliacao = avaliacaoBll.getAvaliacao(avaliacaoTitulo);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        switch (avaliacao.getEstado())
        {
            case "Bom": imageView.setImageResource(R.drawable.bom);
                        break;

            case "Regular": imageView.setImageResource(R.drawable.regular);
                            break;

            case "Ruim": imageView.setImageResource(R.drawable.ruim);
                        break;
        }
    }

    /**
     * Callback
     * @param view
     */
    public void verRelatorio(View view) {
        Log.i("MeuTeste", "Click na imagem");
    }

    /**
     * Callback
     * @param view
     */
    public void editarAvaliacao(View view) {
        Log.i("MeuTeste", "Click no editar");
        Intent intent = new Intent(ResultadoActivity.this, AvaliacaoActivity.class);
        startActivity(intent);
    }

    /**
     * Callback
     * @param view
     */
    public void avaliarNovamente(View view) {
        Log.i("MeuTeste", "Click no avaliar");
    }

    /**
     * Callback
     * @param view
     */
    public void compartilhar(View view) {
        Log.i("MeuTeste", "Click no compartilhar");
    }


    /**
     * Metodo retornar para activity anterior
     *
     * @param item menuItem do menu
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Metodo para inicializar o toolbar
     *
     * Obs: chamada esta no construtor da classe
     *
     */
    public void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // recupera o titulo do app no xml strings
        String title = getResources().getString(R.string.resultado_toolbar_title);

        // coloca o title, recuperado da linha acima, como titulo do toolbar
        toolbar.setTitle(title);

        setSupportActionBar(toolbar);

        // mostra o botao voltar no toolbar
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
