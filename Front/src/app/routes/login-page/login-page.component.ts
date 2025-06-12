import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { VSeparatorComponent } from '../../component/vseparator/vseparator.component';
import { AuthenticationService } from '../../service/authentication/authentication.service';

@Component({
  selector: 'app-login-page',
  imports: [FormsModule, VSeparatorComponent],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css',
})
export class LoginPageComponent {
  userData = {
    email: '',
    password: '',
  };

  constructor(private authenticationService: AuthenticationService) {
    // Check if the user is already logged in
    if (this.authenticationService.userToken) {
      // Redirect to home page or another page if already logged in
      window.location.href = '/'; // Adjust the path as needed
    }
  }

  onSubmit() {
    this.authenticationService
      .login(this.userData.email, this.userData.password)
      .subscribe({
        next: (response) => {
          this.authenticationService.userToken = response['access_token']; // Assuming the token is returned in the response

          window.location.href = '/';
        },
        error: (error) => {
          alert('Usuário ou senha inválidos!');
        },
      });
  }

  ngOnInit() {}
}
