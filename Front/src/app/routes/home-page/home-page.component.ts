import { Component } from '@angular/core';
import { HeaderComponent } from '../../component/header/header.component';
import { BannerComponent } from '../../component/banner/banner.component';
import { ProductListComponent } from '../../component/product-list/product-list.component';
import { VSeparatorComponent } from '../../component/vseparator/vseparator.component';
import { FooterComponent } from '../../component/footer/footer.component';
import { Product } from '../../model/product.model';
import { ProductService } from '../../service/product/product.service';

@Component({
  selector: 'app-home-page',
  imports: [
    HeaderComponent,
    BannerComponent,
    ProductListComponent,
    VSeparatorComponent,
    FooterComponent,
  ],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css',
})
export class HomePageComponent {
  mostSoldItems: Product[] = [];

  recommendedItems: Product[] = [];

  constructor(private productService: ProductService) {
    productService.getAllProducts().subscribe((items) => {
      this.mostSoldItems = items.sort((a, b) => a.stock - b.stock).slice(0, 4);
      this.recommendedItems = items
        .sort((a, b) => b.stock - a.stock)
        .slice(0, 4);
    });
  }
  mostSoldTitle = 'Mais Comprados';

  recommendedTitle = 'Recomendados Para Voce';
}
