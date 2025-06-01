export enum ProductCategory {
  CAKE = 'cake',
  PASTRY = 'pastry',
  BREAD = 'bread',
  DRINK = 'drink',
  OTHER = 'other',
}

export const categoryTitleMapper = {
  [ProductCategory.CAKE]: 'Bolos',
  [ProductCategory.PASTRY]: 'Doces',
  [ProductCategory.BREAD]: 'Pães',
  [ProductCategory.DRINK]: 'Bebidas',
  [ProductCategory.OTHER]: 'Outros',
};

export interface Product {
  id: string;
  imageUrl: string;
  name: string;
  price: number;
  description: string;
  category: ProductCategory;
  stock: number;
}
