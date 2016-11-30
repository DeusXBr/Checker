package checker.ifrs.edu.checker.app;

import android.app.Application;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

import checker.ifrs.edu.checker.vo.Categoria;
import checker.ifrs.edu.checker.vo.Questao;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("my-database.realm")      // nome do arquivo do banco de dados realm
                .deleteRealmIfMigrationNeeded() // deleta todos os dados quando trocado a versão do db
                .initialData(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        try {
                            AssetManager assetManager = getAssets();

                            InputStream is = assetManager.open("categoria.json");
                            realm.createOrUpdateAllFromJson(Categoria.class, is);

                            is = assetManager.open("questoes.json");
                            realm.createOrUpdateAllFromJson(Questao.class, is);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

}
