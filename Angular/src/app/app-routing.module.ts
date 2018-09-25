import { NgModule } from '@angular/core';
import { Route, RouterModule } from '@angular/router';
import {CadastroComponent} from './cadastro/cadastro.component';
import {ListagemComponent} from './listagem/listagem.component';

const rotas: Route[] = [
  {path: '',  redirectTo: '/Cadastro', pathMatch: 'full'},
  {path: 'Cadastro', component: CadastroComponent},
  {path: 'Listagem', component: ListagemComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(rotas)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
