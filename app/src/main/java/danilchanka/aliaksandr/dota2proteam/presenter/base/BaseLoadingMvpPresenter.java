package danilchanka.aliaksandr.dota2proteam.presenter.base;


import android.os.Bundle;
import android.support.annotation.Nullable;

import danilchanka.aliaksandr.dota2proteam.Dota2ProTeamApp;
import danilchanka.aliaksandr.dota2proteam.di.component.DaggerFragmentComponent;
import danilchanka.aliaksandr.dota2proteam.di.component.FragmentComponent;
import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.IView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public abstract class BaseLoadingMvpPresenter<T extends IView> extends AbstractViewModel<T> {

    private FragmentComponent mFragmentComponent;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.dispose();
    }

    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        mFragmentComponent = DaggerFragmentComponent.builder()
                .appComponent(Dota2ProTeamApp.getAppComponent())
                .build();
    }

    public FragmentComponent getFragmentComponent() {
        return mFragmentComponent;
    }

    protected void addSubscription(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }
}
