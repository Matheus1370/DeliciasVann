import { Routes } from '@angular/router';
import { ProductListPageComponent } from './routes/product-list-page/product-list-page.component';
import { HomePageComponent } from './routes/home-page/home-page.component';
import { LoginPageComponent } from './routes/login-page/login-page.component';
import { RegisterPageComponent } from './routes/register-page/register-page.component';
import { ProfilePageComponent } from './routes/profile-page/profile-page.component';

export const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'products', component: ProductListPageComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'register', component: RegisterPageComponent },
  { path: 'profile', component: ProfilePageComponent },
];
