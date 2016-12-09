package checker.ifrs.edu.checker.view.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.model.bll.AvaliacaoBll;
import checker.ifrs.edu.checker.model.bll.CategoriaBll;
import checker.ifrs.edu.checker.utils.CalculaAvaliacaoUtils;
import checker.ifrs.edu.checker.view.adapter.CategoriaListAdapter;
import checker.ifrs.edu.checker.vo.Avaliacao;
import checker.ifrs.edu.checker.vo.Categoria;
import checker.ifrs.edu.checker.vo.Resposta;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class AvaliacaoActivity extends AppCompatActivity {

    public static final String KEY_EXTRA = "checker.ifrs.edu.checker.NOME_CATEGORIA";

    private ListView listCategorias;
    private CategoriaListAdapter categoriaListAdapter;
    private String titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);

        // pega os dados do sharedPreference
        SharedPreferences sharedPrefs = getSharedPreferences(MainActivity.PREFS_NAME, MODE_PRIVATE);
        String avaliacaoTitulo = sharedPrefs.getString("avaliacaoTitulo", null);

        CategoriaBll mCategoriaBll = new CategoriaBll();

        if( avaliacaoTitulo != null ) { //verifica se tem dados no sharedPreferences
            this.titulo = avaliacaoTitulo;

            initToolBar();

            this.listCategorias = (ListView) findViewById(R.id.listViewAvaliacao); // pega layout com o listView
            RealmResults<Categoria> resultCategoria = mCategoriaBll.getAllCategorias(); // pega as categorias do realm

            this.categoriaListAdapter = new CategoriaListAdapter(this, resultCategoria);
            this.listCategorias.setAdapter(categoriaListAdapter); // adiciona o adapter criado acima no listView

            listCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Bundle bundle = new Bundle();
                    bundle.putString(AvaliacaoActivity.KEY_EXTRA, categoriaListAdapter.getItem(position).getNome());

                    Intent intent = new Intent(view.getContext(), QuestaoActivity.class);
                    intent.putExtras(bundle);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    view.getContext().startActivity(intent);
                }
            });

        }else{
            throw new IllegalArgumentException("Activity extra não encontrada: " + MainActivity.KEY_EXTRA);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     *  CallBack para gerar os relatorios
     *
     *  Obs: onClick esta no activity_avaliacao.xml -> content_avaliacao.xml
     */
    public void gerarRelatorio(View view){
        AvaliacaoBll avaliacaoBll = new AvaliacaoBll();
        Avaliacao avaliacao = avaliacaoBll.getAvaliacao(this.titulo);

        CalculaAvaliacaoUtils calculaAvaliacaoUtils = new CalculaAvaliacaoUtils(avaliacao.getRespostas());

        try{
            float nota = calculaAvaliacaoUtils.avaliar();
            String status = calculaAvaliacaoUtils.getStatus();

            Realm.getDefaultInstance().beginTransaction();
            avaliacao.setEstado(status);
            avaliacao.setNota(nota);
            Realm.getDefaultInstance().commitTransaction();

            Intent intent = new Intent(AvaliacaoActivity.this, ResultadoActivity.class);
            startActivity(intent);

        }catch (NullPointerException e){
            Toast.makeText(getApplicationContext(), "Não tem nenhuma questão respondida! ",Toast.LENGTH_SHORT).show();
        }

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
        String title = getResources().getString(R.string.avaliacao_toolbar_title) + " - " + this.titulo;

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
