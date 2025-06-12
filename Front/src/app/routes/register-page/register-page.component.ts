import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { VSeparatorComponent } from '../../component/vseparator/vseparator.component';
import { AuthenticationService } from '../../service/authentication/authentication.service';

@Component({
  selector: 'app-register-page',
  imports: [FormsModule, VSeparatorComponent],
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.css',
})
export class RegisterPageComponent {
  userData = {
    name: '',
    email: '',
    password: '',
    confirmPassword: '',
  };

  constructor(private authenticationService: AuthenticationService) {}

  onSubmit() {
    if (this.userData.password !== this.userData.confirmPassword) {
      alert('As senhas devem ser iguais!');
      return;
    }

    this.authenticationService
      .register(this.userData.email, this.userData.password, this.userData.name)
      .subscribe({
        next: (response) => {
          window.location.href = '/login';
        },
        error: (error) => {
          console.error('Registration error:', error);
          alert('Erro ao registrar usuário. Tente novamente mais tarde.');
        },
      });
  }
}
