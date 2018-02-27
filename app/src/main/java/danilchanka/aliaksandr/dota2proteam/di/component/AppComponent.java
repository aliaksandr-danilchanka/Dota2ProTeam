package danilchanka.aliaksandr.dota2proteam.di.component;

import android.content.Context;

import dagger.Component;
import danilchanka.aliaksandr.dota2proteam.di.PerApplication;
import danilchanka.aliaksandr.dota2proteam.model.ProTeamModel;
import danilchanka.aliaksandr.dota2proteam.module.ApiModule;
import danilchanka.aliaksandr.dota2proteam.module.AppModule;

@PerApplication
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    ProTeamModel proTeamModel();

    Context context();

    ComponentManager componentManager();
}
