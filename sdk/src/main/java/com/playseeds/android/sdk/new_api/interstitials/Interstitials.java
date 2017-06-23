package com.playseeds.android.sdk.new_api.interstitials;

import android.support.annotation.Nullable;

/**
 * An interface, that is used in the {@link com.playseeds.android.sdk.Seeds} to wrap the block of the
 * functionality, that is provided by the Seeds SDK.
 */
public interface Interstitials {

    /**
     * @see Interstitials#fetch(String, String, double, InterstitialListener) )
     */
    void fetch(String interstitialId, String context, InterstitialListener listener);

    /**
     * Downloads the required interstitial.
     *
     * @param interstitialId the interstitialId of the interstitial.
     * @param context the context for the interstitial. Might be null.
     * @param manualPrice the custom price for the requested interstitial.
     * @param listener the instance of {@link InterstitialListener}
     * or {@link InterstitialListenerAdapter} to handle the results. Might be null.
     */
    void fetch(String interstitialId, @Nullable String context, double manualPrice, @Nullable InterstitialListener listener);

    /**
     * Shows the requested interstitial. At first, will look for the interstitialId and then for the context in
     * the downloaded interstitials.
     *
     * @param interstitialId the interstitialId of the interstitial to show.
     * @param context the context of the interstitial to show.
     */
    void show(String interstitialId, String context);

    /**
     * Check by interstitialId if the requested interstitial is already loaded.
     *
     * @param interstitialId the interstitialId to look for.
     * @return true if the interstitial is loaded, false otherwise.
     */
    boolean isLoaded(String interstitialId);

    /**
     * Adds the specified {@link InterstitialListener} for the specified interstitialId.
     *
     * @param interstitialId the interstitialId to add the listener for.
     * @param listener the listener that is being added.
     */
    void addListener(String interstitialId, InterstitialListener listener);

    /**
     * Clears the specified {@link InterstitialListener} from the callback lists.
     *
     * @param listener to be cleared.
     */
    void removeListener(InterstitialListener listener);

    /**
     * Clears all {@link InterstitialListener}.
     */
    void clearListeners();
}
