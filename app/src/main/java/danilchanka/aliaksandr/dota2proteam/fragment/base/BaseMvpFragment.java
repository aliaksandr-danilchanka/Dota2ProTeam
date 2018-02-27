package danilchanka.aliaksandr.dota2proteam.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import javax.inject.Inject;

import danilchanka.aliaksandr.dota2proteam.Dota2ProTeamApp;
import danilchanka.aliaksandr.dota2proteam.di.component.ComponentManager;
import danilchanka.aliaksandr.dota2proteam.di.component.DaggerFragmentComponent;
import danilchanka.aliaksandr.dota2proteam.di.component.FragmentComponent;
import danilchanka.aliaksandr.dota2proteam.presenter.base.BaseMvpPresenter;
import danilchanka.aliaksandr.dota2proteam.view.base.MvpView;

/**
 * Base class for fragments implementing MvpView.
 */
public abstract class BaseMvpFragment<V extends MvpView, T extends BaseMvpPresenter<V>> extends BaseFragment {

    private T presenter;

    private ComponentManager mComponentManager;
    private FragmentComponent mFragmentComponent;

    private boolean mDestroyedBySystem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mComponentManager = Dota2ProTeamApp.getAppComponent().componentManager();

        if (savedInstanceState == null) {
            mFragmentComponent = DaggerFragmentComponent.builder()
                    .appComponent(Dota2ProTeamApp.getAppComponent())
                    .build();
        } else {
            mFragmentComponent = mComponentManager.restoreComponent(savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            getPresenter().attachView((V) this);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mDestroyedBySystem = false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mDestroyedBySystem = true;
        mComponentManager.saveComponent(mFragmentComponent, outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getPresenter().detachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!mDestroyedBySystem) {
            getPresenter().onDestroy();
        }
    }

    protected T getPresenter() {
        return presenter;
    }

    @Inject
    public void setPresenter(T presenter) {
        this.presenter = presenter;
    }

    public FragmentComponent getFragmentComponent() {
        return mFragmentComponent;
    }

}
