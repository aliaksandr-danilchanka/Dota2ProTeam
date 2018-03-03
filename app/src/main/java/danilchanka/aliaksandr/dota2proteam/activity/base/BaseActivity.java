package danilchanka.aliaksandr.dota2proteam.activity.base;

import android.view.MenuItem;

import eu.inloop.viewmodel.base.ViewModelBaseEmptyActivity;

public abstract class BaseActivity extends ViewModelBaseEmptyActivity {

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
