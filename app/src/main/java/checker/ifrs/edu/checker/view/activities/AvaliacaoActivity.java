package checker.ifrs.edu.checker.view.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.model.bll.CategoriaBll;
import checker.ifrs.edu.checker.view.adapter.CategoriaListAdapter;
import checker.ifrs.edu.checker.vo.Categoria;

public class AvaliacaoActivity extends AppCompatActivity {

    public static final String KEY_EXTRA = "checker.ifrs.edu.checker.NOME_CATEGORIA";

    private ListView listCategorias;
    private CategoriaListAdapter categoriaListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);

        initToolBar();

        CategoriaBll mCategoriaBll = new CategoriaBll();

        this.listCategorias = (ListView) findViewById(R.id.listViewAvaliacao); // pega layout com o listView
        ArrayList<Categoria> resultCategoria = mCategoriaBll.getAllCategorias(); // pega as categorias do realm

        this.categoriaListAdapter = new CategoriaListAdapter(this, resultCategoria);
        this.listCategorias.setAdapter(categoriaListAdapter); // adiciona o adapter criado acima no listView
    }

    /**
     *  CallBack para gerar os relatorios
     *
     *  Obs: onClick esta no activity_avaliacao.xml -> content_avaliacao.xml
     */
    public void gerarRelatorio(View view){
        //TODO criar nova intent, validar e calcular os resultado.
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
        String title = getResources().getString(R.string.avaliacao_toolbar_title);

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
