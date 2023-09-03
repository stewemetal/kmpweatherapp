import SwiftUI
import shared

struct ContentView: View {
    let weatherRepository = WeatherRepositoryImpl()
    
    @State private var text: String = "Loading"
    
    var body: some View {
            Text(text)
                .task{
                    let weather = try? await weatherRepository.getWeather()
                    text = weather?.sys.country ?? "Failed"
                }
        }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
