package danilchanka.aliaksandr.dota2proteam.di.component;

import dagger.Component;
import danilchanka.aliaksandr.dota2proteam.di.PerFragment;
import danilchanka.aliaksandr.dota2proteam.fragment.ProTeamDetailFragment;
import danilchanka.aliaksandr.dota2proteam.fragment.ProTeamListFragment;
import danilchanka.aliaksandr.dota2proteam.presenter.ProTeamListMvpPresenter;

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Component(dependencies = AppComponent.class)
public interface FragmentComponent {

    void inject(ProTeamDetailFragment fragment);

    void inject(ProTeamListFragment fragment);

    void inject(ProTeamListMvpPresenter presenter);
}
