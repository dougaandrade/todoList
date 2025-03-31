import { Component, inject } from '@angular/core';
import { LoginService } from '../services/login.service';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms'; // Importação necessária
import { R } from '@angular/cdk/keycodes';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  constructor(private readonly router: Router) {}

  name = 'Login';
  private readonly loginService = inject(LoginService);


  loginForm: FormGroup = new FormGroup({
    name: new FormControl(""),
    email: new FormControl(""),
    password: new FormControl(""),

  });

  onLogin() {
    const formValues = this.loginForm.value;
    console.log('Valores do formulário:', formValues);
    // debugger;
    this.loginService.login(formValues).subscribe({
      next: (data: string) => {
        const token = data;  // Pega o token após "Bearer"
        console.log('Login bem-sucedido:', token);
        this.loginService.saveToken(token); 
        this.router.navigate(['/main']);
      },
      error: (error) => {
        console.error('Erro no login:', error);
      },
    });
    // console.log(this.loginService.getToken());
  }
  
  getAllUsers() {
    this.loginService.getAllUsers().subscribe(data => {
      console.log(data);
    });
  }

}