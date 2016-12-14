package checker.ifrs.edu.checker.view.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.HashMap;
import java.util.List;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.model.bll.AvaliacaoBll;
import checker.ifrs.edu.checker.model.bll.CategoriaBll;
import checker.ifrs.edu.checker.model.bll.QuestaoBll;
import checker.ifrs.edu.checker.model.bll.RespostaBll;
import checker.ifrs.edu.checker.view.adapter.QuestaoListAdapter;
import checker.ifrs.edu.checker.vo.Avaliacao;
import checker.ifrs.edu.checker.vo.Categoria;
import checker.ifrs.edu.checker.vo.Questao;
import checker.ifrs.edu.checker.vo.RealmInt;
import checker.ifrs.edu.checker.vo.Resposta;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class QuestaoActivity extends AppCompatActivity {

    private ListView listQuestao;
    private QuestaoListAdapter questaoListAdapter;
    private String nomeCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas);

        initToolBar();

        QuestaoBll mQuestaoBll = new QuestaoBll();

        if (getIntent().hasExtra(AvaliacaoActivity.KEY_EXTRA)) { //verifica se tem dados passados pela intent
            this.nomeCategoria = getIntent().getStringExtra(AvaliacaoActivity.KEY_EXTRA); //pega o dado passado

            this.listQuestao = (ListView) findViewById(R.id.listViewPerguntas); // pega layout com o listView
            //List<Questao> resultPerguntas = mQuestaoBll.getAllQuestoesByCategoria(nomeCategoria); // pega as perguntas da categoria especificada acima

            this.questaoListAdapter = new QuestaoListAdapter(this, mQuestaoBll.getAllQuestoesByCategoria(nomeCategoria));
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
        SharedPreferences sharedPrefs = this.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);
        final String avaliacaoTitulo = sharedPrefs.getString("avaliacaoTitulo", null);

        AvaliacaoBll avaliacaoBll = new AvaliacaoBll();
        Avaliacao avaliacao = avaliacaoBll.getAvaliacao(avaliacaoTitulo);

        CategoriaBll categoriaBll = new CategoriaBll();
        Categoria categoria = categoriaBll.getCategoria(nomeCategoria);

        int categoriaSize = categoriaBll.getQuestoes(categoria).size();

        HashMap<Integer, Integer> myMap = questaoListAdapter.getRespostas();

        int questoesRespondidas = myMap.size();

        if(questoesRespondidas > 0 && avaliacao.getEstado().equals("Novo"))
        {
            avaliacao.setEstadoDirect("Iniciado");
        }

        if(categoriaSize == questoesRespondidas)
        {
            Log.i("MeuTeste", "conluirEtapa: Parabens voce respondeu tudo");
        }
        else
        {
            Log.i("MeuTeste", "conluirEtapa2: Voce não respodeu tudo faltou: " + (categoriaSize - questoesRespondidas));
        }

        QuestaoBll questaoBll = new QuestaoBll();
        RespostaBll respostaBll = new RespostaBll();
        RealmResults result = null;

        for (int key : myMap.keySet()) {
            Resposta resposta = new Resposta();
            resposta.setQuestao(questaoBll.getQuestao(key));
            resposta.setResposta(myMap.get(key));

            if(!avaliacaoBll.temResposta(avaliacao.getId(),resposta))
            {
                respostaBll.addResposta(resposta);
                avaliacao.setResposta(resposta);
            }
            else{
                avaliacaoBll.editRespostaDeUmaAvaliacao(avaliacao.getId(), resposta);
            }
        }

        Intent intent = new Intent(QuestaoActivity.this, AvaliacaoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

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
