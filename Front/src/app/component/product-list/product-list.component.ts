import { Component, Input } from '@angular/core';
import { Product } from '../../model/product.model';
import { CurrencyUtils } from '../../class/currency-utils/currency-utils';
import { CartService } from '../../service/cart/cart.service';
import { ProductCardComponent } from '../product-card/product-card.component';

@Component({
  selector: 'app-product-list',
  imports: [ProductCardComponent],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css',
  inputs: ['items', 'title'],
})
export class ProductListComponent {
  @Input() title: string = '';
  @Input() items: Product[] = [];
  @Input() type: 'row' | 'complete' = 'row';
  currencyUtils: CurrencyUtils = new CurrencyUtils();

  containerClass: string = 'card-list';

  ngOnInit() {
    if (this.type === 'complete') {
      this.containerClass = 'card-list-complete';
    }
  }
}
