import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        DiHelperKt.doInitKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
