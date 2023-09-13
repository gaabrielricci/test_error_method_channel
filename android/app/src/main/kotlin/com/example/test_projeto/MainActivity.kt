package com.example.test_projeto

import android.app.Activity
import android.webkit.WebView
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity : FlutterActivity() {

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        GeneratedPluginRegistrant.registerWith(flutterEngine)

        flutterEngine.dartExecutor.let {
            MethodChannel(it.binaryMessenger, "native_info_channel").setMethodCallHandler { call, result ->
                when (call.method) {
                    "user-agent" -> {
                        result.success(getuserAgent(activity))
                    }
                }
            }
        }
    }

    private fun getuserAgent(activity: Activity): String {
        return try {
            WebView(activity).settings.userAgentString
        } catch (e: Exception) {
            "failed_to_get_user_agent_native_android"
        }
    }
}
