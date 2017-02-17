package com.inprogress.reactnativeyoutube;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.google.android.youtube.player.YouTubePlayerFragment;

@SuppressLint("ViewConstructor")
public class YouTubeView extends FrameLayout {

    private YouTubePlayerController mYoutubeController;
    private YouTubePlayerFragment mYouTubePlayerFragment;

    public YouTubeView(ReactContext context) {
        super(context);
        init();
    }

    private ReactContext getReactContext() {
        return (ReactContext) getContext();
    }

    public void init() {
        ViewGroup.LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
        mYouTubePlayerFragment = YouTubePlayerFragment.newInstance();
        mYoutubeController = new YouTubePlayerController(this);
    }

    @Override
    protected void onAttachedToWindow() {
        Activity currentActivity = getReactContext().getCurrentActivity();
        if (currentActivity != null) {
            FragmentManager fragmentManager = getReactContext().getCurrentActivity().getFragmentManager();
            fragmentManager.beginTransaction().add(getId(), mYouTubePlayerFragment).commit();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        cleanupResources();
        super.onDetachedFromWindow();
    }

    public void seekTo(int second) {
        mYoutubeController.seekTo(second);
    }


    public void playerViewDidBecomeReady() {
        WritableMap event = Arguments.createMap();
        ReactContext reactContext = (ReactContext) getContext();
        event.putInt("target", getId());
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "ready", event);
    }


    public void didChangeToState(String param) {
        post(measureAndLayout);
        WritableMap event = Arguments.createMap();
        event.putString("state", param);
        event.putInt("target", getId());
        ReactContext reactContext = (ReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "state", event);
    }


    public void didChangeToQuality(String param) {
        WritableMap event = Arguments.createMap();
        event.putString("quality", param);
        event.putInt("target", getId());
        ReactContext reactContext = (ReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "quality", event);
    }


    public void didPlayTime(String current, String duration) {
        WritableMap event = Arguments.createMap();
        event.putString("currentTime", current);
        event.putString("duration", duration);
        event.putInt("target", getId());
        ReactContext reactContext = (ReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "progress", event);
    }


    public void receivedError(String param) {
        WritableMap event = Arguments.createMap();
        ReactContext reactContext = (ReactContext) getContext();
        event.putString("error", param);
        event.putInt("target", getId());
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "error", event);
    }


    public void setVideoId(String str) {
        mYoutubeController.setVideoId(str);
    }

    public void setInline(Boolean bool) {
        mYoutubeController.setPlayInline(bool);
    }

    public void setShowInfo(Boolean bool) {
        mYoutubeController.setShowInfo(bool);
    }

    public void setModestBranding(Boolean bool) {
        mYoutubeController.setModestBranding(bool);
    }

    public void setControls(Integer nb) {
        mYoutubeController.setControls(nb);
    }

    public void setPlay(Boolean bool) {
        mYoutubeController.setPlay(bool);
    }

    public void setHidden(Boolean bool) {
        mYoutubeController.setHidden(bool);
    }

    public void setApiKey(String apiKey) {
        try {
            mYouTubePlayerFragment.initialize(apiKey, mYoutubeController);
        } catch (Exception e) {
            receivedError(e.getMessage());
        }
    }

    public void setLoop(Boolean loop) {
        mYoutubeController.setLoop(loop);
    }

    public void setRelated(Boolean related) {
        mYoutubeController.setRelated(related);
    }

    public void setFullscreen(Boolean bool) {
        mYoutubeController.setFullscreen(bool);
    }

    private final Runnable measureAndLayout = new Runnable() {
        @Override
        public void run() {
            measure(
                    MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.EXACTLY));
            layout(getLeft(), getTop(), getRight(), getBottom());
        }
    };

    public void cleanupResources() {
        Activity currentActivity = getReactContext().getCurrentActivity();
        if (currentActivity != null) {
            FragmentManager fragmentManager = currentActivity.getFragmentManager();
            if (mYouTubePlayerFragment != null) {
                fragmentManager.beginTransaction().remove(mYouTubePlayerFragment).commit();
            }
        }
    }
}
