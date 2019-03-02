package com.github.nazdov.tripple_dot_loader_view

import org.junit.Assert.assertEquals
import org.junit.Test

class BouncerLoaderComputationTest {

    private lateinit var loaderComputation: BouncerLoaderComputation


    @Test
    fun testConvertFirst25DensityToPx() {
        loaderComputation = BouncerLoaderComputation(2.5)
        assertEquals(2.5, loaderComputation.dpToPX(1), 0.0001)
    }

    @Test
    fun testConvertSecond25DensityToPx() {
        loaderComputation = BouncerLoaderComputation(2.5)
        assertEquals(7.5, loaderComputation.dpToPX(3), 0.0001)
    }

    @Test
    fun testConvertFirst3DpiDensityToPx() {
        loaderComputation = BouncerLoaderComputation(3.0)
        assertEquals(6.0, loaderComputation.dpToPX(2), 0.0001)
    }

    @Test
    fun testConvertSecond3DpiDensityToPx() {
        loaderComputation = BouncerLoaderComputation(3.0)
        assertEquals(12.0, loaderComputation.dpToPX(4), 0.0001)
    }

    @Test
    fun testConvertFirst2DpiPxToDp() {
        loaderComputation = BouncerLoaderComputation(2.0)
        assertEquals(3.0, loaderComputation.pxToDp(6), 0.0001)
    }

    @Test
    fun testConvertFirst3DpiPxToDp() {
        loaderComputation = BouncerLoaderComputation(3.0)
        assertEquals(1.0, loaderComputation.pxToDp(3), 0.0001)
    }

}