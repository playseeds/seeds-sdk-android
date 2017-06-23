package com.playseeds.android.sdk.new_api.interstitials;

import android.support.annotation.Nullable;

/**
 * Represents the wrapper for the interstitial with info data.
 *
 * @see InterstitialListener
 */
class SeedsInterstitial {

    public static final int NO_PRICE = -1;

    /**
     * Id of the interstitial
     */
    private String interstitialId;

    /**
     * Context, that represent the current interstitial, might be null.
     */
    @Nullable
    private String context;

    /**
     * Specified price for this interstitial. This field if {@link SeedsInterstitial#NO_PRICE} by default.
     */
    private double price = NO_PRICE;

    public SeedsInterstitial(String interstitialId, double price) {

        this.interstitialId = interstitialId;
        this.price = price;
    }

    public SeedsInterstitial(String interstitialId, @Nullable String context, double price) {

        this.interstitialId = interstitialId;
        this.context = context;
        this.price = price;
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
    public double getPrice() {
        return price;
    }
}
