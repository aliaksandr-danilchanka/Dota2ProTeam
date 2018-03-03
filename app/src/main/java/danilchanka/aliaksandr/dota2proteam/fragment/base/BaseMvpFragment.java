package danilchanka.aliaksandr.dota2proteam.fragment.base;

import android.os.Bundle;
import android.support.annotation.StringRes;

import danilchanka.aliaksandr.dota2proteam.Dota2ProTeamApp;
import danilchanka.aliaksandr.dota2proteam.activity.base.BaseActivity;
import danilchanka.aliaksandr.dota2proteam.di.component.DaggerFragmentComponent;
import danilchanka.aliaksandr.dota2proteam.di.component.FragmentComponent;
import danilchanka.aliaksandr.dota2proteam.presenter.base.BaseLoadingMvpPresenter;
import eu.inloop.viewmodel.IView;
import eu.inloop.viewmodel.base.ViewModelBaseFragment;

/**
 * Base class for fragments implementing MvpView.
 */
public abstract class BaseMvpFragment<T extends IView, R extends BaseLoadingMvpPresenter<T>> extends ViewModelBaseFragment<T, R> {

    private FragmentComponent mFragmentComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragmentComponent = DaggerFragmentComponent.builder()
                .appComponent(Dota2ProTeamApp.getAppComponent())
                .build();
    }

    public BaseActivity getBaseActivity() {
        try {
            return (BaseActivity) getActivity();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setTitle(String title) {
        if (getActivity() != null && ((BaseActivity) getActivity()).getSupportActionBar() != null && title != null) {
            ((BaseActivity) getActivity()).getSupportActionBar().setTitle(title);
        }
    }

    public void setTitle(@StringRes int titleResId) {
        if (getActivity() != null && ((BaseActivity) getActivity()).getSupportActionBar() != null) {
            ((BaseActivity) getActivity()).getSupportActionBar().setTitle(titleResId);
        }
    }

    public FragmentComponent getFragmentComponent() {
        return mFragmentComponent;
    }


}
