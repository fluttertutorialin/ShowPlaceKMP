import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        let koinApplication = KoinKt.doInitKoin()
        _koin = koinApplication.koin
    }
    
	var body: some Scene {
		WindowGroup {
            ContentView()
                .preferredColorScheme(ColorScheme.dark)
		}
	}
}

private var _koin: Koin_coreKoin?
var koin: Koin_coreKoin {
    return _koin!
}
