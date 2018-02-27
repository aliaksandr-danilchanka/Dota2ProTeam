package danilchanka.aliaksandr.dota2proteam.presenter.base;

import danilchanka.aliaksandr.dota2proteam.view.base.MvpView;

public interface MvpPresenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
