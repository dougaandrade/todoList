import { Component, OnInit } from '@angular/core';
import { LoginService } from './services/login.service';
import { LoginComponent } from './login/login.component';

@Component({
  selector: 'app-root',
  standalone : true,
  imports: [LoginComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  {
  mensagem: string = '';

  constructor(private loginService: LoginService) { }

  // ngOnInit(): void {
  //   this.loginService.getTeste().subscribe(response => {
  //     this.mensagem = response;
  //   });
  // }
}
