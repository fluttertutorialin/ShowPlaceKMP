//
//  ShowView.swift
//  iosApp
//
//  Created by Nicolas Grantham on 12/18/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ShowView: View {
    let show: Show

    var body: some View {
        VStack(alignment: .leading) {
            Text("\(show.venue.name) • \(show.time ?? "tbd")")
                .font(.system(size: 18).weight(.bold))
              .foregroundColor(Color(red: 0.88, green: 0.88, blue: 0.88))
              .padding(EdgeInsets(top: 24, leading: 20, bottom: 16, trailing: 20))
            
            Text("\(show.price)")
                .font(.system(size: 13).weight(.bold))
              .foregroundColor(Color(red: 0.49, green: 0.74, blue: 0.72))
              .padding(EdgeInsets(top: 1, leading: 20, bottom: 0, trailing: 0))
            
            Text(show.date)
                .font(.system(size: 13).weight(.bold))
              .foregroundColor(Color(red: 0.49, green: 0.74, blue: 0.73))
              .padding(EdgeInsets(top: 1, leading: 20, bottom: 0, trailing: 0))
            
            Text("\(show.lineup)")
                .font(.system(size: 13).weight(.bold))
              .foregroundColor(Color(red: 0.74, green: 0.53, blue: 1))
              .padding(EdgeInsets(top: 1, leading: 20, bottom: 24, trailing: 28))
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
        .navigationTitle(Text("Shows This Month"))
        .toolbarColorScheme(.dark, for: .navigationBar)
        .toolbarBackground(Color.black,for: .navigationBar)
        .toolbarBackground(.visible, for: .navigationBar)
    }
  }

//#Preview {
//    ShowView()
//}
