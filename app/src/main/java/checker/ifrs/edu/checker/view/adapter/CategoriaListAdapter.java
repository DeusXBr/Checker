package checker.ifrs.edu.checker.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.model.bll.CategoriaBll;
import checker.ifrs.edu.checker.utils.ModelUtils;
import checker.ifrs.edu.checker.vo.Avaliacao;
import checker.ifrs.edu.checker.vo.Categoria;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

import static checker.ifrs.edu.checker.utils.Helper.getAvaliacao;
import static checker.ifrs.edu.checker.utils.ModelUtils.getQuantiadeRespostasCategoria;
import static checker.ifrs.edu.checker.utils.ModelUtils.getQuantidadeQuestoesCategoria;

public class CategoriaListAdapter extends RealmBaseAdapter<Categoria> implements ListAdapter {

    private RealmResults<Categoria> realmResults;
    private Avaliacao avaliacao;

    public CategoriaListAdapter(Context contexto, RealmResults<Categoria> realmResults){
        super(contexto, realmResults);

        this.realmResults = realmResults;

        // pega a atual avaliacao no sharedPreferences
        avaliacao = getAvaliacao(contexto);
    }

    @Override
    public View getView(int position, View converterView, ViewGroup viewGroup) {
        final CustomViewHolder holder;

        if(converterView == null)
        {
            converterView = inflater.inflate(R.layout.item_listview_avaliacao, viewGroup, false);
            holder = new CustomViewHolder(converterView);
            converterView.setTag( holder );
        }
        else
        {
            holder = (CustomViewHolder) converterView.getTag();
        }

        Categoria categoria = realmResults.get(position); //categoria atual
        int quantidadeCategorias = getQuantidadeQuestoesCategoria(categoria); // quantas questoes tem nessa categoria
        int countResposta = getQuantiadeRespostasCategoria(avaliacao, categoria.getNome());
        holder.checkBox.setText( (categoria.getNome() + " - ("+ countResposta +" de " + quantidadeCategorias + ")") );

        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(false);

        ModelUtils modelUtils = new ModelUtils();

        Avaliacao avaliacao = getAvaliacao(context);
        if ( modelUtils.categoriaEstaCompleta(avaliacao, categoria) )
        {
            holder.checkBox.setChecked(true);
        }
        else
        {
            holder.checkBox.setChecked(false);
        }


        holder.checkBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // ... code ..
            }
        });


        return converterView;
    }

    private static class CustomViewHolder{
        CheckBox checkBox;

        CustomViewHolder(View view){
            checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        }
    }
}
