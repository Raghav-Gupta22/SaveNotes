import { Injectable, OnInit } from '@angular/core';
import { UserserviceService } from './userservice.service';
import { AuthserviceService } from './authservice.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class NoteserviceService {
  user: any;
  constructor(
    private userservice: UserserviceService,
    private router: Router,
    private authservice: AuthserviceService,
    private http: HttpClient
  ) {}
  public showNotes(id: any) {
    return this.http.get('http://localhost:3000/' + id + '/showNotes');
  }

  public addNote(noteData: any, id: any) {
    return this.http.post('http://localhost:3000/' + id + '/addNote', noteData);
  }
  public delete(noteId: any) {
    return this.http
      .delete('http://localhost:3000/' + noteId + '/delete')
      .subscribe((response) => {});
  }
  public edit(noteId: any, note: any) {
    return this.http.put('http://localhost:3000/' + noteId + '/editNote', note);
  }
  public setNote(note: any) {
    localStorage.setItem('note', JSON.stringify(note));
  }

  public getNote() {
    let note = localStorage.getItem('note');
    if (note != null) {
      return JSON.parse(note);
    }
  }
}
