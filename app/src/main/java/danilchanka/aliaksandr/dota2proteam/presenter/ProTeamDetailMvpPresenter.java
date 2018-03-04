package danilchanka.aliaksandr.dota2proteam.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import danilchanka.aliaksandr.dota2proteam.entity.ProTeam;
import danilchanka.aliaksandr.dota2proteam.model.ProTeamModel;
import danilchanka.aliaksandr.dota2proteam.presenter.base.BaseLoadingMvpPresenter;
import danilchanka.aliaksandr.dota2proteam.view.ProTeamDetailMvpView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ProTeamDetailMvpPresenter extends BaseLoadingMvpPresenter<ProTeamDetailMvpView> {

    @Inject
    ProTeamModel mProTeamModel;

    private ProTeam mProTeam;
    private int teamId;
    private ViewState mViewState = ViewState.INIT;

    @Inject
    public ProTeamDetailMvpPresenter() {
    }

    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        getFragmentComponent().inject(this);
    }

    @Override
    public void onBindView(@NonNull ProTeamDetailMvpView mvpView) {
        super.onBindView(mvpView);
        if (getViewState() == ViewState.INIT && mProTeam == null) {
            loadProTeamItem();
        } else {
            showViewState();
        }
    }

    public void onReloadClick() {
        loadProTeamItem();
    }

    private ViewState getViewState() {
        return mViewState;
    }

    private void setViewState(ViewState state) {
        mViewState = state;
        showViewState();
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    private void loadProTeamItem(){
        setViewState(ViewState.LOADING);
        addSubscription(
                mProTeamModel.loadProTeamDetail(teamId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<ProTeam>() {

                            @Override
                            public void onNext(ProTeam proTeam) {
                                mProTeam = proTeam;
                                if (proTeam == null) {
                                    setViewState(ViewState.ERROR);
                                } else {
                                    setViewState(ViewState.NORMAL);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                setViewState(ViewState.ERROR);
                            }

                            @Override
                            public void onComplete() {

                            }
                        }));
    }

    private void showViewState() {
        switch (mViewState) {
            case NORMAL:
                if (mProTeam == null) {
                    getViewOptional().showEmpty();
                } else {
                    getViewOptional().showProTeamDetail(mProTeam);
                }
                break;
            case LOADING:
                getViewOptional().showLoading();
                break;
            case ERROR:
                getViewOptional().showError();
        }
    }

    enum ViewState {INIT, NORMAL, LOADING, ERROR}
}
