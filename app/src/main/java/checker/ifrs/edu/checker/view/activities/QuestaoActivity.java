package checker.ifrs.edu.checker.view.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.model.bll.AvaliacaoBll;
import checker.ifrs.edu.checker.model.bll.CategoriaBll;
import checker.ifrs.edu.checker.model.bll.QuestaoBll;
import checker.ifrs.edu.checker.model.bll.RespostaBll;
import checker.ifrs.edu.checker.view.adapter.QuestaoListAdapter;
import checker.ifrs.edu.checker.vo.Avaliacao;
import checker.ifrs.edu.checker.vo.Categoria;
import checker.ifrs.edu.checker.vo.Resposta;

import static checker.ifrs.edu.checker.utils.Helper.getAvaliacao;
import static checker.ifrs.edu.checker.utils.ModelUtils.getRespostas;

public class QuestaoActivity extends AppCompatActivity
{
    private ListView listQuestao;
    private QuestaoListAdapter questaoListAdapter;
    private String nomeCategoria;

    private Avaliacao avaliacao;
    private QuestaoBll mQuestaoBll;
    private AvaliacaoBll mAvaliacaoBll;
    private CategoriaBll mCategoriaBll;
    private RespostaBll mRespostaBll;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas);

        initToolBar();

        // pega a atual avaliacao no sharedPreferences
        avaliacao = getAvaliacao(this);

        // cria os objetos que serão utilizados para acessar o banco
        mQuestaoBll = new QuestaoBll();
        mAvaliacaoBll = new AvaliacaoBll();
        mCategoriaBll = new CategoriaBll();
        mQuestaoBll = new QuestaoBll();
        mRespostaBll = new RespostaBll();

        //verifica se tem dados passados pela intent
        if (getIntent().hasExtra(AvaliacaoActivity.KEY_EXTRA))
        {
            //pega o dado passado
            this.nomeCategoria = getIntent().getStringExtra(AvaliacaoActivity.KEY_EXTRA);

            // pega layout com o listView
            this.listQuestao = (ListView) findViewById(R.id.listViewPerguntas);

            // iniciar o QuestaoListAdapter precisa de todas as questoes e se houver as respostas
            this.questaoListAdapter = new QuestaoListAdapter(this,
                                                             mQuestaoBll.getAllQuestoesByCategoria(nomeCategoria),
                                                             getRespostas(avaliacao, nomeCategoria)
                                                            );

            // adiciona o adapter criado acima no listView
            this.listQuestao.setAdapter(questaoListAdapter);

        }
        else
        {
            throw new IllegalArgumentException("Activity extra não encontrada: " + AvaliacaoActivity.KEY_EXTRA);
        }

    }

    /**
     *  CallBack para finalizar a etapa
     *
     *  Obs: onClick esta no activity_perguntas.xml -> content_questao.xml
     */
    public void conluirEtapa(View view)
    {
        Categoria categoria = mCategoriaBll.getCategoria(nomeCategoria);

        int categoriaSize = mCategoriaBll.getQuestoes(categoria).size();

        SparseIntArray sparseIntArray = questaoListAdapter.getRespostas();
        int questoesRespondidas = sparseIntArray.size();

        if (questoesRespondidas > 0 && avaliacao.getEstado().equals("Novo"))
        {
            this.avaliacao.setEstadoDirect("Iniciado");
        }

        if (categoriaSize == questoesRespondidas)
        {
            Log.i("MeuTeste", "conluirEtapa: Parabens voce respondeu tudo");
        }
        else
        {
            Log.i("MeuTeste", "conluirEtapa2: Voce não respodeu tudo faltou: " + (categoriaSize - questoesRespondidas));
        }

        for (int i = 0; i < questoesRespondidas; i++)
        {
            int key = sparseIntArray.keyAt(i);

            Resposta resposta = new Resposta();

            resposta.setQuestao(mQuestaoBll.getQuestao(key));
            resposta.setResposta(sparseIntArray.get(key));
            if (!mAvaliacaoBll.temResposta(avaliacao.getId(),resposta))
            {
                mRespostaBll.addResposta(resposta);
                avaliacao.setResposta(resposta);
            }
            else
            {
                mAvaliacaoBll.editRespostaDeUmaAvaliacao(avaliacao.getId(), resposta);
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
    public boolean onOptionsItemSelected(MenuItem item)
    {
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
