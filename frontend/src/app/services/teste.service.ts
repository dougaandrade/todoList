import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TesteService {
  private apiUrl = 'http://localhost:8080/api/teste'; // URL do backend

  constructor(private http: HttpClient) { }

  getTeste(): Observable<string> {
    return this.http.get(this.apiUrl, { responseType: 'text' });
  }
}
