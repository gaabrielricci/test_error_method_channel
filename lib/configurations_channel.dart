import 'package:flutter/services.dart';

class ConfigsChannel {
  static final ConfigsChannel _instance = ConfigsChannel.internal();

  factory ConfigsChannel() => _instance;

  ConfigsChannel.internal();

  static const _methodChannel = MethodChannel('native_info_channel');

  Future<String> getUserAgent() async {
    return await _methodChannel.invokeMethod("user-agent");
  }
}
