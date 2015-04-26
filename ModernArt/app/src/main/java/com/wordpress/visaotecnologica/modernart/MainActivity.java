/**
 * Copyright 2015 André Almeida Maldonado
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wordpress.visaotecnologica.modernart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

// TODO: Layout for different screens

/**
 * Shows a set of color changeable rectangles.
 * This app was created as a part of "Programming Mobile Apps with Android" course.
 * More information about the course in coursera.org
 */
public class MainActivity extends ActionBarActivity {

    /** Sets the action for seekbar */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create variables to manipulate views
        SeekBar mSeekBar = (SeekBar)findViewById(R.id.seekBar);
        final TextView mRect1_1 = (TextView)findViewById(R.id.rect1_1);
        final TextView mRect1_2 = (TextView)findViewById(R.id.rect1_2);
        final TextView mRect2_1 = (TextView)findViewById(R.id.rect2_1);
        final TextView mRect2_3 = (TextView)findViewById(R.id.rect2_3);

        // Make the color change when the user slides the seekbar
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mRect1_1.setBackgroundColor(colorChange(getResources().getColor(R.color.peeper),
                        getResources().getColor(R.color.chalk), progress));
                mRect1_2.setBackgroundColor(colorChange(getResources().getColor(R.color.chalk),
                        getResources().getColor(R.color.smalltown), progress));
                mRect2_1.setBackgroundColor(colorChange(getResources().getColor(R.color.smalltown),
                        getResources().getColor(R.color.demotron), progress));
                mRect2_3.setBackgroundColor(colorChange(getResources().getColor(R.color.demotron),
                        getResources().getColor(R.color.peeper), progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // TODO: refactor this method
    /**
     * Creates the More Information menu
     * In this menu the user can visit moma.org
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_moreinfo) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.more_information_title);
            builder.setMessage(R.string.more_information_message);
            builder.setNegativeButton(R.string.more_information_button_not_now,
                    new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton(R.string.more_information_button_visit,
                    new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(getResources().getString(R.string.more_information_url)));
                    startActivity(intent);
                }
            });
            builder.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // TODO: Move this code to a better place
    /**
     * Returns the color intersection between two colors based on a 0 to 100 scale
     */
    private int colorChange(int color1, int color2, int progress) {
        int red, green, blue; // rgb components
        int deltaR, deltaG, deltaB; // change in rgb components

        deltaR = Color.red(color2) - Color.red(color1);
        deltaG = Color.green(color2) - Color.green(color1);
        deltaB = Color.blue(color2) - Color.blue(color1);

        red = Color.red(color1) + (deltaR * progress / 100);
        green = Color.green(color1) + (deltaG * progress / 100);
        blue = Color.blue(color1) + (deltaB * progress / 100);

        return Color.rgb(red, green, blue);
    }
}
