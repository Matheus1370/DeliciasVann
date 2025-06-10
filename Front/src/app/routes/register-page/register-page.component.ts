import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { VSeparatorComponent } from '../../component/vseparator/vseparator.component';

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

  onSubmit() {
    console.log(this.userData);
  }
}
