package com.appshow.bdad.splash

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.baidu.mobads.sdk.api.*
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView

internal class SplashView(context: Context, var messenger: BinaryMessenger?, id: Int, creationParams: Map<String?, Any?>?) : PlatformView {
    private val TAG = "RewardVideoAd"
    private var mExpressContainer: FrameLayout? = null
    private var channel : MethodChannel?
    private val mCodeId: String?
    var mContext: Context? = null
    override fun getView(): View {
        return mExpressContainer!!
    }

    override fun dispose() {}

    init {
        mCodeId = creationParams?.get("androidCodeId") as String?
        mExpressContainer = FrameLayout(context)
        mContext=context
        channel = MethodChannel(messenger, "com.miles.baiduad/SplashAdView"+"_"+id)
        loadSplashAd()
    }
    private  fun loadSplashAd(){
        val bdAdConfig = BDAdConfig.Builder()
                // 2、应用在mssp平台申请到的appsid，和包名一一对应，此处设置等同于在AndroidManifest.xml里面设置
                .setAppsid("ae5dfabe") // 3、设置下载弹窗的类型和按钮动效样式，可选
                .build(mContext)
        bdAdConfig.init()
        MobadsPermissionSettings.setPermissionReadDeviceID(true);
        MobadsPermissionSettings.setPermissionLocation(true);
        MobadsPermissionSettings.setPermissionStorage(true);
        MobadsPermissionSettings.setPermissionAppList(true);

        var splashAd: SplashAd? = null

        // 默认请求https广告，若需要请求http广告，请设置AdSettings.setSupportHttps为false
        //         AdSettings.setSupportHttps(false);

        val listener: SplashInteractionListener = object : SplashInteractionListener {
            override fun onLpClosed() {

            }

            override fun onAdDismissed() {
                Log.d(TAG, "onLpClosed: "+"关闭。。。。。")
                channel?.invokeMethod("OnSkip","")
            }

            override fun onADLoaded() {
                Log.d(TAG, "onADLoaded: 。。。。。")
                channel?.invokeMethod("OnSuccess","")
            }

            override fun onAdFailed(message: String) {
                Log.d(TAG, "onAdFailed: "+message)
                channel?.invokeMethod("OnFail",message)
            }

            override fun onAdPresent() {

            }

            override fun onAdClick() {

                // 设置开屏可接受点击时，该回调可用
            }

            override fun onAdCacheSuccess() {

            }

            override fun onAdCacheFailed() {

            }
        }

        // 如果开屏需要load广告和show广告分开，请参考类RSplashManagerActivity的写法
        // 如果需要修改开屏超时时间、隐藏工信部下载整改展示，请设置下面代码;
        val parameters = RequestParameters.Builder()
        // sdk内部默认超时时间为4200，单位：毫秒
        parameters.addExtra(SplashAd.KEY_TIMEOUT, "4200")
        // 是否限制点击区域，默认不限制
        parameters.addExtra(SplashAd.KEY_LIMIT_REGION_CLICK, "false")
        // 是否展示点击引导按钮，默认不展示，若设置可限制点击区域，则此选项默认打开
        parameters.addExtra(SplashAd.KEY_DISPLAY_CLICK_REGION, "true")
        // 用户点击开屏下载类广告时，是否弹出Dialog
        // 此选项设置为true的情况下，会覆盖掉 {SplashAd.KEY_DISPLAY_DOWNLOADINFO} 的设置
        parameters.addExtra(SplashAd.KEY_POPDIALOG_DOWNLOAD, "true")
        splashAd = SplashAd(mContext, "7788269", parameters.build(), listener)
        splashAd.loadAndShow(mExpressContainer)
    }

}