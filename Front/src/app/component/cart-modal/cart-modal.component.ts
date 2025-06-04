import { Component } from '@angular/core';
import { CartItem, CartService } from '../../service/cart/cart.service';
import { Product } from '../../model/product.model';
import { ProductCardComponent } from '../product-card/product-card.component';
import { CurrencyUtils } from '../../class/currency-utils/currency-utils';
import { CartProductCardComponent } from '../cart-product-card/cart-product-card.component';
import { VSeparatorComponent } from '../vseparator/vseparator.component';

@Component({
  selector: 'app-cart-modal',
  imports: [CartProductCardComponent, VSeparatorComponent],
  templateUrl: './cart-modal.component.html',
  styleUrl: './cart-modal.component.css',
})
export class CartModalComponent {
  public currencyUtils: CurrencyUtils = new CurrencyUtils();

  constructor(public cartService: CartService) {}

  get cartItems(): CartItem[] {
    return this.cartService.getCartItems();
  }

  getMainDivDisplay(): string {
    return this.cartService.isCartModalOpen ? 'flex' : 'none';
  }

  closeCartModal(): void {
    this.cartService.isCartModalOpen = false;
  }
}
