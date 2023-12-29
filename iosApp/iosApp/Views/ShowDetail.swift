//
//  ShowDetail.swift
//  iosApp
//
//  Created by Nicolas Grantham on 12/18/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ShowDetail: View {
    @StateObject var viewModel = ShowDetailViewModel(repository: ShowRepository())
    var showId: String

    var body: some View {
        VStack(alignment: .leading) {
            Text("\(viewModel.show?.venue.name ?? "TDB") • \(viewModel.show?.time ?? "TDB")")
                .font(.system(size: 20).weight(.bold))
                .foregroundColor(Color(red: 0.88, green: 0.88, blue: 0.88))
                .padding(EdgeInsets(top: 28, leading: 28, bottom: 16, trailing: 20))
            
            Text("\(viewModel.show?.price ?? "TDB")")
                .font(.system(size: 16).weight(.bold))
                .foregroundColor(Color(red: 0.49, green: 0.74, blue: 0.72))
                .padding(EdgeInsets(top: 1, leading: 28, bottom: 0, trailing: 20))
            
            Text(viewModel.show?.date ?? "TDB")
                .font(.system(size: 16).weight(.bold))
                .foregroundColor(Color(red: 0.49, green: 0.74, blue: 0.73))
                .padding(EdgeInsets(top: 1, leading: 28, bottom: 0, trailing: 20))
            
            Text("\(viewModel.show?.lineup ?? "TDB")")
                .font(.system(size: 16).weight(.bold))
                .foregroundColor(Color(red: 0.74, green: 0.53, blue: 1))
                .padding(EdgeInsets(top: 1, leading: 28, bottom: 24, trailing: 20))
            
            Divider()
                .frame(height: 1)
                .overlay(Color(red: 0.33, green: 0.31, blue: 0.31))
                .padding(.horizontal, 14)
            
            ShowDetailRowItem(title: "Age", bodyText: "\(viewModel.show?.venue.ageLimit ?? "TDB")")
            ShowDetailRowItem(title: "Capacity", bodyText: "\(viewModel.show?.venue.capacity ?? "TDB")")
            ShowDetailRowItem(title: "Accessibility", bodyText: "\(viewModel.show?.venue.accessibility ?? "TDB")")
            
            Divider()
                .frame(height: 1)
                .overlay(Color(red: 0.33, green: 0.31, blue: 0.31))
                .padding(EdgeInsets(top: 24, leading: 14, bottom: 0, trailing: 14))
            
            ShowDetailRowItem(title: "Contact", bodyText: "\(viewModel.show?.venue.url ?? "TDB")", showBottomPadding: true, showPurple: true)
        }
        .frame(
            maxWidth: .infinity,
            alignment: .topLeading
        )
        .background(
            Rectangle()
                .foregroundColor(.clear)
                .background(Color(red: 0.13, green: 0.13, blue: 0.13))
                .cornerRadius(20)
        )
        .task {
            await viewModel.getShow(showId: showId)
        }
        .navigationTitle(Text("Event Details"))
        .toolbarColorScheme(.dark, for: .navigationBar)
        .toolbarBackground(Color.black,for: .navigationBar)
        .toolbarBackground(.visible, for: .navigationBar)
    }
}

#Preview {
    ShowDetail(showId: "1")
}

struct ShowDetailRowItem: View {
    var title: String
    var bodyText: String
    var showBottomPadding: Bool = false
    var showPurple: Bool = false
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(title)
                .font(.system(size: 13).weight(.bold))
              .foregroundColor(showPurple ? Color(red: 0.74, green: 0.53, blue: 1) : Color(red: 0.49, green: 0.74, blue: 0.73))
            
            Text(bodyText)
                .font(.system(size: 13).weight(.bold))
              .foregroundColor(Color(red: 0.88, green: 0.88, blue: 0.88))
              .padding(.bottom, showBottomPadding ? 30 : 0)
        }
        .padding(EdgeInsets(top: 24, leading: 28, bottom: 0, trailing: 20))
        .frame(alignment: .leading)
    }
}
	
