import { Injectable } from '@angular/core';
import { Product } from '../../model/product.model';

interface CartItem {
  product: Product;
  amount: number;
}

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private items: CartItem[] = [];

  constructor() {}

  addToCart(product: Product) {
    const existingItem = this.items.find(
      (item) => item.product.id === product.id
    );
    if (existingItem) {
      existingItem.amount++;
    } else {
      this.items.push({ product, amount: 1 });
    }
  }

  removeFromCart(product: Product) {
    const index = this.items.findIndex(
      (item) => item.product.id === product.id
    );
    if (index !== -1) {
      this.items.splice(index, 1);
    }
  }

  editCartItem(product: Product, amount: number) {
    const existingItem = this.items.find(
      (item) => item.product.id === product.id
    );
    if (existingItem) {
      if (amount <= 0) {
        this.removeFromCart(product);
      } else {
        existingItem.amount = amount;
      }
    }
  }

  getAmountInCart(product: Product): number {
    const existingItem = this.items.find(
      (item) => item.product.id === product.id
    );
    return existingItem ? existingItem.amount : 0;
  }

  getCartItems(): CartItem[] {
    return this.items;
  }

  clearCart() {
    this.items = [];
  }

  getTotalPrice(): number {
    return this.items.reduce(
      (total, item) => total + item.product.price * item.amount,
      0
    );
  }
}
