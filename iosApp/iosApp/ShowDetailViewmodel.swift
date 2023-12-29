//
//  ShowDetailViewmodel.swift
//  iosApp
//
//  Created by Nicolas Grantham on 12/18/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import Combine
import shared
import KMPNativeCoroutinesAsync

private let log = koin.loggerWithTag(tag: "ShowDetailScreen")

@MainActor
class ShowDetailViewModel : ObservableObject {
    @Published var show: Show? = nil

    private let repository: ShowRepository
    init(repository: ShowRepository) {
        self.repository = repository
    }
    
    func getShow(showId: String) async {
        do {
            let show = try await asyncFunction(for: repository.getShow(id: showId))
            self.show = show
            log.d(throwable: nil, tag: "ShowDetailViewModel", message: { "Fetched show \(showId) from viewmodel: \(show)"})
        } catch {
            log.e(messageString: "Failed with error: \(error)", throwable: nil, tag: "ShowDetailViewModel")
        }
    }
}
