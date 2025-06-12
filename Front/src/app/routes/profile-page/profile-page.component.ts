import { Component } from '@angular/core';
import { HeaderComponent } from '../../component/header/header.component';
import { FooterComponent } from '../../component/footer/footer.component';
import { AuthenticationService } from '../../service/authentication/authentication.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-profile-page',
  imports: [HeaderComponent, FooterComponent, FormsModule],
  templateUrl: './profile-page.component.html',
  styleUrl: './profile-page.component.css',
})
export class ProfilePageComponent {
  newEmail: string = '';
  newName: string = '';

  constructor(public authenticationService: AuthenticationService) {
    // Check if the user is logged in
    if (!this.authenticationService.userToken) {
      // Redirect to login page if not logged in
      window.location.href = '/login'; // Adjust the path as needed
      return;
    }

    this.newEmail = this.authenticationService.userData?.email || '';
    this.newName = this.authenticationService.userData?.name || '';
  }

  ngOnInit() {
    // Any additional initialization logic can go here
    this.newEmail = this.authenticationService.userData?.email || '';
    this.newName = this.authenticationService.userData?.name || '';
  }

  logout() {
    localStorage.clear();
    window.location.href = '/'; // Adjust the path as needed
  }

  updateProfile() {
    this.authenticationService.updateUserData({
      id: this.authenticationService.userData?.id || '',
      email: this.newEmail,
      name: this.newName,
      password: this.authenticationService.userData?.password || '',
      createdAt: this.authenticationService.userData?.createdAt || '',
    });
  }
}
