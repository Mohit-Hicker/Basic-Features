package com.m.nativeads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ColorDrawable colorDrawable = new ColorDrawable(Color.WHITE);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdLoader adLoader = new AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        NativeTemplateStyle styles = new
                                NativeTemplateStyle.Builder().withMainBackgroundColor(colorDrawable).build();
                        TemplateView template = findViewById(R.id.my_template);
                        template.setStyles(styles);
                        template.setNativeAd(nativeAd);
                        FrameLayout frameLayout = findViewById(R.id.frameLyt);
                        // Assumes that your ad layout is in a file call native_ad_layout.xml
                        // in the res/layout folder
                        NativeAdView adView = (NativeAdView) getLayoutInflater()
                                .inflate(R.layout.nativeads, null);
                        // This method sets the text, images and the native ad, etc into the ad
                        // view.
//                        populateNativeAdView(nativeAd, adView);
                        frameLayout.removeAllViews();
                        frameLayout.addView(adView);
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }
    private void displayNativeAd(ViewGroup parent, NativeAd ad) {

        // Inflate a layout and add it to the parent ViewGroup.
        LayoutInflater inflater = (LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        NativeAdView adView = (NativeAdView) inflater
                .inflate(R.layout.nativeads, parent);

        // Locate the view that will hold the headline, set its text, and call the
        // NativeAdView's setHeadlineView method to register it.
        TextView headlineView = adView.findViewById(R.id.ad_headline);
        headlineView.setText(ad.getHeadline());
        adView.setHeadlineView(headlineView);

        // Repeat the above process for the other assets in the NativeAd
        // using additional view objects (Buttons, ImageViews, etc).

        // If the app is using a MediaView, it should be
        // instantiated and passed to setMediaView. This view is a little different
        // in that the asset is populated automatically, so there's one less step.
//        MediaView mediaView = (MediaView) adView.findViewById(R.id.ad_media);
//        adView.setMediaView(mediaView);

        // Call the NativeAdView's setNativeAd method to register the
        // NativeAdObject.
        adView.setNativeAd(ad);

        // Ensure that the parent view doesn't already contain an ad view.
        parent.removeAllViews();

        // Place the AdView into the parent.
        parent.addView(adView);
    }
}