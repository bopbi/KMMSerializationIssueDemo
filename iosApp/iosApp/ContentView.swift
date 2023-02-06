import SwiftUI
import shared

struct ContentView: View {
    let greet = UseCase().execute()

	var body: some View {
        Text(greet.title)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
