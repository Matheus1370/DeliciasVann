import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { BannerComponent } from './banner/banner.component';
import {
  ProductListComponent,
  Item,
} from './product-list/product-list.component';
import { VSeparatorComponent } from './vseparator/vseparator.component';
import { FooterComponent } from './footer/footer.component';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    HeaderComponent,
    BannerComponent,
    ProductListComponent,
    VSeparatorComponent,
    FooterComponent,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  mostSoldTitle = 'Mais Comprados';
  mostSoldItems: Item[] = [
    {
      id: 1,
      name: 'Bolo de Chocolate',
      price: 80.0,
      imageUrl: '/product-images/unsplash_kPxsqUGneXQ.png',
    },
    {
      id: 2,
      name: 'Bolo de Morango',
      price: 80.0,
      imageUrl: '/product-images/unsplash__nAVO0UGt2A.png',
    },
    {
      id: 3,
      name: 'Bolo de sorvete',
      price: 80.0,
      imageUrl: '/product-images/unsplash_QNyRp21hb5I.png',
    },
    {
      id: 4,
      name: 'Bolo de Amora',
      price: 80.0,
      imageUrl: '/product-images/unsplash__TN1m5R1pFI.png',
    },
  ];

  recommendedTitle = 'Recomendados Para Voce';
  recommendedItems: Item[] = [
    {
      id: 1,
      name: 'Bolo de Morango',
      price: 80.0,
      imageUrl: '/product-images/unsplash__nAVO0UGt2A.png',
    },
    {
      id: 2,
      name: 'Bolo de sorvete',
      price: 80.0,
      imageUrl: '/product-images/unsplash_QNyRp21hb5I.png',
    },
    {
      id: 3,
      name: 'Bolo de Amora',
      price: 80.0,
      imageUrl: '/product-images/unsplash__TN1m5R1pFI.png',
    },
    {
      id: 4,
      name: 'Bolo de Chocolate',
      price: 80.0,
      imageUrl: '/product-images/unsplash_kPxsqUGneXQ.png',
    },
  ];
}
