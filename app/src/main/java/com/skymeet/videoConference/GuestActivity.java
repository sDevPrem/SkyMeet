package com.skymeet.videoConference;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.skymeet.videoConference.databinding.ActivityGuestBinding;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class GuestActivity extends AppCompatActivity {


 ActivityGuestBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityGuestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
        setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);


        YoYo.with(Techniques.Landing).duration(1200).repeat(0).playOn( binding.videoCall);
        YoYo.with(Techniques.Landing).duration(1200).repeat(0).playOn( binding.guestModeBigView);
        YoYo.with(Techniques.FlipInX).duration(1200).repeat(3).playOn( binding.facebook);
        YoYo.with(Techniques.FlipInX).duration(1200).repeat(3).playOn( binding.linkedin);
        YoYo.with(Techniques.FlipInX).duration(1200).repeat(3).playOn( binding.instagram);
        YoYo.with(Techniques.FlipInX).duration(1200).repeat(3).playOn( binding.guestprofileAppInfo);
        YoYo.with(Techniques.Pulse).duration(1200).repeat(3).playOn( binding.followtv);
        YoYo.with(Techniques.Landing).duration(1200).repeat(0).playOn( binding.joinBtn);
        YoYo.with(Techniques.Wobble).duration(1200).repeat(0).playOn( binding.shareCode);
        YoYo.with(Techniques.Wobble).duration(1200).repeat(0).playOn( binding.backToSignIn);

        URL serverURL = null;
        try {
            serverURL = new URL("https://meet.jit.si");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        JitsiMeetConferenceOptions defaultOptions
                = new JitsiMeetConferenceOptions.Builder()
                .setServerURL(serverURL)
                .build();

        JitsiMeet.setDefaultConferenceOptions(defaultOptions);




        binding.joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);

                if (TextUtils.isEmpty( binding.codeBox.getText().toString())) {
                    binding.codeBox.setError("Meeting Code cannot be empty");
                    binding.codeBox.requestFocus();
                    YoYo.with(Techniques.Shake).duration(1200).repeat(0).playOn( binding.joinBtn);
                } else {
                    JitsiMeetConferenceOptions options
                            = new JitsiMeetConferenceOptions.Builder()
                            .setRoom( binding.codeBox.getText().toString().trim())
                            .setFeatureFlag("welcomepage.enabled", false)
                            .setFeatureFlag("live-streaming.enabled",false)
                            .setFeatureFlag("invite.enabled",false)
                            .setFeatureFlag("video-share.enabled", false)
                            .setFeatureFlag("overflow-menu.enabled", true)
                            .setFeatureFlag("fullscreen.enabled", true)
                            .build();
                    JitsiMeetActivity.launch(GuestActivity.this, options);
                }
            }
        });

        binding.guestprofileAppInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                startActivity(new Intent(GuestActivity.this, GuestInfoActivity.class));
            }
        });

        binding.backToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                startActivity(new Intent(GuestActivity.this, SignInActivity.class));
            }
        });

        binding.shareCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                if (TextUtils.isEmpty( binding.codeBox.getText().toString())) {
//                    codeBox.setError("Enter Meeting Code before sharing!");
//                    codeBox.requestFocus();
                    YoYo.with(Techniques.Wave).duration(1200).repeat(0).playOn( binding.shareCode);
                } else {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = "To join the meeting on SkyMeet Conference, please use\n\n"
                            + "Code: " +  binding.codeBox.getText().toString().trim() + "\n\nDownload SkyMeet:\n\n"
                            + "Android: https://play.google.com/store/apps/details?id=com.skymeet.videoConference\n\n"
                            + "iOS: An apple a day keeps a doctor away but visiting a lady doctor everyday could be your spouse someday.";
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share Code via"));
                }

            }
        });

        binding.videoCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Wave).duration(1200).repeat(0).playOn( binding.videoCall);
            }
        });

        binding.facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
//                Profile/Page opens up on chrome, not on the app
                String YourPageURL = "https://www.facebook.com/skymeet.conference/";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(YourPageURL));
                startActivity(browserIntent);
            }
        });

        binding.linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                String YourPageURL = "https://www.linkedin.com/company/skymeet.conference/";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(YourPageURL));
                startActivity(browserIntent);

            }
        });

        binding.instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                String YourPageURL = "https://www.instagram.com/skymeet.conference/";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(YourPageURL));
                startActivity(browserIntent);

            }
        });
    }

    private final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.exit_guest)
                .setTitle("Exit App")
                .setMessage("Do you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNeutralButton("Rate App", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String YourPageURL = "https://play.google.com/store/apps/details?id=com.skymeet.videoConference";
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(YourPageURL));
                        startActivity(browserIntent);
                    }
                })
                .show();

    }

}