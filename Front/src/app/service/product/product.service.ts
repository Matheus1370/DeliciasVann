import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../../model/product.model';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getAllProducts() {
    return this.http.get<Product[]>(this.baseUrl + '/product');
  }

  getProductById(id: string) {
    return this.http.get<Product>(`${this.baseUrl}/product/${id}`);
  }
}
