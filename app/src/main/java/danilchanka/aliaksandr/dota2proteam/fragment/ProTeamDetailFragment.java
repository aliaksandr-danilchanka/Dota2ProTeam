package danilchanka.aliaksandr.dota2proteam.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import danilchanka.aliaksandr.dota2proteam.R;
import danilchanka.aliaksandr.dota2proteam.entity.ProTeam;
import danilchanka.aliaksandr.dota2proteam.fragment.base.BaseMvpFragment;
import danilchanka.aliaksandr.dota2proteam.presenter.ProTeamDetailMvpPresenter;
import danilchanka.aliaksandr.dota2proteam.ui.LoadingView;
import danilchanka.aliaksandr.dota2proteam.view.ProTeamDetailMvpView;

public class ProTeamDetailFragment extends BaseMvpFragment<ProTeamDetailMvpView, ProTeamDetailMvpPresenter> implements ProTeamDetailMvpView {

    private static final String KEY_PRO_TEAM_ID = "KEY_PRO_TEAM_ID";

    @BindView(R.id.txtRating)
    TextView mRating;
    @BindView(R.id.txtWins)
    TextView mWins;
    @BindView(R.id.txtLosses)
    TextView mLosses;
    @BindView(R.id.txtTag)
    TextView mTag;
    @BindView(R.id.loadingView)
    LoadingView mLoadingView;

    public static ProTeamDetailFragment newInstance(int proTeamId) {
        ProTeamDetailFragment f = new ProTeamDetailFragment();
        Bundle b = new Bundle();
        b.putInt(KEY_PRO_TEAM_ID, proTeamId);
        f.setArguments(b);
        return f;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentComponent().inject(this);
        getViewModel().setTeamId(getArguments().getInt(KEY_PRO_TEAM_ID));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mV = inflater.inflate(R.layout.fragment_pro_team_detail, null);
        ButterKnife.bind(this, mV);
        return mV;
    }

    @Override
    public void showProTeamDetail(ProTeam proTeam) {
        mRating.setText(String.format("%s", proTeam.getRating()));
        mWins.setText(String.format("%s", proTeam.getWins()));
        mLosses.setText(String.format("%s", proTeam.getLosses()));
        mTag.setText(proTeam.getTag());
        mLoadingView.setState(LoadingView.State.NORMAL);
    }

    @Override
    public void showLoading() {
        mLoadingView.setState(LoadingView.State.LOADING);
    }

    @Override
    public void showEmpty() {
        mLoadingView.setState(LoadingView.State.EMPTY);
    }

    @Override
    public void showError() {
        mLoadingView.setState(LoadingView.State.ERROR);
        mLoadingView.setOnReloadClickListener(() -> getViewModel().onReloadClick());
    }

}
