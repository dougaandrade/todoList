import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router'; // Import RouterModule

interface Board {
  id: string;
  name: string;
  cardCount: number;
}

@Component({
  selector: 'app-boards',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './boards.component.html',
  styleUrls: ['./boards.component.css']
})
export class BoardsComponent {
  boards: Board[] = [
    { id: '1', name: 'First board', cardCount: 0 }
  ];

  addBoard() {
    const newBoard: Board = {
      id: Date.now().toString(),
      name: `New Board ${this.boards.length + 1}`,
      cardCount: 0
    };
    this.boards.push(newBoard);
  }

  deleteBoard(id: string) {
    this.boards = this.boards.filter(board => board.id !== id);
  }
}
