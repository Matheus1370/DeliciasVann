import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  logoImgSrc = 'assets/img-logo.svg';
  bagIconSrc = 'assets/ic-bag.svg';
  profileIconSrc = 'assets/ic-profile.svg';

}
