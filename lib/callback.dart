typedef OnFail = void Function(dynamic message);
typedef OnSkip = void Function();
typedef OnSuccess = void Function();

class CallBack {
  OnSkip? onSkip;
  OnFail? onFail;
  OnSuccess? onSuccess;
  CallBack({this.onSkip, this.onFail, this.onSuccess});
}
