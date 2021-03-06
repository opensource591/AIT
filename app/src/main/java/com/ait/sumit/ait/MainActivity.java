package com.ait.sumit.ait;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import java.io.DataOutputStream;
import java.io.IOException;

import Fragments.MainFragment;
import eu.chainfire.libsuperuser.Shell;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //strings
    String asset = "file:///android_asset/";
    String contact= "https://foxerweb.wordpress.com/contact/";
    String rom="http://google";
    String recovery="http://google";
    String kernel = "http://google";
    // Get the HTML file name
    String unrooted= "unrooted.html";
    String home = "home.html";
    String ErrorFile = "error.html";
    String file = asset+home;
    String error = asset+ErrorFile;
    boolean suAvailable = false;
    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//Floating button starts
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast when action started above
                Context context = getApplicationContext();
                CharSequence text = "Fab clicked!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                //Toast was shown
            }
        }); //floating button ends

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                     } else {
            //TODO when users deny
                            }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        } else {
            //TODO when users deny
        }
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new MainFragment()).commit();

    }




    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        WebView mywebview = (WebView) this.findViewById(R.id.webView);
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mywebview.canGoBack()) {
            mywebview.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        WebView mywebview = (WebView) this.findViewById(R.id.webView);
        mywebview.getSettings() .setJavaScriptCanOpenWindowsAutomatically(true);
        mywebview.addJavascriptInterface(new WebAppInterface(this), "Android");
        mywebview.loadUrl(file);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        suAvailable = Shell.SU.available();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reboot_recovery) {

            if (suAvailable) {

                Shell.SU.run("reboot recovery");
            }
            else
            {
                Toast.makeText(getApplicationContext(),R.string.not_rooted,Toast.LENGTH_LONG).show();

            }

            }
        if (id == R.id.action_reboot) {
            if (suAvailable) {
                Shell.SU.run("reboot");

            }
            else {
                Toast.makeText(getApplicationContext(), R.string.not_rooted, Toast.LENGTH_LONG).show();

            }
        }

        if (id == R.id.action_power) {
            if (suAvailable) {
                Shell.SU.run("reboot -p");

            }
            else {
                Toast.makeText(getApplicationContext(), R.string.not_rooted, Toast.LENGTH_LONG).show();

            }
        }
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, settings.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fm = getFragmentManager();
        int id = item.getItemId();

        if (id == R.id.nav_rom)
        {

            final WebView mywebview = (WebView) this.findViewById(R.id.webView);
            mywebview.getSettings() .setJavaScriptCanOpenWindowsAutomatically(true);
            mywebview.addJavascriptInterface(new WebAppInterface(this), "Android");
            mywebview.loadUrl(rom);
            mywebview.setWebViewClient(new WebViewClient() {

                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    mywebview.loadUrl(error);

                }
            });
        }


        else if (id == R.id.nav_recovery)
        {
            final WebView mywebview = (WebView) this.findViewById(R.id.webView);
            mywebview.getSettings() .setJavaScriptCanOpenWindowsAutomatically(true);
            mywebview.addJavascriptInterface(new WebAppInterface(this), "Android");
            mywebview.loadUrl(recovery);
            mywebview.setWebViewClient(new WebViewClient() {

                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    mywebview.loadUrl(error);

                }
            });
        }
        else if (id == R.id.nav_kernel)
        {
            final WebView mywebview = (WebView) this.findViewById(R.id.webView);
            mywebview.getSettings() .setJavaScriptCanOpenWindowsAutomatically(true);
            mywebview.addJavascriptInterface(new WebAppInterface(this), "Android");
            mywebview.loadUrl(kernel);
            mywebview.setWebViewClient(new WebViewClient() {

                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    mywebview.loadUrl(error);

                }
            });
        }

        else if (id == R.id.nav_contact)
        {
            final WebView mywebview = (WebView) this.findViewById(R.id.webView);
            mywebview.getSettings() .setJavaScriptCanOpenWindowsAutomatically(true);
            mywebview.addJavascriptInterface(new WebAppInterface(this), "Android");
            mywebview.loadUrl(contact);
            mywebview.setWebViewClient(new WebViewClient() {

                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    mywebview.loadUrl(error);

                }
            });
        }
        else if (id == R.id.nav_about)
        {

            Intent intent = new Intent(this, about_us.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_bootimg)
        {
            Intent intent = new Intent(this, boot_tools.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_recoveryimg)
        {
            Intent intent = new Intent(this, recovery_tools.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_terminal)
        {
            Intent intent = new Intent(this, terminal.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_mod)
        {
            Intent intent = new Intent(this, mods.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
