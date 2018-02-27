package danilchanka.aliaksandr.dota2proteam.module;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import danilchanka.aliaksandr.dota2proteam.di.PerApplication;
import danilchanka.aliaksandr.dota2proteam.di.component.ComponentManager;

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @PerApplication
    Application providesApplication() {
        return mApplication;
    }

    @Provides @PerApplication
    Context providesContext() {
        return mApplication;
    }

    @Provides @PerApplication
    ComponentManager providesComponentManager() {
        return new ComponentManager();
    }
}
