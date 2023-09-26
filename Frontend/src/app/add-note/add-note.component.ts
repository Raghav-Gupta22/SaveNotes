import { Component } from '@angular/core';
import { NoteserviceService } from '../services/noteservice.service';
import { UserserviceService } from '../services/userservice.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-add-note',
  templateUrl: './add-note.component.html',
  styleUrls: ['./add-note.component.css'],
})
export class AddNoteComponent {
  title = '';
  description = '';
  user: any;
  constructor(
    private noteservice: NoteserviceService,
    private userservice: UserserviceService,
    private router : Router
  ) {}

  addNote() {
    let newNote = {
      title: this.title,
      description: this.description,
    };
    this.user = this.userservice.getUser();
    this.noteservice.addNote(newNote, this.user.id).subscribe(
      response => {
        console.log(response); // Log the response from the server
        this.router.navigate(["home"]); // Navigate to home page only if the request is successful
      },
      error => {
        console.error(error); // Log any error if the request fails
      }
    );
  }
}
