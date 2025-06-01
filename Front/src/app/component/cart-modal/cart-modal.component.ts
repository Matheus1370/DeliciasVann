import { Component } from '@angular/core';
import { CartItem, CartService } from '../../service/cart/cart.service';
import { Product } from '../../model/product.model';

@Component({
  selector: 'app-cart-modal',
  imports: [],
  templateUrl: './cart-modal.component.html',
  styleUrl: './cart-modal.component.css',
})
export class CartModalComponent {
  constructor(private cartService: CartService) {}

  get cartItems(): CartItem[] {
    return this.cartService.getCartItems();
  }
}
