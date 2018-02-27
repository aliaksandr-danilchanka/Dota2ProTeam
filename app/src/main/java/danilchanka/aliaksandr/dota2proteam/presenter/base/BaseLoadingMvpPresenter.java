package danilchanka.aliaksandr.dota2proteam.presenter.base;


import danilchanka.aliaksandr.dota2proteam.view.base.MvpView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public abstract class BaseLoadingMvpPresenter<T extends MvpView> extends BaseMvpPresenter<T>{

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.dispose();
    }

    protected void addSubscription(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }
}
