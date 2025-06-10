import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './component/header/header.component';
import { BannerComponent } from './component/banner/banner.component';
import { ProductListComponent } from './component/product-list/product-list.component';
import { VSeparatorComponent } from './component/vseparator/vseparator.component';
import { FooterComponent } from './component/footer/footer.component';
import { Product } from './model/product.model';
import { ProductService } from './service/product/product.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  providers: [],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'delicias da van';
}
