package checker.ifrs.edu.checker.view.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.model.bll.AvaliacaoBll;
import checker.ifrs.edu.checker.view.adapter.RelatorioListAdapter;
import checker.ifrs.edu.checker.vo.Avaliacao;

public class ListaRelatorioActivity extends AppCompatActivity {

    private ListView listRelatorios;
    private RelatorioListAdapter relatorioListAdapter;
    public static final String PREFS_NAME = "Preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listarelatorio);

        initToolBar();

        final AvaliacaoBll mAvaliacaoBll = new AvaliacaoBll();

        this.listRelatorios = (ListView) findViewById(R.id.listViewlrelatorios); // pega layout com o listView

        List<Avaliacao> resultAvaliacao = mAvaliacaoBll.getAllAvaliacoes(); // pega as avaliacoes do realm

        this.relatorioListAdapter = new RelatorioListAdapter(this, resultAvaliacao);
        this.listRelatorios.setAdapter(relatorioListAdapter); // adiciona o adapter criado acima no listView

        listRelatorios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // titulo da avaliacao salva no sharedPreferences
                SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                Avaliacao avaliacao = (Avaliacao)relatorioListAdapter.getItem(position);
                editor.putInt("avaliacaoId", avaliacao.getId());
                editor.putString("avaliacaoTitulo", avaliacao.getNome());
                editor.apply();

                Intent intent = new Intent(ListaRelatorioActivity.this, ResultadoActivity.class);
                startActivity(intent);
            }
        });

        listRelatorios.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                Avaliacao avaliacao = (Avaliacao)relatorioListAdapter.getItem(position);
                mAvaliacaoBll.removeAvaliacao(avaliacao);

                relatorioListAdapter.updateList(mAvaliacaoBll.getAllAvaliacoes());

                return true;
            }
        });
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
        String title = getResources().getString(R.string.listarelatorio_toolbar_title);

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
