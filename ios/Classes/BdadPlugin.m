#import "BdadPlugin.h"
#if __has_include(<bdad/bdad-Swift.h>)
#import <bdad/bdad-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "bdad-Swift.h"
#endif

@implementation BdadPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftBdadPlugin registerWithRegistrar:registrar];
}
@end
