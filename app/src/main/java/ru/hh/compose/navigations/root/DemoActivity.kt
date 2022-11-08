package ru.hh.compose.navigations.root

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumble.appyx.core.integrationpoint.ActivityIntegrationPoint
import com.bumble.appyx.core.integrationpoint.IntegrationPointProvider
import ru.hh.compose.navigations.R

class DemoActivity : AppCompatActivity(R.layout.activity_demo_root), IntegrationPointProvider {

    override lateinit var appyxIntegrationPoint: ActivityIntegrationPoint

    fun createIntegrationPoint(savedInstanceState: Bundle?) =
        ActivityIntegrationPoint(
            activity = this,
            savedInstanceState = savedInstanceState
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appyxIntegrationPoint = createIntegrationPoint(savedInstanceState)
    }

    @Suppress("OVERRIDE_DEPRECATION")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        appyxIntegrationPoint.onActivityResult(requestCode, resultCode, data)
    }

    @Suppress("OVERRIDE_DEPRECATION")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        appyxIntegrationPoint.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        appyxIntegrationPoint.onSaveInstanceState(outState)
    }

}
