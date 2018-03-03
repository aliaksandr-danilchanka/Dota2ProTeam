package danilchanka.aliaksandr.dota2proteam.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;

import javax.inject.Inject;

import danilchanka.aliaksandr.dota2proteam.di.PerFragment;
import danilchanka.aliaksandr.dota2proteam.entity.ProTeam;
import danilchanka.aliaksandr.dota2proteam.model.ProTeamModel;
import danilchanka.aliaksandr.dota2proteam.presenter.base.BaseLoadingMvpPresenter;
import danilchanka.aliaksandr.dota2proteam.view.ProTeamListMvpView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

@PerFragment
public class ProTeamListMvpPresenter extends BaseLoadingMvpPresenter<ProTeamListMvpView> {

    @Inject
    ProTeamModel mProTeamModel;

    private ArrayList<ProTeam> mProTeams;
    private ViewState mViewState = ViewState.INIT;

    @Inject
    public ProTeamListMvpPresenter() {
    }

    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        getFragmentComponent().inject(this);
    }

    @Override
    public void onBindView(@NonNull ProTeamListMvpView mvpView) {
        super.onBindView(mvpView);
        if (getViewState() == ViewState.INIT && mProTeams == null) {
            loadAndRefreshContacts();
        } else {
            showViewState();
        }
    }

    public void onProTeamClick(ProTeam proTeam, View contactView) {
        getViewOptional().startProTeamDetail(proTeam, contactView);
    }

    public void onReloadClick() {
        loadAndRefreshContacts();
    }

    public void onSwipeToRefresh() {
        loadAndRefreshContacts();
    }

    public void refreshContactList() {
        loadAndRefreshContacts();
    }

    private ViewState getViewState() {
        return mViewState;
    }

    private void setViewState(ViewState state) {
        mViewState = state;
        showViewState();
    }

    private void loadAndRefreshContacts() {
        setViewState(ViewState.LOADING);
        addSubscription(
                mProTeamModel.loadProTeamList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<ArrayList<ProTeam>>() {

                            @Override
                            public void onNext(ArrayList<ProTeam> proTeams) {
                                mProTeams = proTeams;
                                if (proTeams == null) {
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
                if (mProTeams.isEmpty()) {
                    getViewOptional().showEmpty();
                } else {
                    getViewOptional().showProTeamList(mProTeams);
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
