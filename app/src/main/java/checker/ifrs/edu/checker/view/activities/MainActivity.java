package checker.ifrs.edu.checker.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import checker.ifrs.edu.checker.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Metodo para inicializar o toolbar
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
        Intent intent = new Intent(MainActivity.this, AvaliacaoActivity.class);
        startActivity(intent);

//        LayoutInflater li = LayoutInflater.from(this);
//        View promptsView = li.inflate(R.layout.dialog, null);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        builder.setView(promptsView);
//
//        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);
//
//        builder.setPositiveButton("ok", new DialogInterface.OnClickListener(){
//            @Override
//            public void onClick(DialogInterface dialog, int id){
//
//                // Intent para nova activity caso o usuario precione ok
//                Intent intent = new Intent(MainActivity.this, AvaliacaoActivity.class);
//                startActivity(intent);
//
//                // A entrada de dados do usuario precisa ser salva no banco de dados.
//                // code....
//
//                // Mostra mensagem do que foi digitado
//                Context context = getApplicationContext();
//                CharSequence text = userInput.getText();
//                int duration = Toast.LENGTH_SHORT;
//
//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
//            }
//        })
//                .setNegativeButton("Cancelar", null)
//                .setCancelable(false);
//
//        AlertDialog alert = builder.create();
//        alert.show();
    }

    public void listarAvaliacoes(View view) {
        Intent intent = new Intent(MainActivity.this, ListaAvaliacaoActivity.class);
        startActivity(intent);
    }

    public void sobre(View view) {
        Intent intent = new Intent(MainActivity.this, SobreActivity.class);
        startActivity(intent);
    }

    public void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Recupera o titulo do app no xml strings
        String title = getResources().getString(R.string.main_toolbar_title);

        // Coloca o title, recuperado da linha acima, como titulo do toolbar
        toolbar.setTitle(title);

        setSupportActionBar(toolbar);
    }
}
