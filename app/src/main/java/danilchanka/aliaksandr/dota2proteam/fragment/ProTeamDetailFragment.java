package danilchanka.aliaksandr.dota2proteam.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import danilchanka.aliaksandr.dota2proteam.R;
import danilchanka.aliaksandr.dota2proteam.entity.ProTeam;

public class ProTeamDetailFragment extends Fragment{

    private static final String KEY_PRO_TEAM = "KEY_PRO_TEAM";

    @BindView(R.id.txtRating)
    TextView mRating;
    @BindView(R.id.txtWins)
    TextView mWins;
    @BindView(R.id.txtLosses)
    TextView mLosses;
    @BindView(R.id.txtTag)
    TextView mTag;

    private ProTeam mProTeam;

    public static ProTeamDetailFragment newInstance(ProTeam proTeam) {
        ProTeamDetailFragment f = new ProTeamDetailFragment();
        Bundle b = new Bundle();
        b.putParcelable(KEY_PRO_TEAM, proTeam);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mV = inflater.inflate(R.layout.fragment_pro_team_detail, null);
        ButterKnife.bind(this, mV);
        mProTeam = getArguments().getParcelable(KEY_PRO_TEAM);
        initializeData();
        return mV;
    }

    private void initializeData() {
        mRating.setText(String.format("%s", mProTeam.getRating()));
        mWins.setText(String.format("%s", mProTeam.getWins()));
        mLosses.setText(String.format("%s", mProTeam.getLosses()));
        mTag.setText(mProTeam.getTag());
    }

}
