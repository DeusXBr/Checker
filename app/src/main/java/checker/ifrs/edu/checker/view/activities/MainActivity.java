package checker.ifrs.edu.checker.view.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.model.bll.AvaliacaoBll;
import checker.ifrs.edu.checker.vo.Avaliacao;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_EXTRA = "checker.ifrs.edu.checker.TITULO_AVALIACAO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.close, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem Item) {
        finish();
        return true;
    }

    public void novaAvaliacao(View view) {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(promptsView);

        final EditText titulo = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);

        builder.setPositiveButton("criar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                AvaliacaoBll mAvaliacaoBll = new AvaliacaoBll();

                //Log.i("MeuTeste", "Titulo preenchido pelo usuario:" + titulo.getText() + "!");
                titulo.setText(titulo.getText().toString().trim());
                //Log.i("MeuTeste", "Titulo preenchido pelo usuario:" + titulo.getText() + "!");

                // verifica se foi preenchido o titulo
                if (titulo.getText().toString().length() > 0) {

                    Avaliacao resultAvaliacao = mAvaliacaoBll.getAvaliacao(titulo.getText().toString());

                    // verifica se existe uma avaliacao com o mesmo titulo no realm
                    if( resultAvaliacao == null ){

                        // cria nova avaliacao
                        Avaliacao avaliacao = new Avaliacao();
                        avaliacao.setNome(titulo.getText().toString());

                        mAvaliacaoBll.addAvaliacao(avaliacao); // adiciona no realm

                        Bundle bundle = new Bundle();
                        bundle.putString(MainActivity.KEY_EXTRA, titulo.getText().toString());

                        // Intent para nova activity
                        Intent intent = new Intent(MainActivity.this, AvaliacaoActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
            }

        })
            .setNegativeButton("Cancelar", null)
            .setCancelable(false);

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void listarAvaliacoes(View view) {
        Intent intent = new Intent(MainActivity.this, ListaRelatorioActivity.class);
        startActivity(intent);
    }

    public void sobre(View view) {
        Intent intent = new Intent(MainActivity.this, SobreActivity.class);
        startActivity(intent);
    }

    /**
     * Metodo para inicializar o toolbar
     *
     * Obs: chamada esta no construtor da classe
     *
     */
    public void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Recupera o titulo do app no xml strings
        String title = getResources().getString(R.string.main_toolbar_title);

        // Coloca o title, recuperado da linha acima, como titulo do toolbar
        toolbar.setTitle(title);

        setSupportActionBar(toolbar);
    }

}
