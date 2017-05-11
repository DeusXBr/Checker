package checker.ifrs.edu.checker.view.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.vo.Avaliacao;

import static checker.ifrs.edu.checker.utils.CalculaAvaliacaoUtils.STATUS_NEGATIVO;
import static checker.ifrs.edu.checker.utils.CalculaAvaliacaoUtils.STATUS_POSITIVO;
import static checker.ifrs.edu.checker.utils.CalculaAvaliacaoUtils.STATUS_REGULAR;
import static checker.ifrs.edu.checker.utils.Helper.getAvaliacao;

public class ResultadoActivity extends AppCompatActivity
{

    private ImageView vejaRelatorio;
    private Avaliacao avaliacao;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        initToolBar();

        // pega a atual avaliacao no sharedPreferences
        avaliacao = getAvaliacao(this);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        switch ( avaliacao.getEstado() )
        {
            case STATUS_POSITIVO: imageView.setImageResource(R.drawable.bom);
                                  break;

            case STATUS_REGULAR: imageView.setImageResource(R.drawable.regular);
                                 break;

            case STATUS_NEGATIVO: imageView.setImageResource(R.drawable.ruim);
                                  break;
        }
    }

    /**
     * Callback
     * @param view
     */
    public void verRelatorio(View view)
    {
        Intent intent = new Intent(ResultadoActivity.this, RelatorioActivity.class);
        startActivity(intent);
    }

    /**
     * Callback
     * @param view
     */
    public void editarAvaliacao(View view)
    {
        Intent intent = new Intent(ResultadoActivity.this, AvaliacaoActivity.class);
        startActivity(intent);
    }

    /**
     * Callback
     * @param view
     */
    public void avaliarNovamente(View view)
    {
        Log.i("MeuTeste", "Click no avaliar");
    }

    /**
     * Callback
     * @param view
     */
    public void compartilhar(View view)
    {
        String texto = "Avaliei a acessibilidade física de um lugar chamado " + avaliacao.getNome() +
                       " e o resultado foi "+avaliacao.getEstado() +
                       "! Use o Checker você também =D";

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, texto);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent,"Share via"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Metodo retornar para tela inicial
     *
     * @param item menuItem do menu
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.id.menu_home)
        {
            Intent intent = new Intent(ResultadoActivity.this, MainActivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == android.R.id.home)
        {
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
    public void initToolBar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // recupera o titulo do app no xml strings
        String title = getResources().getString(R.string.resultado_toolbar_title);

        // coloca o title, recuperado da linha acima, como titulo do toolbar
        toolbar.setTitle(title);

        setSupportActionBar(toolbar);

        // mostra o botao voltar no toolbar
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null)
        {
            actionbar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
