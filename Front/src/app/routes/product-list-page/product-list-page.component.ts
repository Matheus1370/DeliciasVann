import { Component } from '@angular/core';
import { HeaderComponent } from '../../component/header/header.component';
import { BannerComponent } from '../../component/banner/banner.component';
import { FooterComponent } from '../../component/footer/footer.component';
import { ProductService } from '../../service/product/product.service';
import {
  categoryTitleMapper,
  Product,
  ProductCategory,
} from '../../model/product.model';
import { ProductListComponent } from '../../component/product-list/product-list.component';
import { VSeparatorComponent } from '../../component/vseparator/vseparator.component';
import { CartModalComponent } from '../../component/cart-modal/cart-modal.component';

@Component({
  selector: 'app-product-list-page',
  imports: [
    HeaderComponent,
    BannerComponent,
    FooterComponent,
    ProductListComponent,
    VSeparatorComponent,
    CartModalComponent,
  ],

  templateUrl: './product-list-page.component.html',
  styleUrl: './product-list-page.component.css',
})
export class ProductListPageComponent {
  items: { [key in ProductCategory]: Product[] } = {
    [ProductCategory.BREAD]: [],
    [ProductCategory.CAKE]: [],
    [ProductCategory.PASTRY]: [],
    [ProductCategory.DRINK]: [],
    [ProductCategory.OTHER]: [],
  };

  titleMapper = categoryTitleMapper;

  constructor(private productService: ProductService) {
    productService.getAllProducts().subscribe((items) => {
      for (let category in ProductCategory) {
        this.items[ProductCategory[category as keyof typeof ProductCategory]] =
          items.filter((item) => {
            return (
              item.category ==
              ProductCategory[category as keyof typeof ProductCategory]
            );
          });
      }
    });
  }

  keys(): ProductCategory[] {
    return Object.keys(this.items) as ProductCategory[];
  }
}
