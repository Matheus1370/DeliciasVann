import { Component, Input } from '@angular/core';
import { CartItem, CartService } from '../../service/cart/cart.service';
import { CurrencyUtils } from '../../class/currency-utils/currency-utils';
import { StepperComponent } from '../stepper/stepper.component';

@Component({
  selector: 'app-cart-product-card',
  imports: [StepperComponent],
  inputs: ['item'],
  templateUrl: './cart-product-card.component.html',
  styleUrl: './cart-product-card.component.css',
})
export class CartProductCardComponent {
  @Input() item!: CartItem;

  currencyUtils: CurrencyUtils = new CurrencyUtils();

  constructor(public cartService: CartService) {}

  editCartItem = (quantity: number): void => {
    this.cartService.editCartItem(this.item.product, quantity);
  };
}
