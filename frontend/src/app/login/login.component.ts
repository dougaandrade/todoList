import { Component, inject } from '@angular/core';
import { LoginService } from '../services/login.service';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms'; // Importação necessária
import { R } from '@angular/cdk/keycodes';


@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  name = 'Login';
  private loginService = inject(LoginService);


  loginForm: FormGroup = new FormGroup({
    name: new FormControl(""),
    email: new FormControl(""),
    // password: new FormControl(""),

  });

  login() {
    const formValues = this.loginForm.value;
    // debugger;
    this.loginService.login(formValues).subscribe(data => {
      console.log(data);
    });
  }

  getAllUsers() {
    this.loginService.getAllUsers().subscribe(data => {
      console.log(data);
    });
  }

}