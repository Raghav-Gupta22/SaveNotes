import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { UserserviceService } from '../services/userservice.service';
import { Router } from '@angular/router';
import { AuthserviceService } from '../services/authservice.service';
import { NoteserviceService } from '../services/noteservice.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  user: any;
  notes: any;
  p: any;
  temp: any;

  constructor(
    private userservice: UserserviceService,
    private router: Router,
    private authservice: AuthserviceService,
    private noteservice: NoteserviceService
  ) {}

  ngOnInit(): void {
    this.user = this.userservice.getUser();
    console.log(this.user);
    this.noteservice.showNotes(this.user.id).subscribe(
      (data) => {
        this.notes = data;
      }
    );
  }

  logout() {
    this.userservice.logout();
    this.router.navigate(['login']);
  }

  addNote() {
    this.router.navigate(['addnote']);
  }

  delete(noteId: any) {
    console.log(noteId);
    this.noteservice.delete(noteId);
    window.location.reload();
  }
  edit(note: any){
    this.noteservice.setNote(note)
    this.router.navigate(['editnote'])
  }
}
