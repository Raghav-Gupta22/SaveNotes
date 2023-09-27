import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NoteserviceService } from '../services/noteservice.service';

@Component({
  selector: 'app-edit-note',
  templateUrl: './edit-note.component.html',
  styleUrls: ['./edit-note.component.css'],
})
export class EditNoteComponent implements OnInit {
  title = '';
  description = '';
  note: any;
  constructor(
    private router: Router,
    private noteService: NoteserviceService
  ) {}
  ngOnInit(): void {
    this.note = this.noteService.getNote();
    this.title = this.note.title;
    this.description = this.note.description;
  }
  public editNote() {
    if (this.note != null) {
      let newNote = {
        title: this.title,
        description: this.description,
      };
      this.noteService.edit(this.note.id, newNote).subscribe(()=>{
        this.router.navigate(['home']);
      })
    }
  }
  cancel(){
    this.router.navigate(['home'])
  }
}
