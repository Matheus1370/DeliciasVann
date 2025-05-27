import { Component, Input } from '@angular/core';
import { Product } from '../model/product.model';


@Component({
  selector: 'app-product-list',
  imports: [],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css',
  inputs: ['items', 'title'],
})
export class ProductListComponent {
  @Input() title: string = '';
  @Input() items: Product[] = [];

  numberToCurrency(value: number): string {
    return new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL',
    }).format(value);
  }
}
