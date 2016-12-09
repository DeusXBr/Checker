package checker.ifrs.edu.checker.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.vo.Categoria;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

public class CategoriaListAdapter extends RealmBaseAdapter<Categoria> implements ListAdapter {

    private RealmResults<Categoria> realmResults;

    public CategoriaListAdapter(Context contexto, RealmResults<Categoria> realmResults){
        super(contexto, realmResults);

        this.realmResults = realmResults;
    }

    @Override
    public View getView(int position, View converterView, ViewGroup viewGroup) {
        final CustomViewHolder holder;

        if(converterView == null)
        {
            converterView = inflater.inflate(R.layout.item_listview_avaliacao, viewGroup, false);
            holder = new CustomViewHolder();
            converterView.setTag( holder );

            holder.checkBox = (CheckBox) converterView.findViewById(R.id.checkbox);
        }
        else
        {
            holder = (CustomViewHolder) converterView.getTag();
        }

        // TODO verificar se esta categoria tem todas as resposta respondidas

        Categoria categoria = realmResults.get(position);
        holder.checkBox.setText(categoria.getNome());

        return converterView;
    }

    private static class CustomViewHolder{
        CheckBox checkBox;
    }

    //    private Context mContext;
//    private List<Categoria> mCategoriaList;
//
//    public CategoriaListAdapter(Context mContext, List<Categoria> mCategoriaList){
//        this.mContext = mContext;
//        this.mCategoriaList = mCategoriaList;
//    }
//
//    @Override
//    public int getCount() {
//        return mCategoriaList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return mCategoriaList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(final int position, View view, ViewGroup viewGroup) {
//        View v = View.inflate(mContext, R.layout.item_listview_avaliacao, null); // set o layout de cada item
//
//        CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkbox);
//        checkBox.setText(mCategoriaList.get(position).getNome()); // set o text do checkbox
//
//        v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Bundle bundle = new Bundle();
//
//                Categoria categoria = (Categoria) getItem(position);
//                bundle.putString(AvaliacaoActivity.KEY_EXTRA, categoria.getNome());
//
//                Intent intent = new Intent(view.getContext(), QuestaoActivity.class);
//                intent.putExtras(bundle);
//                view.getContext().startActivity(intent);
//
//            }
//        });
//
//        return v;
//    }
}
