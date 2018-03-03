package danilchanka.aliaksandr.dota2proteam.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import danilchanka.aliaksandr.dota2proteam.R;
import danilchanka.aliaksandr.dota2proteam.activity.base.BaseFragmentActivity;
import danilchanka.aliaksandr.dota2proteam.entity.ProTeam;
import danilchanka.aliaksandr.dota2proteam.fragment.ProTeamDetailFragment;

public class ProTeamDetailActivity extends BaseFragmentActivity {

    public static final String ARG_PRO_TEAM = "ARG_PRO_TEAM";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.imgUserIcon)
    ImageView mImgUserIcon;
    @BindView(R.id.txtName)
    TextView mTxtName;
    @BindView(R.id.detailView)
    LinearLayout mLinearLayout;

    private ProTeam mProTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_team_detail);
        ButterKnife.bind(this);
        mProTeam = getIntent().getParcelableExtra(ARG_PRO_TEAM);
        setUpAppBar();

        if (savedInstanceState == null) {
            addFragment(ProTeamDetailFragment.newInstance(mProTeam));
        }
    }

    private void setUpAppBar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setTitle(mProTeam.getName());

        if (mProTeam.getLogo_url() != null) {
            Picasso.with(mLinearLayout.getContext())
                    .load(mProTeam.getLogo_url())
                    .into(mImgUserIcon);
        }
        mTxtName.setText(mProTeam.getName());
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.content_pro_team_detail;
    }
}
