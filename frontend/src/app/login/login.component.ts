import { Component, inject } from '@angular/core';
import { LoginService } from '../services/login.service';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms'; // Importação necessária
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common'; // Importação necessária

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule], // Adicionado CommonModule
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  constructor(private readonly router: Router) {}

  name = 'Login';
  private readonly loginService = inject(LoginService);
  loginError: string | null = null;
  loginSuccess: boolean = false;
  userName: string = '';

  loginForm: FormGroup = new FormGroup({
    email: new FormControl(""),
    password: new FormControl(""),

  });

  onLogin() {
    const formValues = this.loginForm.value;
    console.log('Valores do formulário:', formValues);

    this.loginService.login(formValues).subscribe({
      next: (data: string) => {
        const token = data;
        console.log('Login bem-sucedido:', token);
        this.loginService.saveToken(token);
        this.userName = formValues.email.split('@')[0]; // Extrai o nome do email
        this.loginSuccess = true;
        setTimeout(() => {
          this.router.navigate(['/main']);
        }, 2000); // Redireciona após 3 segundos
      },
      error: (error) => {
        console.error('Erro no login:', error);
        this.loginError = 'Email ou senha incorreto';
        setTimeout(() => {
          this.loginError = null;
        }, 3000);
      },
    });
  }
  
  getAllUsers() {
    this.loginService.getAllUsers().subscribe(data => {
      console.log(data);
    });
  }

}