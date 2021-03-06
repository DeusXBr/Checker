package checker.ifrs.edu.checker.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.utils.RespostaComparator;
import checker.ifrs.edu.checker.view.adapter.FeedBackListAdapter;
import checker.ifrs.edu.checker.vo.Avaliacao;
import checker.ifrs.edu.checker.vo.Resposta;

import static checker.ifrs.edu.checker.utils.Helper.getAvaliacao;
import static checker.ifrs.edu.checker.utils.ModelUtils.getRespostasErradasRegulares;

public class ListaFeedBackActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listafeedback);

        initToolBar();

        Avaliacao avaliacao = getAvaliacao(this);
        List<Resposta> listRespostas = getRespostasErradasRegulares(avaliacao);

        Collections.sort(listRespostas, new RespostaComparator());

        if (listRespostas.size() > 0)
        {
            TextView titulo_avaliacao = (TextView)findViewById(R.id.titulo_avaliacao_feedback);
            titulo_avaliacao.setText("Avaliação: " + avaliacao.getNome());

            TextView data_avalicao = (TextView)findViewById(R.id.data_hora_avaliacao_feedback);
            data_avalicao.setText(avaliacao.getDataModificado());

            FeedBackListAdapter adapter = new FeedBackListAdapter(this, listRespostas);

            ListView listViewFeedback = (ListView) findViewById(R.id.listview_feedback);
            listViewFeedback.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Metodo retornar para activity anterior
     *
     * @param item menuItem do menu
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.id.menu_home)
        {
            Intent intent = new Intent(ListaFeedBackActivity.this, MainActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == android.R.id.home)
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
        String title = getResources().getString(R.string.relatorio_toolbar_title);

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
