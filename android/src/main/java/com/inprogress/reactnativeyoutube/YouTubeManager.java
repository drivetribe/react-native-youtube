package com.inprogress.reactnativeyoutube;

import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;

class YouTubeManager extends ViewGroupManager<YouTubeView> {

    private static final String REACT_CLASS = "ReactYouTube";

    private static final String PROP_VIDEO_ID = "videoId";
    private static final String PROP_API_KEY = "apiKey";
    private static final String PROP_INLINE = "playsInline";
    private static final String PROP_SHOW_INFO = "showinfo";
    private static final String PROP_MODESTBRANDING = "modestbranding";
    private static final String PROP_CONTROLS = "controls";
    private static final String PROP_PLAY = "play";
    private static final String PROP_HIDDEN = "hidden";
    private static final String PROP_REL = "rel";
    private static final String PROP_LOOP = "loop";
    private static final String PROP_FULLSCREEN = "fs";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected YouTubeView createViewInstance(ThemedReactContext themedReactContext) {
        return new YouTubeView(themedReactContext);
    }

    @Override public void onDropViewInstance(YouTubeView view) {
        view.cleanupResources();
    }

    @Override
    public @Nullable Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                "error",
                MapBuilder.of("registrationName", "onYoutubeVideoError"),
                "ready",
                MapBuilder.of("registrationName", "onYoutubeVideoReady"),
                "state",
                MapBuilder.of("registrationName", "onYoutubeVideoChangeState"),
                "quality",
                MapBuilder.of("registrationName", "onYoutubeVideoChangeQuality")
        );
    }

    @ReactProp(name = PROP_VIDEO_ID)
    public void setPropVideoId(
            YouTubeView view, @Nullable String param) {
        //Log.e(PROP_VIDEO_ID,""+param);
        view.setVideoId(param);
    }

    @ReactProp(name = PROP_API_KEY)
    public void setApiKey(
            YouTubeView view, @Nullable String param) {
        //Log.e(PROP_API_KEY,""+param);
        view.setApiKey(param);
    }

    @ReactProp(name = PROP_PLAY)
    public void setPropPlay(
            YouTubeView view, @Nullable Boolean param) {
        Log.e(PROP_PLAY, "" + param);
        view.setPlay(param);
    }

    @ReactProp(name = PROP_HIDDEN)
    public void setPropHidden(
            YouTubeView view, @Nullable Boolean param) {
        //Log.e(PROP_HIDDEN,""+param);
        view.setHidden(param);
    }

    @ReactProp(name = PROP_INLINE)
    public void setPropInline(
            YouTubeView view, @Nullable Boolean param) {
        //Log.e(PROP_INLINE,""+param);
        view.setInline(param);
    }

    @ReactProp(name = PROP_REL)
    public void setPropRel(
            YouTubeView view, @Nullable Boolean param) {
        //Log.e(PROP_REL,""+param);
        view.setRelated(param);
    }

    @ReactProp(name = PROP_MODESTBRANDING)
    public void setPropModestbranding(
            YouTubeView view, @Nullable Boolean param) {
        //Log.e(PROP_MODESTBRANDING,""+param);
        view.setModestBranding(param);
    }

    @ReactProp(name = PROP_LOOP)
    public void setPropLoop(
            YouTubeView view, @Nullable Boolean param) {
        //Log.e(PROP_LOOP,""+param);
        view.setLoop(param);
    }

    @ReactProp(name = PROP_CONTROLS)
    public void setPropControls(
            YouTubeView view, @Nullable Integer param) {
        //Log.e(PROP_CONTROLS,""+param);
        view.setControls(param);
    }

    @ReactProp(name = PROP_SHOW_INFO)
    public void setPropShowInfo(
            YouTubeView view, @Nullable Boolean param) {
        //Log.e(PROP_SHOW_INFO,""+param);
        view.setShowInfo(param);
    }

    @ReactProp(name = PROP_FULLSCREEN)
    public void setPropFullscreen(
            YouTubeView view, @Nullable Boolean param) {
        //Log.e(PROP_FULLSCREEN,""+param);
        view.setFullscreen(param);
    }
}
