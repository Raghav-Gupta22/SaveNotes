import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './auth/guard.guard';
import { AddNoteComponent } from './add-note/add-note.component';
import { EditNoteComponent } from './edit-note/edit-note.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    path: 'home',
    component: HomeComponent,
    canActivate :[AuthGuard],
  },
  {
    path: 'addnote',
    component: AddNoteComponent,
    canActivate :[AuthGuard],
  },
  {
    path: 'editnote',
    component: EditNoteComponent,
    canActivate :[AuthGuard],
  },
  
  {
    path: '**',
    component: LoginComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
