package com.example.benchmark

import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SwipePlansBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()


    private fun setupTest(): MacrobenchmarkScope.() -> Unit = {
        pressHome()
        startActivityAndWait()
    }

    @Test
    fun swipePlans() {
        benchmarkRule.measureRepeated(
            packageName = Constants.PACKAGE_NAME,
            metrics = listOf(FrameTimingMetric()),
            iterations = Constants.ITERATION_COUNT,
            startupMode = StartupMode.COLD,
            setupBlock = setupTest()
        ) {
            for (i in 0 until 3) {
                device.swipeRight()
            }
            for (i in 0 until 3) {
                device.swipeLeft()
            }
        }
    }

    private fun UiDevice.swipeRight() {
        performActionAndWait(
            {
                drag(
                    (displayWidth / 1.5).toInt(),
                    displayHeight / 2,
                    0,
                    displayHeight / 2,
                    10
                )
            },
            Until.scrollFinished(Direction.RIGHT),
            1000
        )
    }

    private fun UiDevice.swipeLeft() {
        performActionAndWait(
            {
                drag(
                    0,
                    displayHeight / 2,
                    (displayWidth / 1.5).toInt(),
                    displayHeight / 2,
                    10
                )
            },
            Until.scrollFinished(Direction.RIGHT),
            1000
        )
    }
}