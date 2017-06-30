package com.playseeds.android.sdk.new_api.interstitials;

import android.support.annotation.Nullable;

/**
 * Represents the wrapper for the interstitial with info data.
 *
 * @see InterstitialListener
 */
public class SeedsInterstitial {

    public static final String NO_PRICE = "";
    public static final String NO_CONTEXT = "";

    /**
     * Id of the interstitial
     */
    private String interstitialId;

    /**
     * Context, that represent the current interstitial.
     * This field is {@link SeedsInterstitial#NO_CONTEXT} by default.
     */
    @Nullable
    private String context = NO_CONTEXT;

    /**
     * Specified price for this interstitial.
     * This field is {@link SeedsInterstitial#NO_PRICE} by default.
     */
    private String price = NO_PRICE;

    public SeedsInterstitial(String interstitialId) {
        this.interstitialId = interstitialId;
    }

    public SeedsInterstitial(String interstitialId, @Nullable String price) {

        this.interstitialId = interstitialId;
        this.price = price == null ? NO_PRICE : price;
    }

    public SeedsInterstitial(String interstitialId, @Nullable String context, @Nullable String price) {

        this.interstitialId = interstitialId;
        this.context = context == null ? NO_CONTEXT : context;
        this.price = price == null ? NO_PRICE : price;
    }

    /**
     * Returns the interstitialId of the interstitial.
     *
     * @return the interstitialId of the interstitial.
     */
    public String getInterstitialId() {
        return interstitialId;
    }

    /**
     * Returns the context of the interstitial. Might be null.
     *
     * @return the context of the interstitial.
     */
    @Nullable
    public String getContext() {
        return context;
    }

    /**
     * Returns the price of the interstitial. Will return {@link SeedsInterstitial#NO_PRICE} if price
     * isn't specified.
     *
     * @return the price of the interstitial.
     */
    public String getPrice() {
        return price;
    }
}
