import { Component } from '@angular/core';
import { CartService } from '../../service/cart/cart.service';
import { AuthenticationService } from '../../service/authentication/authentication.service';

@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent {
  logoImgSrc = 'assets/img-logo.svg';
  bagIconSrc = 'assets/ic-bag.svg';
  profileIconSrc = 'assets/ic-profile.svg';

  isLoggedIn: boolean = false;
  constructor(
    private cartService: CartService,
    private authService: AuthenticationService
  ) {
    this.isLoggedIn = !!this.authService.userToken;
  }

  openCartModal(): void {
    this.cartService.isCartModalOpen = true;
  }
}
