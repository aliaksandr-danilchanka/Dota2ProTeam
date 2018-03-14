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
import danilchanka.aliaksandr.dota2proteam.util.PaperHelper;
import danilchanka.aliaksandr.dota2proteam.view.ProTeamListMvpView;
import io.paperdb.Paper;
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
            loadAndRefreshProTeams();
        } else {
            showViewState();
        }
    }

    public void onProTeamClick(ProTeam proTeam, View contactView) {
        getViewOptional().startProTeamDetail(proTeam, contactView);
    }

    public void onReloadClick() {
        loadAndRefreshProTeams();
    }

    public void onSwipeToRefresh() {
        refreshProTeams();
    }

    public void refreshProTeamList() {
        refreshProTeams();
    }

    private ViewState getViewState() {
        return mViewState;
    }

    private void setViewState(ViewState state) {
        mViewState = state;
        showViewState();
    }

    private void refreshProTeams() {
        addSubscription(
                mProTeamModel.loadProTeamList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<ArrayList<ProTeam>>() {
                            @Override
                            public void onComplete() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                getViewOptional().showRefreshingError();
                            }

                            @Override
                            public void onNext(ArrayList<ProTeam> proTeams) {
                                mProTeams = proTeams;
                                setViewState(ViewState.NORMAL);
                            }
                        }));
    }


    private void loadAndRefreshProTeams() {
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
                                getDataFromPaper();
                            }

                            @Override
                            public void onComplete() {

                            }
                        }));
    }

    private void getDataFromPaper(){
        mProTeams = Paper.book(PaperHelper.BOOK_DEFAULT).read(PaperHelper.PRO_TEAM_LIST);
        if (mProTeams == null) {
            setViewState(ViewState.ERROR);
        } else {
            setViewState(ViewState.NORMAL);
        }
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
