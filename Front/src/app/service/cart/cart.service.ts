import { Injectable } from '@angular/core';
import { Product } from '../../model/product.model';
import { LocalStorageDataSource } from '../../data/local-storage.datasource';
import { ProductService } from '../product/product.service';

export interface CartItem {
  product: Product;
  amount: number;
}

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private items: CartItem[] = [];

  constructor(private productService: ProductService) {
    const storedItems = LocalStorageDataSource.getCartItems();

    for (const item of storedItems) {
      this.productService.getProductById(item.id).subscribe({
        next: (product) => {
          if (product) {
            this.items.push({ product, amount: item.amount });
          }
        },
        error: (err) => {
          console.error('Error fetching product:', err);
        },
      });
    }
  }

  addToCart(product: Product) {
    const existingItem = this.items.find(
      (item) => item.product.id === product.id
    );
    if (existingItem) {
      existingItem.amount++;
    } else {
      this.items.push({ product, amount: 1 });
    }
    LocalStorageDataSource.updateCartItems(
      this.items.map((item) => ({
        id: item.product.id,
        amount: item.amount,
      }))
    );
  }

  removeFromCart(product: Product) {
    const index = this.items.findIndex(
      (item) => item.product.id === product.id
    );
    if (index !== -1) {
      this.items.splice(index, 1);
    }
    LocalStorageDataSource.updateCartItems(
      this.items.map((item) => ({
        id: item.product.id,
        amount: item.amount,
      }))
    );
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
    LocalStorageDataSource.updateCartItems(
      this.items.map((item) => ({
        id: item.product.id,
        amount: item.amount,
      }))
    );
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
