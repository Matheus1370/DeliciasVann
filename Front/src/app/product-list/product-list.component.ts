import { Component, Input } from '@angular/core';

export interface Item {
  id: number;
  imageUrl: string;
  name: string;
  price: number;
}

@Component({
  selector: 'app-product-list',
  imports: [],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css',
  inputs: ['items', 'title'],
})
export class ProductListComponent {
  @Input() title: string = '';
  @Input() items: Item[] = [];

  numberToCurrency(value: number): string {
    return new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL',
    }).format(value);
  }
}
