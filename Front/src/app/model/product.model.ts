export enum ProductCategory {
  CAKE = 'cake',
  PASTRY = 'pastry',
  BREAD = 'bread',
  DRINK = 'drink',
  OTHER = 'other'
}

export interface Product {
  id: string;
  imageUrl: string;
  name: string;
  price: number;
  description: string;
  category: ProductCategory;
  stock: number;

}
