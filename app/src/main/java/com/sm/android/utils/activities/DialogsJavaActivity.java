package com.sm.android.utils.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sm.android.utils.R;
import com.sm.android.utils.databinding.ActivityDialogsBinding;
import com.sm.android.utils.databinding.ActivityDialogsJavaBinding;

public class DialogsJavaActivity extends AppCompatActivity  {

    private ActivityDialogsJavaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDialogsJavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnDialog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                exit_dialog1();

            }
        });

        binding.btnDialog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_rating_dialog2();

            }
        });
    }

    public void exit_dialog1() {
        final Dialog dialog;
        dialog = new Dialog(DialogsJavaActivity.this);
        try {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        } catch (NullPointerException ne) {
        } catch (Exception e) {
        }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_exit2);
        Button yes = dialog.findViewById(R.id.yes);
        Button no = dialog.findViewById(R.id.no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
//                finishAffinity();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void show_rating_dialog2() {
        final Dialog dialog;
        dialog = new Dialog(DialogsJavaActivity.this);
        try {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        } catch (NullPointerException ne) {
        } catch (Exception e) {
        }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_java_rateus);
        Button submit = dialog.findViewById(R.id.submit);
        ImageView cancel = dialog.findViewById(R.id.exit_rateUs);
        final RatingBar rating_bar = dialog.findViewById(R.id.rating_bar);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        rating_bar.setNumStars(5);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rating_bar.getRating() < 3) {
                    if (rating_bar.getRating() < 1) {
                        Toast.makeText(DialogsJavaActivity.this, "Rating <3", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    dialog.dismiss();
                } else {
                    Toast.makeText(DialogsJavaActivity.this, "Rating >4 ", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }



            }
        });
        dialog.show();
    }

}