import { Component, inject } from '@angular/core';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-login',
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  name = 'Login';
  private loginService = inject(LoginService);

  login() {
    this.loginService.login();
  }

  getAllUsers() {
    this.loginService.getAllUsers().subscribe(data => {
      console.log(data);
    });
  }

}