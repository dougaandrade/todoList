import { Component, OnInit } from '@angular/core';
import { TesteService } from './services/teste.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  mensagem: string = '';

  constructor(private testeService: TesteService) { }

  ngOnInit(): void {
    this.testeService.getTeste().subscribe(response => {
      this.mensagem = response;
    });
  }
}
