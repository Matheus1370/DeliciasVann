import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { VSeparatorComponent } from '../../component/vseparator/vseparator.component';

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

  onSubmit() {
    console.log(this.userData);
  }
}
