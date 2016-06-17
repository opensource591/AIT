package com.ait.sumit.ait;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import eu.chainfire.libsuperuser.Shell;

public class boot_tools extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot_tools);
        Button newbutton = (Button)findViewById(R.id.backup);
        TextView newview=(TextView)findViewById(R.id.textView7);
        newview.setMovementMethod(new ScrollingMovementMethod());
        newbutton.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        TextView textView = (TextView)findViewById(R.id.boot_text);
                        textView.setText("Saved to sdcard/AIT/Boot.img");
                        Shell.SU.run("mkdir /sdcard/AIT");
                        Shell.SU.run("dd if=/dev/block/mmcblk0p8 of=sdcard/AIT/boot.img");

                    }

                }
        );
        Button flash = (Button)findViewById(R.id.flash);

        flash.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        TextView textView = (TextView)findViewById(R.id.boot_text);
                        textView.setText("Flashed boot.img from /sdcard/AIT/boot.img");
                        Shell.SU.run("mkdir /sdcard/AIT");
                        Shell.SU.run("dd if=sdcard/AIT/boot.img of=/dev/block/mmcblk0p8");

                    }

                }
        );
    }
}