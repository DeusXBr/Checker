package checker.ifrs.edu.checker.view.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Iterator;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.model.bll.CategoriaBll;
import checker.ifrs.edu.checker.model.dal.CategoriaDal;
import checker.ifrs.edu.checker.vo.Categoria;
import io.realm.Realm;

public class AvaliacaoActivity extends AppCompatActivity {

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);

        // Metodo para inicializar o toolbar
        initToolBar();


        CategoriaDal mCategoriaDal = new CategoriaDal(this);
        CategoriaBll mCategoriaBll = new CategoriaBll(mCategoriaDal);

        mCategoriaBll.addCategoria(new Categoria(1, "Calçadas"));
        mCategoriaBll.addCategoria(new Categoria(2, "Rebaixamento de calçadas"));
        mCategoriaBll.addCategoria(new Categoria(3, "Coletores"));
        mCategoriaBll.addCategoria(new Categoria(4, "Circulação externa"));
        mCategoriaBll.addCategoria(new Categoria(5, "Circulação interna"));
        mCategoriaBll.addCategoria(new Categoria(6, "Portas"));
        mCategoriaBll.addCategoria(new Categoria(7, "Refeitório e cantina"));
        mCategoriaBll.addCategoria(new Categoria(8, "Mobiliário e layout"));
        mCategoriaBll.addCategoria(new Categoria(9, "Biblioteca"));
        mCategoriaBll.addCategoria(new Categoria(10, "Bebedouro"));
        mCategoriaBll.addCategoria(new Categoria(11, "Circulação vertical"));
        mCategoriaBll.addCategoria(new Categoria(12, "Plataforma elevatória"));
        mCategoriaBll.addCategoria(new Categoria(13, "Escada"));
        mCategoriaBll.addCategoria(new Categoria(14, "Rampas"));
        mCategoriaBll.addCategoria(new Categoria(15, "Corrimão e guarda-corpo"));
        mCategoriaBll.addCategoria(new Categoria(16, "Circulação vertical"));
        mCategoriaBll.addCategoria(new Categoria(17, "Sanitários e vestiários"));
        mCategoriaBll.addCategoria(new Categoria(18, "Cinamas, teatros, auditórios e similares"));
        mCategoriaBll.addCategoria(new Categoria(19, "Estacionamento"));
        mCategoriaBll.addCategoria(new Categoria(20, "Geral"));


        LinearLayout mlinearLayout = (LinearLayout) findViewById(R.id.content_avaliacao);
        ArrayList<Categoria> resultCategoria = mCategoriaBll.getAllCategorias();
        Iterator itr = resultCategoria.iterator();

        while(itr.hasNext()){
            Categoria mCategoria = (Categoria)itr.next();

            CheckBox checkBox = new CheckBox(this);
            checkBox.setId(mCategoria.getId());
            checkBox.setText(mCategoria.getNome());
            mlinearLayout.addView(checkBox);
        }

        Button mButton = new Button(this);

        mButton.setText("Resultado");
        mlinearLayout.addView(mButton);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // acao para voltar no toolbar
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Recupera o titulo do app no xml strings
        String title = getResources().getString(R.string.avaliacao_toolbar_title);

        // Coloca o title, recuperado da linha acima, como titulo do toolbar
        toolbar.setTitle(title);

        setSupportActionBar(toolbar);

        // mostra o botao voltar no toolbar
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
