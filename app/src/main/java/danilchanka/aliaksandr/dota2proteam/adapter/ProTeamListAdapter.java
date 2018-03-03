package danilchanka.aliaksandr.dota2proteam.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import danilchanka.aliaksandr.dota2proteam.R;
import danilchanka.aliaksandr.dota2proteam.adapter.base.SimpleRecyclerAdapter;
import danilchanka.aliaksandr.dota2proteam.entity.ProTeam;

public class ProTeamListAdapter extends SimpleRecyclerAdapter<ProTeam> {

    @Inject
    public ProTeamListAdapter(Context context) {
        super(context);
    }

    @Override
    public SimpleRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProTeamViewHolder(getContext(), getViewHolderView(parent, R.layout.row_pro_team));
    }

    public class ProTeamViewHolder extends SimpleRecyclerViewHolder<ProTeam> {
        @BindView(R.id.imgUserIcon)
        ImageView mImgUserIcon;
        @BindView(R.id.layoutContent)
        RelativeLayout mRelativeLayout;
        @BindView(R.id.txtName)
        TextView mTxtName;
        @BindView(R.id.txtRating)
        TextView mTxtRating;

        public ProTeamViewHolder(Context context, View itemView) {
            super(context, itemView);
        }

        @Override
        protected void bind(ProTeam proTeam) {
            super.bind(proTeam);
            mTxtName.setText(proTeam.getName());
            mTxtRating.setText(String.format("%s", proTeam.getRating()));

            if (proTeam.getLogo_url() != null) {
                Picasso.with(mRelativeLayout.getContext())
                        .load(proTeam.getLogo_url())
                        .into(mImgUserIcon);
            }
        }
    }
}
