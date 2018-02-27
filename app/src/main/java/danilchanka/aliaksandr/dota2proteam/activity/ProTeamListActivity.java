package danilchanka.aliaksandr.dota2proteam.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import danilchanka.aliaksandr.dota2proteam.R;
import danilchanka.aliaksandr.dota2proteam.activity.base.BaseFragmentActivity;
import danilchanka.aliaksandr.dota2proteam.fragment.ProTeamListFragment;

public class ProTeamListActivity extends BaseFragmentActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_team_list);
        ButterKnife.bind(this);
        setUpAppBar();

        if (savedInstanceState == null) {
            addFragment(ProTeamListFragment.newInstance());
        }
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.content;
    }

    private void setUpAppBar() {
        setSupportActionBar(mToolbar);
    }
}
