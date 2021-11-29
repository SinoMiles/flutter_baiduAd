import 'dart:async';

import 'package:bdad/SplashAdView.dart';
import 'package:bdad/callback.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';

class Bdad {
  static const MethodChannel _channel = const MethodChannel('bdad');

  static Future<String?> get platformVersion async {
    final String? version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Widget splashAdView(
      {required String androidCodeId, CallBack? callBack}) {
    return SplashAdView(
      androidCodeId: androidCodeId,
      callBack: callBack,
    );
  }
}
