//
//  ShowsViewModel.swift
//  iosApp
//
//  Created by Nicolas Grantham on 12/9/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import Combine
import shared
import KMPNativeCoroutinesAsync

private let log = koin.loggerWithTag(tag: "ShowListScreen")

@MainActor
class ShowsViewModel : ObservableObject {
    @Published var isLoading: Bool = false
    @Published var shows = [Show]()
    
    private let repository: ShowRepository

    init(repository: ShowRepository) {
        self.repository = repository
    }
    
    func getShows() async {
        isLoading = true
        do {
            let shows =  try await asyncFunction(for: repository.getShows())
            log.d(throwable: nil, tag: "ShowsViewModel", message: { "Fetched shows from viewmodel: \(shows)"})
            self.isLoading = false
            self.shows = shows
        } catch {
            self.isLoading = false
            log.e(messageString: "Failed with error: \(error)", throwable: nil, tag: "ShowsViewModel")
        }
    }
}
