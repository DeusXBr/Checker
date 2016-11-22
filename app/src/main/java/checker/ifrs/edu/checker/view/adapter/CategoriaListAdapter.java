package checker.ifrs.edu.checker.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import java.util.List;

import checker.ifrs.edu.checker.R;
import checker.ifrs.edu.checker.view.activities.AvaliacaoActivity;
import checker.ifrs.edu.checker.view.activities.QuestaoActivity;
import checker.ifrs.edu.checker.vo.Categoria;

public class CategoriaListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Categoria> mCategoriaList;

    public CategoriaListAdapter(Context mContext, List<Categoria> mCategoriaList){
        this.mContext = mContext;
        this.mCategoriaList = mCategoriaList;
    }

    @Override
    public int getCount() {
        return mCategoriaList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCategoriaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext, R.layout.item_listview_avaliacao, null); // set o layout de cada item

        CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkbox);
        checkBox.setText(mCategoriaList.get(position).getNome()); // set o text do checkbox

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();

                Categoria categoria = (Categoria) getItem(position);
                bundle.putString(AvaliacaoActivity.KEY_EXTRA, categoria.getNome());

                Intent intent = new Intent(view.getContext(), QuestaoActivity.class);
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);

            }
        });

        return v;
    }
}
