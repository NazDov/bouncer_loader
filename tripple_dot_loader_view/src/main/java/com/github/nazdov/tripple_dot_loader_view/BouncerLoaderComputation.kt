package com.github.nazdov.tripple_dot_loader_view

/**
 * provides conversion from DP to PX based on
 * the device dpi value
 */
class BouncerLoaderComputation constructor(private val dpi: Double) {

    /**
     * converts dp to px based on device dpi
     * @see BouncerLoaderComputation.dpi
     */
    fun dpToPX(dp: Int): Double {
        return dp * dpi
    }

    fun pxToDp(px: Int): Double {
        return px / dpi
    }

}
