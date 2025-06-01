import { Component, Input } from '@angular/core';
import { Product } from '../../model/product.model';
import { CurrencyUtils } from '../../class/currency-utils/currency-utils';
import { CartService } from '../../service/cart/cart.service';
import { StepperComponent } from '../stepper/stepper.component';

@Component({
  selector: 'app-product-card',
  imports: [StepperComponent],
  inputs: ['item'],
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.css',
})
export class ProductCardComponent {
  @Input() item!: Product;
  currencyUtils = new CurrencyUtils();

  constructor(private cartService: CartService) {}

  addToCart(): void {
    this.cartService.addToCart(this.item);
  }

  editCartItem = (amount: number): void => {
    this.cartService.editCartItem(this.item, amount);
  };

  getAmountInCart(): number {
    return this.cartService.getAmountInCart(this.item);
  }
}
