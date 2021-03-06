package com.demo.bottomnativation;



        import android.graphics.Color;
        import android.graphics.drawable.Drawable;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.design.internal.BottomNavigationItemView;
        import android.support.design.internal.BottomNavigationMenuView;
        import android.support.design.widget.BottomNavigationView;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentTransaction;
        import android.support.v4.content.ContextCompat;
        import android.support.v4.content.res.ResourcesCompat;
        import android.support.v4.graphics.drawable.DrawableCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.view.LayoutInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        BottomNavigationMenuView bottomNavigationMenuView =
                (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(1);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;

        View badge = LayoutInflater.from(this)
                .inflate(R.layout.notification_badge, bottomNavigationMenuView, false);

        TextView lTextCount= badge.findViewById(R.id.notifications);
        lTextCount.setText("3");
        itemView.addView(badge);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                item.setIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_add_alert_black_24dp));
                                selectedFragment = FragmentOne.newInstance();
                                break;
                            case R.id.action_item2:
                                selectedFragment = FragmentTwo.newInstance();
                                break;

                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, FragmentOne.newInstance());
        transaction.commit();

        //Used to select an item programmatically
        //bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }
}