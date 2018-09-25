import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-listagem',
  templateUrl: './listagem.component.html',
  styleUrls: ['./listagem.component.css']
})
export class ListagemComponent implements OnInit {

  Listar = [
    {id: 1, nome: 'Arroz', quantidade:5},
    {id: 2, nome: 'Feijão', quantidade:2},
    {id: 3, nome: 'Macarrão', quantidade:6},
    {id: 4, nome: 'Salada', quantidade:3},
    {id: 5, nome: 'Tomate', quantidade:8},
    {id: 6, nome: 'Camarão', quantidade:15},
    {id: 7, nome: 'Alface', quantidade:65}
  ];

  constructor() { }

  ngOnInit() {
  }

}
