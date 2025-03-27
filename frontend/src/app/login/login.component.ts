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

  constructor(private router: Router) {}

  name = 'Login';
  private loginService = inject(LoginService);


  loginForm: FormGroup = new FormGroup({
    name: new FormControl(""),
    email: new FormControl(""),
    // password: new FormControl(""),

  });

  login() {
    debugger;
    const formValues = this.loginForm.value;
    console.log('Valores do formulário:', formValues);  // Verifica os dados enviados
    this.loginService.login(formValues).subscribe({
      next: (data: any) => {
        const token = data.split(" ")[1];  // Pega o token após "Bearer"
        console.log('Login bem-sucedido:', token);
        this.loginService.saveToken(token); 
        this.router.navigate(['/main']);
      },
      error: (error) => {
        console.error('Erro no login:', error);
      }
    });
  }
  
  

  getAllUsers() {
    this.loginService.getAllUsers().subscribe(data => {
      console.log(data);
    });
  }

}