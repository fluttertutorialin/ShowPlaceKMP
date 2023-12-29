//
//  ShowList.swift
//  iosApp
//
//  Created by Nicolas Grantham on 12/18/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ShowList: View {
    @StateObject var viewModel = ShowsViewModel(repository: ShowRepository())

    var body: some View {
        NavigationStack {
            if viewModel.isLoading {
                ProgressView()
                    .frame(maxHeight: .infinity, alignment: .bottom)
            }
            else if (viewModel.shows.isEmpty) {
                VStack {
                            Text("Error:").font(.headline)
                       }
            }
            List(viewModel.shows, id: \.self) { show in
                ShowView(show:show)
                    .background(
                        NavigationLink(destination: ShowDetail(showId: show.id)) {
                        }.opacity(0)
                    )
                .listRowSeparator(.hidden)
                .listRowBackground(Color.black)
            }
            .listStyle(.inset)
            .scrollContentBackground(.hidden)
            .ignoresSafeArea(edges: .bottom)
            .task {
                await viewModel.getShows()
            }
        }
        .frame(maxWidth: .infinity)
        .toolbarBackground(.visible, for: .navigationBar)
    }
}

#Preview {
    ShowList()
}
