package com.ntsoftware.vspc.myvspc.screens.other;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.ntsoftware.vspc.myvspc.MainActivity;
import com.ntsoftware.vspc.myvspc.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtherFragment extends Fragment {


    @BindView(R.id.other_nav_view)
    NavigationView navigationView;

    Context context;

    MainActivity activity;

    CoordinatorLayout.LayoutParams layoutParamsAppbarScroll;

    CoordinatorLayout.Behavior layoutBehavior;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_other, container, false);

        context = getContext();

        ButterKnife.bind(this,root);

        navigationView.setNavigationItemSelectedListener(itemSelectedListener);

        activity = ((MainActivity)getActivity());

        layoutParamsAppbarScroll = (CoordinatorLayout.LayoutParams) activity.getScrollLayout().getLayoutParams();

        layoutBehavior = layoutParamsAppbarScroll.getBehavior();

        layoutParamsAppbarScroll.setBehavior(null);

        return root;
    }

    @Override
    public void onStop() {
        super.onStop();
        layoutParamsAppbarScroll.setBehavior(layoutBehavior);
    }

    NavigationView.OnNavigationItemSelectedListener itemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_settings:
                    Toast.makeText(context, getString(R.string.nav_in_dev), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_web_home:
                    openWebSite("https://www.vspc34.ru/");
                    break;
                case R.id.nav_web_enrollee:
                    openWebSite("https://www.vspc34.ru/index.php?option=com_content&view=category&id=9&Itemid=22");
                    break;
                case R.id.nav_web_student:
                    openWebSite("https://www.vspc34.ru/index.php?option=com_content&view=category&id=50&Itemid=42");
                    break;
                case R.id.nav_web_about:
                    openWebSite("https://www.vspc34.ru/index.php?option=com_content&view=category&id=104&Itemid=152");
                    break;
                default:
                    Toast.makeText(context, "No support", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
    };

    private void openWebSite(String url){
        try {
            new CustomTabsIntent.Builder().build().launchUrl(context, Uri.parse(url));
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context, getString(R.string.nav_open_site_error_toast) ,Toast.LENGTH_SHORT).show();
        }
    }
}