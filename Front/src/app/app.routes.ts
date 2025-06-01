import { Routes } from '@angular/router';
import { ProductListPageComponent } from './routes/product-list-page/product-list-page.component';
import { HomePageComponent } from './routes/home-page/home-page.component';

export const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'products', component: ProductListPageComponent },
];
