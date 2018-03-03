package danilchanka.aliaksandr.dota2proteam.fragment;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import danilchanka.aliaksandr.dota2proteam.R;
import danilchanka.aliaksandr.dota2proteam.activity.ProTeamDetailActivity;
import danilchanka.aliaksandr.dota2proteam.adapter.ProTeamListAdapter;
import danilchanka.aliaksandr.dota2proteam.entity.ProTeam;
import danilchanka.aliaksandr.dota2proteam.fragment.base.BaseMvpFragment;
import danilchanka.aliaksandr.dota2proteam.presenter.ProTeamListMvpPresenter;
import danilchanka.aliaksandr.dota2proteam.ui.LoadingView;
import danilchanka.aliaksandr.dota2proteam.view.ProTeamListMvpView;

public class ProTeamListFragment extends BaseMvpFragment<ProTeamListMvpView, ProTeamListMvpPresenter> implements ProTeamListMvpView {

    @Inject
    ProTeamListAdapter mProTeamListAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeToRefreshLayout)
    SwipeRefreshLayout mSwipeToRefreshLayout;
    @BindView(R.id.loadingView)
    LoadingView mLoadingView;

    public static ProTeamListFragment newInstance() {
        return new ProTeamListFragment();
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pro_team_list, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();
        mSwipeToRefreshLayout.setOnRefreshListener(() -> {
            mSwipeToRefreshLayout.setRefreshing(false);
            getViewModel().onSwipeToRefresh();
        });
        return view;
    }



    @Override public void showProTeamList(ArrayList<ProTeam> proTeams) {
        mProTeamListAdapter.setItems(proTeams);
        mProTeamListAdapter.notifyDataSetChanged();
        mLoadingView.setState(LoadingView.State.NORMAL);
    }

    @Override public void showLoading() {
        mLoadingView.setState(LoadingView.State.LOADING);
    }

    @Override public void showEmpty() {
        mLoadingView.setState(LoadingView.State.EMPTY);
    }

    @Override public void showError() {
        mLoadingView.setState(LoadingView.State.ERROR);
        mLoadingView.setOnReloadClickListener(getViewModel()::onReloadClick);
    }

    @Override
    public void startProTeamDetail(ProTeam proTeam, View proTeamView) {
        Intent intent = new Intent(getBaseActivity(), ProTeamDetailActivity.class);
        intent.putExtra(ProTeamDetailActivity.ARG_PRO_TEAM, proTeam);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String transitionName = getString(R.string.user_icon_transition);
            View sharedView = proTeamView.findViewById(R.id.imgUserIcon);

            ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(getBaseActivity(), sharedView, transitionName);
            startActivity(intent, transitionActivityOptions.toBundle());

        } else {
            getBaseActivity().startActivity(intent);
        }
    }

    @Override public void showRefreshingError() {
        Toast.makeText(getContext(), R.string.error_refreshing_contact_list, Toast.LENGTH_LONG).show();
    }

    private void initRecyclerView() {
        mRecyclerView.setAdapter(mProTeamListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mProTeamListAdapter.setOnItemClickListener(getViewModel()::onProTeamClick);
    }
}
