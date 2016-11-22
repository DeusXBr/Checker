package checker.ifrs.edu.checker.view.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.model.bll.QuestaoBll;
import checker.ifrs.edu.checker.view.adapter.QuestaoListAdapter;
import checker.ifrs.edu.checker.vo.Questao;

public class QuestaoActivity extends AppCompatActivity {

    private ListView listQuestao;
    private QuestaoListAdapter questaoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas);

        initToolBar();

        QuestaoBll mQuestaoBll = new QuestaoBll();
        String nomeCategoria;

        if (getIntent().hasExtra(AvaliacaoActivity.KEY_EXTRA)) { //verifica se tem dados passados pela intent
            nomeCategoria = getIntent().getStringExtra(AvaliacaoActivity.KEY_EXTRA); //pega o dado passado

            this.listQuestao = (ListView) findViewById(R.id.listViewPerguntas); // pega layout com o listView
            List<Questao> resultPerguntas = mQuestaoBll.getAllQuestoesByCategoria(nomeCategoria); // pega as perguntas da categoria especificada acima

            this.questaoListAdapter = new QuestaoListAdapter(this, resultPerguntas);
            this.listQuestao.setAdapter(questaoListAdapter); // adiciona o adapter criado acima no listView

        } else {
            throw new IllegalArgumentException("Activity extra não encontrada: " + AvaliacaoActivity.KEY_EXTRA);
        }

    }

    /**
     *  CallBack para finalizar a etapa
     *
     *  Obs: onClick esta no activity_perguntas.xml -> content_questao.xml    */
    public void conluirEtapa(View view){
        //TODO criar nova intent para finalizar etapa.
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
        String title = getResources().getString(R.string.questao_toolbar_title);

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
