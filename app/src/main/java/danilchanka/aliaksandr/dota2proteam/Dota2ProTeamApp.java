package danilchanka.aliaksandr.dota2proteam;

import android.app.Application;

import javax.inject.Inject;

import danilchanka.aliaksandr.dota2proteam.di.component.AppComponent;
import danilchanka.aliaksandr.dota2proteam.di.component.ComponentManager;
import danilchanka.aliaksandr.dota2proteam.di.component.DaggerAppComponent;
import danilchanka.aliaksandr.dota2proteam.module.ApiModule;
import danilchanka.aliaksandr.dota2proteam.module.AppModule;
import io.paperdb.Paper;

public class Dota2ProTeamApp extends Application {

    private static AppComponent sAppComponent;

    @Inject
    ComponentManager mComponentManager;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
        Paper.init(this);
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    private void initComponent() {
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule("https://api.opendota.com/api/"))
                .build();
    }
}
